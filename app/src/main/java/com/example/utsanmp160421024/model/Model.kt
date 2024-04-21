package com.example.utsanmp160421024.model

data class User(
    var id : String,
    var nameFirst : String,
    var nameLast : String,
    var username : String,
    var password : String,
    var pic: String,
)

data class Artikel(
    var id : String,
    var judul : String,
    var username : String,
    var tipe : String,
    var paragrafAbstract : String,
    var pic: String,
)

data class Paragraf(
    var id : String,
    var idArtikel : String,
    var isi : String,
)