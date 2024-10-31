package br.com.challenge.aihacklogistics.fragment.medicamentos

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.challenge.aihacklogistics.R
import br.com.challenge.aihacklogistics.databinding.ActivityEditarMedicamentoBinding
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class PaginaEditarMedicamentoFragment : Fragment() {

    private var _binding: ActivityEditarMedicamentoBinding? = null
    private val binding get() = _binding!!
    private val BASE_URL = "https://tdsr-d6c6c-default-rtdb.firebaseio.com"
    private val cliente = OkHttpClient()
    private lateinit var sharedPreferences: SharedPreferences
    private var pedidoId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityEditarMedicamentoBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences("PedidosPrefs", Context.MODE_PRIVATE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recebe os dados do pedido enviados pelo bundle
        pedidoId = arguments?.getString("id")
        val dadosPedido = arguments?.getString("dadosPedido")
        val jsonPedido = JSONObject(dadosPedido)

        // Preencher os campos com os dados recebidos
        binding.inputNomeSolicitante.setText(jsonPedido.getString("nome"))
        binding.inputMedicamento.setText(jsonPedido.getString("medicamento"))
        binding.inputQuantidade.setText(jsonPedido.getString("quantidade"))
        binding.inputCpf.setText(jsonPedido.getString("cpf"))

        // Lógica do botão Salvar
        binding.buttonSalvar.setOnClickListener {
            val nome = binding.inputNomeSolicitante.text.toString()
            val medicamento = binding.inputMedicamento.text.toString()
            val quantidade = binding.inputQuantidade.text.toString()
            val cpf = binding.inputCpf.text.toString()

            // Validação básica dos campos
            if (nome.isEmpty() || medicamento.isEmpty() || quantidade.isEmpty() || cpf.isEmpty()) {
                Toast.makeText(requireContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Verificação de validade do CPF
            if (!isValidCPF(cpf)) {
                Toast.makeText(requireContext(), "CPF inválido!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Criação do JSON com os dados atualizados do pedido
            val pedidoAtualizadoJson = """
                {
                    "nome": "$nome",
                    "medicamento": "$medicamento",
                    "quantidade": "$quantidade",
                    "cpf": "$cpf"
                }
            """.trimIndent()

            // Salvar localmente antes de enviar ao Firebase
            salvarPedidoLocalmente(nome, medicamento, quantidade, cpf)

            // Criação da requisição PUT para o Firebase
            val request = Request.Builder()
                .url("$BASE_URL/pedidos/$pedidoId.json")
                .put(pedidoAtualizadoJson.toRequestBody("application/json".toMediaType()))
                .build()

            // Callback da requisição HTTP
            cliente.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    requireActivity().runOnUiThread {
                        Toast.makeText(
                            requireContext(),
                            "Erro ao atualizar pedido: ${e.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    requireActivity().runOnUiThread {
                        Toast.makeText(
                            requireContext(),
                            "Pedido atualizado com sucesso!",
                            Toast.LENGTH_LONG
                        ).show()

                        // Voltar para a pagina inicial de medicamentos
                        findNavController().navigate(R.id.action_PaginaEditarPedido_to_PaginaMedicamentosActivity)
                    }
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Função para salvar o pedido localmente
    private fun salvarPedidoLocalmente(nome: String, medicamento: String, quantidade: String, cpf: String) {
        val editor = sharedPreferences.edit()
        editor.putString("nome", nome)
        editor.putString("medicamento", medicamento)
        editor.putString("quantidade", quantidade)
        editor.putString("cpf", cpf)
        editor.apply()

        Toast.makeText(requireContext(), "Pedido salvo localmente!", Toast.LENGTH_SHORT).show()
    }

    // Função para validar CPF
    private fun isValidCPF(cpf: String): Boolean {
        if (cpf.length != 11 || cpf.all { it == cpf[0] }) return false
        val digits = cpf.map { it.toString().toInt() }
        val calcDigit = { factors: List<Int> ->
            val sum = digits.zip(factors).map { it.first * it.second }.sum()
            val mod = sum % 11
            if (mod < 2) 0 else 11 - mod
        }

        val firstDigit = calcDigit((10 downTo 2).toList())
        val secondDigit = calcDigit((11 downTo 2).toList())
        return digits[9] == firstDigit && digits[10] == secondDigit
    }
}