package com.appdistaitec.classday.data

data class DistributivoResponse(
    val asignatura:String,
    val profesor:String,
    val aula:String,
    val latitud:String,
    val longitud:String,
    val idCarrera:Int,
    val idCurso:Int,
    val idCiclo:Int,
    val idJornada:Int
)
