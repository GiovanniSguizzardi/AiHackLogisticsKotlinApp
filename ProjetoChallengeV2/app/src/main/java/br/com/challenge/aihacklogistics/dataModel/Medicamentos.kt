package br.com.challenge.aihacklogistics.dataModel

import org.json.JSONObject

data class Medicamentos (
    val nome: String,
    val medicamentos: String,
    val quantidade: Int,
    val cpf: String,
) {
    fun toJson(): String {
        return JSONObject().apply {
            put("nome", nome)
            put("medicamentos", medicamentos)
            put("quantidade", quantidade)
            put("cpf", cpf)
        }.toString()
    }
}