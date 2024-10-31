package br.com.challenge.aihacklogistics.fragment.agendamentos

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.challenge.aihacklogistics.R
import br.com.challenge.aihacklogistics.databinding.PaginaAgendamentoBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class PaginaAgendamentoFragment : Fragment() {

    private var _binding: PaginaAgendamentoBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PaginaAgendamentoBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences("ConsultaPrefs", Context.MODE_PRIVATE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Limpar os campos ao clicar no botão Limpar
        binding.buttonLimpar.setOnClickListener {
            binding.inputNome.text.clear()
            binding.inputEspecialidade.text.clear()
            binding.inputData.text.clear()
            binding.inputCpf.text.clear()
        }


        // Carregar dados salvos localmente (se houver)
        carregarConsultaLocal()

        // Adiciona o TextWatcher para formatar a data automaticamente
        binding.inputData.addTextChangedListener(object : TextWatcher {
            private var isUpdating = false
            private val mask = "##/##/####"
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (isUpdating) return
                val str = s.toString().filter { it.isDigit() }
                if (str.length <= 8) {
                    isUpdating = true
                    val formatted = applyMask(mask, str)
                    s?.replace(0, s.length, formatted)
                    isUpdating = false
                }
            }

            private fun applyMask(mask: String, text: String): String {
                var i = 0
                val masked = StringBuilder()
                for (m in mask.toCharArray()) {
                    if (m == '#' && i < text.length) {
                        masked.append(text[i])
                        i++
                    } else if (i < text.length) {
                        masked.append(m)
                    }
                }
                return masked.toString()
            }
        })

        // Lógica do clique do botão "Consultar"
        binding.buttonConsultar.setOnClickListener {
            val nome = binding.inputNome.text.toString()
            val especialidade = binding.inputEspecialidade.text.toString()
            val dt_consulta = binding.inputData.text.toString()
            val cpf = binding.inputCpf.text.toString()

            // Validação básica dos campos
            if (nome.isEmpty() || especialidade.isEmpty() || cpf.isEmpty() || dt_consulta.isEmpty()) {
                Toast.makeText(requireContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Verificação de validade do CPF
            if (!isValidCPF(cpf)) {
                Toast.makeText(requireContext(), "CPF inválido!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Verificação de validade da data
            if (!isValidDate(dt_consulta)) {
                Toast.makeText(requireContext(), "Data inválida!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Salvar os dados localmente
            salvarConsultaLocalmente(nome, especialidade, dt_consulta, cpf)

            // Após a consulta de disponibilidade, navega para a página de confirmação
            val bundle = Bundle().apply {
                putString("nome", nome)
                putString("data", dt_consulta)
                putString("especialidade", especialidade)
                putString("cpf", cpf)
            }

            findNavController().navigate(
                R.id.action_PaginaAgendamento_to_PaginaConfirmacaoFragment,
                bundle
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Função para salvar a consulta localmente usando SharedPreferences
    private fun salvarConsultaLocalmente(nome: String, especialidade: String, data: String, cpf: String) {
        val editor = sharedPreferences.edit()
        editor.putString("nome", nome)
        editor.putString("especialidade", especialidade)
        editor.putString("data", data)
        editor.putString("cpf", cpf)
        editor.apply()

        Toast.makeText(requireContext(), "Consulta salva localmente!", Toast.LENGTH_SHORT).show()
    }

    // Função para carregar dados locais (se existirem)
    private fun carregarConsultaLocal() {
        val nome = sharedPreferences.getString("nome", "")
        val especialidade = sharedPreferences.getString("especialidade", "")
        val data = sharedPreferences.getString("data", "")
        val cpf = sharedPreferences.getString("cpf", "")

        if (!nome.isNullOrEmpty() && !especialidade.isNullOrEmpty() && !data.isNullOrEmpty() && !cpf.isNullOrEmpty()) {
            binding.inputNome.setText(nome)
            binding.inputEspecialidade.setText(especialidade)
            binding.inputData.setText(data)
            binding.inputCpf.setText(cpf)

            Toast.makeText(requireContext(), "Dados carregados localmente!", Toast.LENGTH_SHORT).show()
        }
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