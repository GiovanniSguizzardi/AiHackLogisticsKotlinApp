package br.com.challenge.aihacklogistics.activity.agendamentos

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

class PaginaExcluirActivity : Fragment() {

    private lateinit var listView: ListView
    private val clientesList = ArrayList<Cliente>()
    private val BASE_URL = "https://tdsr-d6c6c-default-rtdb.firebaseio.com"
    private val cliente = OkHttpClient()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_listar_consultas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView = view.findViewById(R.id.listViewClientes)

        carregarClientes()
    }

    private fun carregarClientes() {
        // Faz a requisição para obter a lista de clientes
        val request = Request.Builder()
            .url("$BASE_URL/contatos.json")
            .build()

        val response = object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                requireActivity().runOnUiThread {
                    Toast.makeText(
                        requireContext(),
                        "Erro ao carregar consulta: ${e.message}",
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
                        jsonObject.keys().forEach { key ->
                            val nome = jsonObject.getJSONObject(key).getString("nome")
                            val especialidade = jsonObject.getJSONObject(key).getString("especialidade")
                            val cpf = jsonObject.getJSONObject(key).getString("cpf")
                            val dt_consulta = jsonObject.getJSONObject(key).getString("dt_consulta")

                            val cliente = Cliente(key, nome, especialidade, cpf, dt_consulta)
                            clientesList.add(cliente)
                        }

                        requireActivity().runOnUiThread {
                            val adapter = ClienteAdapter()
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

    // Data class para armazenar informações do cliente
    data class Cliente(val id: String, val nome: String, val especialidade: String, val cpf: String, val dt_consulta: String)

    // Adapter para o ListView
    inner class ClienteAdapter : ArrayAdapter<Cliente>(requireContext(), android.R.layout.simple_list_item_2, android.R.id.text1, clientesList) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val inflater = LayoutInflater.from(context)
            val view = convertView ?: inflater.inflate(android.R.layout.simple_list_item_2, parent, false)

            val cliente = getItem(position)
            // Linhas que vão aparecer na nossa View
            val linha01 = view.findViewById<TextView>(android.R.id.text1)
            val linha02 = view.findViewById<TextView>(android.R.id.text2)

            linha01.text = "Nome: ${cliente?.nome}"
            linha02.text = "Data da Consulta: ${cliente?.dt_consulta}"

            // Adicionando a funcionalidade de exclusão ao clicar no item
            view.setOnClickListener {
                mostrarConfirmacaoExclusao(cliente)
            }

            return view
        }

        // Função para mostrar o AlertDialog de confirmação
        private fun mostrarConfirmacaoExclusao(cliente: Cliente?) {
            cliente?.let {
                val dialogBuilder = AlertDialog.Builder(requireContext())
                dialogBuilder.setMessage("Tem certeza que deseja desmarcar a consulta de ${cliente.nome} marcada para o dia ${cliente.dt_consulta}?")
                    .setCancelable(false)
                    .setPositiveButton("Sim") { _, _ ->
                        excluirCliente(cliente.id)
                    }
                    .setNegativeButton("Não") { dialog, _ ->
                        dialog.dismiss()
                    }

                val alert = dialogBuilder.create()
                alert.setTitle("Confirmar Exclusão")
                alert.show()
            }
        }

        // Função para excluir cliente
        private fun excluirCliente(clienteId: String) {
            val request = Request.Builder()
                .url("$BASE_URL/contatos/$clienteId.json")
                .delete()
                .build()

            cliente.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    requireActivity().runOnUiThread {
                        Toast.makeText(requireContext(), "Erro ao desmarcar: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    requireActivity().runOnUiThread {
                        Toast.makeText(requireContext(), "Consulta desmarcada com sucesso!", Toast.LENGTH_LONG).show()
                        clientesList.removeAll { cliente -> cliente.id == clienteId }
                        notifyDataSetChanged()
                    }
                }
            })
        }
    }
}