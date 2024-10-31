package br.com.challenge.aihacklogistics.fragment.agendamentos

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
import br.com.challenge.aihacklogistics.databinding.ActivityEditarConsultaBinding
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class PaginaEditarConsultaFragment : Fragment() {

    private var _binding: ActivityEditarConsultaBinding? = null
    private val binding get() = _binding!!
    private val BASE_URL = "https://tdsr-d6c6c-default-rtdb.firebaseio.com"
    private val cliente = OkHttpClient()
    private lateinit var sharedPreferences: SharedPreferences
    private var consultaId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityEditarConsultaBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences("ConsultasPrefs", Context.MODE_PRIVATE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recebe os dados da consulta enviados pelo bundle
        consultaId = arguments?.getString("id")
        val dadosConsulta = arguments?.getString("dadosConsulta")
        val jsonConsulta = JSONObject(dadosConsulta)

        // Preencher os campos com os dados recebidos
        binding.inputNome.setText(jsonConsulta.getString("nome"))
        binding.inputEspecialidade.setText(jsonConsulta.getString("especialidade"))
        binding.inputCpf.setText(jsonConsulta.getString("cpf"))
        binding.inputDataConsulta.setText(jsonConsulta.getString("dt_consulta"))

        // Lógica do botão Salvar
        binding.buttonSalvar.setOnClickListener {
            val nome = binding.inputNome.text.toString()
            val especialidade = binding.inputEspecialidade.text.toString()
            val cpf = binding.inputCpf.text.toString()
            val dataConsulta = binding.inputDataConsulta.text.toString()

            // Validação básica dos campos
            if (nome.isEmpty() || especialidade.isEmpty() || cpf.isEmpty() || dataConsulta.isEmpty()) {
                Toast.makeText(requireContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Verificação de validade do CPF
            if (!isValidCPF(cpf)) {
                Toast.makeText(requireContext(), "CPF inválido!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Verificação de validade da data
            if (!isValidDate(dataConsulta)) {
                Toast.makeText(requireContext(), "Data inválida!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Criação do JSON com os dados atualizados
            val consultaAtualizadaJson = """
                {
                    "nome": "$nome",
                    "especialidade": "$especialidade",
                    "cpf": "$cpf",
                    "dt_consulta": "$dataConsulta"
                }
            """.trimIndent()

            // Salvar localmente antes de enviar ao Firebase
            salvarConsultaLocalmente(nome, especialidade, dataConsulta, cpf)

            // Criação da requisição PUT para o Firebase
            val request = Request.Builder()
                .url("$BASE_URL/contatos/$consultaId.json")
                .put(consultaAtualizadaJson.toRequestBody("application/json".toMediaType()))
                .build()

            // Callback da requisição HTTP
            cliente.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    requireActivity().runOnUiThread {
                        Toast.makeText(
                            requireContext(),
                            "Erro ao atualizar consulta: ${e.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    requireActivity().runOnUiThread {
                        Toast.makeText(
                            requireContext(),
                            "Consulta atualizada com sucesso!",
                            Toast.LENGTH_LONG
                        ).show()

                        // Voltar para a página anterior
                        findNavController().navigate(R.id.action_PaginaEditarConsulta_to_ListarClientesActivity)
                    }
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Função para salvar a consulta localmente
    private fun salvarConsultaLocalmente(nome: String, especialidade: String, data: String, cpf: String) {
        val editor = sharedPreferences.edit()
        editor.putString("nome", nome)
        editor.putString("especialidade", especialidade)
        editor.putString("data", data)
        editor.putString("cpf", cpf)
        editor.apply()

        Toast.makeText(requireContext(), "Consulta salva localmente!", Toast.LENGTH_SHORT).show()
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

    // Função para verificar se a data é válida
    private fun isValidDate(date: String): Boolean {
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        format.isLenient = false
        return try {
            format.parse(date) != null
        } catch (e: ParseException) {
            false
        }
    }
}