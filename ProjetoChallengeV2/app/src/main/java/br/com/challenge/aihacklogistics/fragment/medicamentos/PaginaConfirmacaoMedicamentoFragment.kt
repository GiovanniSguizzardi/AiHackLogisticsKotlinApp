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
import br.com.challenge.aihacklogistics.databinding.PaginaConfirmacaoMedicamentoBinding
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class PaginaConfirmacaoMedicamentoFragment : Fragment() {

    private var _binding: PaginaConfirmacaoMedicamentoBinding? = null
    private val binding get() = _binding!!
    private val BASE_URL = "https://tdsr-d6c6c-default-rtdb.firebaseio.com"
    private val cliente = OkHttpClient()
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PaginaConfirmacaoMedicamentoBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences("SolicitacaoPrefs", Context.MODE_PRIVATE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nomeSolicitante = arguments?.getString("nome")
        val medicamento = arguments?.getString("medicamento")
        val quantidade = arguments?.getString("quantidade")
        val cpf = arguments?.getString("cpf")

        binding.nomeSolicitante.text = "Nome: $nomeSolicitante"
        binding.medicamento.text = "Medicamento: $medicamento"
        binding.quantidade.text = "Quantidade: $quantidade"
        binding.cpf.text = "CPF: $cpf"

        binding.btnConfirmarPedido.setOnClickListener {
            val pedidoJson = """
                {
                    "nome": "$nomeSolicitante",
                    "medicamento": "$medicamento",
                    "quantidade": "$quantidade",
                    "cpf": "$cpf"
                }
            """.trimIndent()

            val request = Request.Builder()
                .url("$BASE_URL/pedidos.json")
                .post(pedidoJson.toRequestBody("application/json".toMediaType()))
                .build()

            cliente.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    requireActivity().runOnUiThread {
                        Toast.makeText(
                            requireContext(),
                            "Erro ao confirmar pedido: ${e.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    requireActivity().runOnUiThread {
                        Toast.makeText(
                            requireContext(),
                            "Pedido confirmado com sucesso!",
                            Toast.LENGTH_LONG
                        ).show()
                        limparDadosLocalmente()
                        findNavController().navigate(R.id.action_PaginaConfirmacaoMedicamentoFragment_to_PaginaMedicamentosActivity)
                    }
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun limparDadosLocalmente() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        Toast.makeText(requireContext(), "Dados locais limpos!", Toast.LENGTH_SHORT).show()
    }
}