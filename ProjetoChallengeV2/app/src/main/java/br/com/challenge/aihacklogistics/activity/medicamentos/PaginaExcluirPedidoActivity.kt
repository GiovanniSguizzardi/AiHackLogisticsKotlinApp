package br.com.challenge.aihacklogistics.activity.medicamentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import br.com.challenge.aihacklogistics.R
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class PaginaExcluirPedidoActivity : Fragment() {

    private lateinit var listView: ListView
    private val pedidosList = ArrayList<Pedido>()
    private val BASE_URL = "https://tdsr-d6c6c-default-rtdb.firebaseio.com"
    private val cliente = OkHttpClient()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_listar_pedidos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView = view.findViewById(R.id.listViewPedidosExcluir)

        carregarPedidos()
    }

    private fun carregarPedidos() {
        val request = Request.Builder()
            .url("$BASE_URL/pedidos.json")
            .build()

        val response = object : Callback {
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
                        jsonObject.keys().forEach { key ->
                            val nome = jsonObject.getJSONObject(key).getString("nome")
                            val medicamento = jsonObject.getJSONObject(key).getString("medicamento")
                            val quantidade = jsonObject.getJSONObject(key).getString("quantidade")
                            val cpf = jsonObject.getJSONObject(key).getString("cpf")

                            val pedido = Pedido(key, nome, medicamento, quantidade, cpf)
                            pedidosList.add(pedido)
                        }

                        requireActivity().runOnUiThread {
                            val adapter = PedidoAdapter()
                            listView.adapter = adapter
                        }
                    } catch (e: Exception) {
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
    }

    data class Pedido(val id: String, val nome: String, val medicamento: String, val quantidade: String, val cpf: String)

    inner class PedidoAdapter : ArrayAdapter<Pedido>(requireContext(), android.R.layout.simple_list_item_2, android.R.id.text1, pedidosList) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val inflater = LayoutInflater.from(context)
            val view = convertView ?: inflater.inflate(android.R.layout.simple_list_item_2, parent, false)

            val pedido = getItem(position)
            val linha01 = view.findViewById<TextView>(android.R.id.text1)
            val linha02 = view.findViewById<TextView>(android.R.id.text2)

            linha01.text = "Medicamento: ${pedido?.medicamento}"
            linha02.text = "Quantidade: ${pedido?.quantidade}"

            view.setOnClickListener {
                mostrarConfirmacaoExclusao(pedido)
            }

            return view
        }

        private fun mostrarConfirmacaoExclusao(pedido: Pedido?) {
            pedido?.let {
                val dialogBuilder = AlertDialog.Builder(requireContext())
                dialogBuilder.setMessage("Tem certeza que deseja excluir o pedido de ${pedido.medicamento} solicitado por ${pedido.nome}?")
                    .setCancelable(false)
                    .setPositiveButton("Sim") { _, _ ->
                        excluirPedido(pedido.id)
                    }
                    .setNegativeButton("Não") { dialog, _ ->
                        dialog.dismiss()
                    }

                val alert = dialogBuilder.create()
                alert.setTitle("Confirmar Exclusão")
                alert.show()
            }
        }

        private fun excluirPedido(pedidoId: String) {
            val request = Request.Builder()
                .url("$BASE_URL/pedidos/$pedidoId.json")
                .delete()
                .build()

            cliente.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    requireActivity().runOnUiThread {
                        Toast.makeText(requireContext(), "Erro ao excluir pedido: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    requireActivity().runOnUiThread {
                        Toast.makeText(requireContext(), "Pedido excluído com sucesso!", Toast.LENGTH_LONG).show()
                        pedidosList.removeAll { pedido -> pedido.id == pedidoId }
                        notifyDataSetChanged()
                    }
                }
            })
        }
    }
}