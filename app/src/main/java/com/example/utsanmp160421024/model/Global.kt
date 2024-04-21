package com.example.utsanmp160421024.model

object Global {
    var currentUser : User? = null

    var artikels: ArrayList<Artikel> = arrayListOf(
        Artikel("1", "Judul1", "Adi", "Makanan", "Batagor merupakan makanan yang enak", ""),
        Artikel("2", "Judul2", "Budi", "Peliharaan", "ANJING, BABI, DAN HEWAN PELIHARAAN LAINNYA", ""),
        Artikel("3", "Judul3", "Chalie", "Olahraga", "Basket tidak memperlukan bola", ""),
    )

    var paragrafs: ArrayList<Paragraf> = arrayListOf(
        Paragraf("1", "1", "Batagor bukan singkatan dari bata goreng"),
        Paragraf("2", "2", "Kucing perlu diberi makan"),
        Paragraf("3", "1", "Batagor juga bukan singkatan dari Ba**k Goreng"),
    )

    var users: ArrayList<User> = arrayListOf(
        User("1", "Adi", "Cahyadi", "1111", "1111", "url disini"),
        User("2", "Budi", "Berdiri", "2222", "2222", "url disini"),
        User("3", "Charlie", "Sulit", "3333", "3333", "url disini"),
    )
}