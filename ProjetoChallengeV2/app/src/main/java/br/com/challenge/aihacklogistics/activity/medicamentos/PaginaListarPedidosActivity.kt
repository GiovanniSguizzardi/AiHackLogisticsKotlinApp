package br.com.challenge.aihacklogistics.activity.medicamentos

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
import br.com.challenge.aihacklogistics.databinding.ActivityListarMedicamentosBinding
import okhttp3.*
import org.json.JSONObject
import org.json.JSONException
import java.io.IOException

class PaginaListarPedidosActivity : Fragment() {

    private var _binding: ActivityListarMedicamentosBinding? = null
    private val binding get() = _binding!!
    private val BASE_URL = "https://tdsr-d6c6c-default-rtdb.firebaseio.com"
    private val cliente = OkHttpClient()
    private lateinit var listView: ListView
    private val pedidosList = ArrayList<String>()
    private val pedidosMap = mutableMapOf<String, JSONObject>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityListarMedicamentosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView = binding.listViewPedidos

        val request = Request.Builder()
            .url("$BASE_URL/pedidos.json")
            .build()

        cliente.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                requireActivity().runOnUiThread {
                    Toast.makeText(
                        requireContext(),
                        "Erro ao carregar pedidos: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                if (responseBody != null) {
                    try {
                        val jsonObject = JSONObject(responseBody)
                        pedidosList.clear()
                        pedidosMap.clear()

                        jsonObject.keys().forEach { key ->
                            val pedidoJson = jsonObject.getJSONObject(key)
                            val nome = pedidoJson.getString("nome")
                            val medicamento = pedidoJson.getString("medicamento")
                            val quantidade = pedidoJson.getString("quantidade")
                            val cpf = pedidoJson.getString("cpf")

                            val pedidoInfo = """
                                Nome: $nome
                                Medicamento: $medicamento
                                Quantidade: $quantidade
                                CPF: $cpf
                            """.trimIndent()

                            pedidosList.add(pedidoInfo)
                            pedidosMap[key] = pedidoJson
                        }

                        requireActivity().runOnUiThread {
                            val adapter = ArrayAdapter(
                                requireContext(),
                                android.R.layout.simple_list_item_1,
                                pedidosList
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
        })

        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedKey = pedidosMap.keys.elementAt(position)
            val bundle = Bundle().apply {
                putString("id", selectedKey)
                putString("dadosPedido", pedidosMap[selectedKey].toString())
            }
            findNavController().navigate(R.id.action_PaginaListarPedidos_to_PaginaEditarPedido, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
