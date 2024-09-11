package br.com.challenge.aihacklogistics.paginas.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.challenge.aihacklogistics.R
import br.com.challenge.aihacklogistics.databinding.PaginaConfirmacaoBinding
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class PaginaConfirmacaoFragment : Fragment() {

    private var _binding: PaginaConfirmacaoBinding? = null
    private val binding get() = _binding!!
    private val BASE_URL = "https://tdsr-d6c6c-default-rtdb.firebaseio.com"
    private val cliente = OkHttpClient()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PaginaConfirmacaoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nomePaciente = arguments?.getString("nome")
        val dataConsulta = arguments?.getString("data")
        val especialidade = arguments?.getString("especialidade")
        val cpf = arguments?.getString("cpf")

        binding.nomePaciente.text = "Nome: $nomePaciente"
        binding.data.text = "Data: $dataConsulta"
        binding.especialidade.text = "Especialidade: $especialidade"
        binding.btnAgendar.setOnClickListener {
            // Criação do JSON
            val contatoJson = """
                {
                    "nome": "$nomePaciente",
                    "especialidade": "$especialidade",
                    "cpf": "$cpf",
                    "dt_consulta": "$dataConsulta"
                }
            """.trimIndent()

            val request = Request.Builder()
                .url("$BASE_URL/contatos.json")
                .post(
                    contatoJson.toRequestBody("application/json".toMediaType())
                )
                .build()

            cliente.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    requireActivity().runOnUiThread {
                        Toast.makeText(
                            requireContext(),
                            "Erro ao pré-agendar consulta: ${e.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    requireActivity().runOnUiThread {
                        Toast.makeText(
                            requireContext(),
                            "Consulta pré-agendada com sucesso!",
                            Toast.LENGTH_LONG
                        ).show()

                        // Redireciona para a página inicial
                        findNavController().navigate(R.id.action_PaginaConfirmacaoFragment_to_PaginaInicial)
                    }
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}