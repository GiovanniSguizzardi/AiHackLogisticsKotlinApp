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
import br.com.challenge.aihacklogistics.databinding.PaginaSolicitacaoMedicamentoBinding

class PaginaSolicitacaoMedicamentoFragment : Fragment() {

    private var _binding: PaginaSolicitacaoMedicamentoBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PaginaSolicitacaoMedicamentoBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences("SolicitacaoPrefs", Context.MODE_PRIVATE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLimpar.setOnClickListener {
            binding.inputNome.text.clear()
            binding.inputMedicamento.text.clear()
            binding.inputQuantidade.text.clear()
            binding.inputCpf.text.clear()
        }

        carregarSolicitacaoLocal()

        binding.buttonSolicitar.setOnClickListener {
            val nome = binding.inputNome.text.toString()
            val medicamento = binding.inputMedicamento.text.toString()
            val quantidade = binding.inputQuantidade.text.toString()
            val cpf = binding.inputCpf.text.toString()

            if (nome.isEmpty() || medicamento.isEmpty() || quantidade.isEmpty() || cpf.isEmpty()) {
                Toast.makeText(requireContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!isValidCPF(cpf)) {
                Toast.makeText(requireContext(), "CPF inválido!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            salvarSolicitacaoLocalmente(nome, medicamento, quantidade, cpf)

            val bundle = Bundle().apply {
                putString("nome", nome)
                putString("medicamento", medicamento)
                putString("quantidade", quantidade)
                putString("cpf", cpf)
            }

            findNavController().navigate(R.id.action_PaginaSolicitacaoMedicamento_to_PaginaConfirmacaoMedicamentoFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun salvarSolicitacaoLocalmente(nome: String, medicamento: String, quantidade: String, cpf: String) {
        val editor = sharedPreferences.edit()
        editor.putString("nome", nome)
        editor.putString("medicamento", medicamento)
        editor.putString("quantidade", quantidade)
        editor.putString("cpf", cpf)
        editor.apply()
        Toast.makeText(requireContext(), "Solicitação salva localmente!", Toast.LENGTH_SHORT).show()
    }

    private fun carregarSolicitacaoLocal() {
        val nome = sharedPreferences.getString("nome", "")
        val medicamento = sharedPreferences.getString("medicamento", "")
        val quantidade = sharedPreferences.getString("quantidade", "")
        val cpf = sharedPreferences.getString("cpf", "")

        if (!nome.isNullOrEmpty() && !medicamento.isNullOrEmpty() && !quantidade.isNullOrEmpty() && !cpf.isNullOrEmpty()) {
            binding.inputNome.setText(nome)
            binding.inputMedicamento.setText(medicamento)
            binding.inputQuantidade.setText(quantidade)
            binding.inputCpf.setText(cpf)
            Toast.makeText(requireContext(), "Dados carregados localmente!", Toast.LENGTH_SHORT).show()
        }
    }

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