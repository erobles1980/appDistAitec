package com.appdistaitec.classday.model

data class ResponseBody(
    val carrera:String,
    val periodo:String,
    val semestre:String,
    val curso:String,
    val nivel:Int,
    val paralelo:String
)

data class CedulaBody(
    val cedula:String
)
