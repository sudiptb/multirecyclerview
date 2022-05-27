package com.example.multirecyclerview

data class User(
    val id: Int,
    val name: String,
    var productList: List<Product> = emptyList()
)

data class Product(
    val image: Int,
    val productName: String
)

fun getList() = listOf(
    User(1, "Sudipto", listOf(
        Product(R.drawable.musicknob, "Books1"),
        Product(R.drawable.musicknob, "Books2"),
        Product(R.drawable.musicknob, "Books3"),
        Product(R.drawable.musicknob, "Books4"),
    )),
    User(2, "Sudipto1", listOf(
        Product(R.drawable.musicknob, "Books1"),
        Product(R.drawable.musicknob, "Books2"),
    ))
)
