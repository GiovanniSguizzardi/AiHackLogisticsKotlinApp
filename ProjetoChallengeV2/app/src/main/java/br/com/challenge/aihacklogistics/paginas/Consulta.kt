package br.com.challenge.aihacklogistics

import org.json.JSONObject

data class Consulta(
    val id: String,
    val nome: String,
    val especialidade: String,
    val cpf: String,
    val dt_consulta: String
) {
    fun toJson(): String {
        return JSONObject().apply {
            put("id", id)
            put("nome", nome)
            put("especialidade", especialidade)
            put("cpf", cpf)
            put("dt_consulta", dt_consulta)
        }.toString()
    }
}