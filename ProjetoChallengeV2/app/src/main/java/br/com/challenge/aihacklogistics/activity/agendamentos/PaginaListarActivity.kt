package br.com.challenge.aihacklogistics.activity.agendamentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.challenge.aihacklogistics.R
import br.com.challenge.aihacklogistics.databinding.ActivityListarConsultasBinding
import okhttp3.*
import org.json.JSONObject
import org.json.JSONException
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class PaginaListarActivity : Fragment() {

    private var _binding: ActivityListarConsultasBinding? = null
    private val binding get() = _binding!!
    private val BASE_URL = "https://tdsr-d6c6c-default-rtdb.firebaseio.com"
    private val cliente = OkHttpClient()
    private lateinit var listView: ListView
    private val clientesList = ArrayList<String>()
    private val clientesMap = mutableMapOf<String, JSONObject>() // Guardar os dados completos

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityListarConsultasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView = binding.listViewClientes

        val request = Request.Builder()
            .url("$BASE_URL/contatos.json")
            .build()

        val response = object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                requireActivity().runOnUiThread {
                    Toast.makeText(
                        requireContext(),
                        "Erro ao carregar consultas: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                if (responseBody != null) {
                    try {
                        val jsonObject = JSONObject(responseBody)
                        clientesList.clear()
                        clientesMap.clear()

                        jsonObject.keys().forEach { key ->
                            val clienteJson = jsonObject.getJSONObject(key)
                            val nome = clienteJson.getString("nome")
                            val especialidade = clienteJson.getString("especialidade")
                            val cpf = clienteJson.getString("cpf")
                            var dt_consulta = clienteJson.getString("dt_consulta")

                            if (dt_consulta.length <= 10 && isValidDate(dt_consulta)) {
                                dt_consulta = formatarData(dt_consulta)
                            } else {
                                dt_consulta = "Data Inválida"
                            }
                            val clienteInfo = """
                                Nome: $nome
                                Especialidade: $especialidade
                                CPF: $cpf
                                Data da Consulta: $dt_consulta
                            """.trimIndent()

                            clientesList.add(clienteInfo)
                            clientesMap[key] = clienteJson // Armazena os dados para usar na edição
                        }

                        requireActivity().runOnUiThread {
                            val adapter = ArrayAdapter(
                                requireContext(),
                                android.R.layout.simple_list_item_1,
                                clientesList
                            )
                            listView.adapter = adapter
                        }
                    } catch (e: JSONException) {
                        requireActivity().runOnUiThread {
                            Toast.makeText(
                                requireContext(),
                                "Erro ao processar os dados: ${e.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }
        cliente.newCall(request).enqueue(response)

        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = clientesList[position]
            val selectedKey = clientesMap.keys.elementAt(position) // Obtemos a chave do Firebase
            val bundle = Bundle().apply {
                putString("id", selectedKey) // Enviar a chave do item para edição
                putString("dadosConsulta", clientesMap[selectedKey].toString()) // Enviar os dados do JSON
            }
            findNavController().navigate(R.id.action_PaginaListar_to_PaginaEditarConsulta, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Função para formatar a data
    private fun formatarData(data: String): String {
        return try {
            val inputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val date = inputFormat.parse(data)
            outputFormat.format(date!!)
        } catch (e: Exception) {
            "Data Inválida"
        }
    }

    // Função para verificar se a data é válida
    private fun isValidDate(data: String): Boolean {
        return try {
            val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            format.isLenient = false
            format.parse(data) != null
        } catch (e: Exception) {
            false
        }
    }
}