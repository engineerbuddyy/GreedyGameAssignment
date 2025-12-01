package com.example.greedygamequoteappassignment.data

data class QuoteModel(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val text:String,  //Added text by my own , because it was missing
    val downloadUrl: String,
    val imageUrl: String
)

object QuoteData {
    fun getQuotes(): List<QuoteModel> = listOf(
        QuoteModel(
            id = "0",
            author = "Alejandro Escamilla",
            width = 5000,
            height = 3333,
            text = "I want my country to be one where people are not limited, so that they can pursue their own dreams, interests, and ambitions without worrying about the inequalities of life and the opinions of others.",
            downloadUrl = "https://picsum.photos/id/0/5000/3333",
            imageUrl = "https://unsplash.com/photos/yC-Yzbqy7PY"
        ),
        QuoteModel(
            id = "1",
            author = "Alejandro Escamilla",
            width = 5000,
            height = 3333,
            text = "I want my country to be one where people are not limited, so that they can pursue their own dreams, interests, and ambitions without worrying about the inequalities of life and the opinions of others.",
            downloadUrl = "https://picsum.photos/id/1/5000/3333",
            imageUrl = "https://unsplash.com/photos/LNRyGwIJr5c"
        ),
        QuoteModel(
            id = "2",
            author = "Alejandro Escamilla",
            width = 5000,
            height = 3333,
            text = "I want my country to be one where people are not limited, so that they can pursue their own dreams, interests, and ambitions without worrying about the inequalities of life and the opinions of others.",
            downloadUrl = "https://picsum.photos/id/2/5000/3333",
            imageUrl = "https://unsplash.com/photos/N7XodRrbzS0"
        ),
        QuoteModel(
            id = "3",
            author = "Alejandro Escamilla",
            width = 5000,
            height = 3333,
            text = "I want my country to be one where people are not limited, so that they can pursue their own dreams, interests, and ambitions without worrying about the inequalities of life and the opinions of others.",
            downloadUrl = "https://picsum.photos/id/3/5000/3333",
            imageUrl = "https://unsplash.com/photos/Dl6jeyfihLk"
        ),
        QuoteModel(
            id = "4",
            author = "Alejandro Escamilla",
            width = 5000,
            height = 3333,
            text = "I want my country to be one where people are not limited, so that they can pursue their own dreams, interests, and ambitions without worrying about the inequalities of life and the opinions of others.",
            downloadUrl = "https://picsum.photos/id/4/5000/3333",
            imageUrl = "https://unsplash.com/photos/y83Je1OC6Wc"
        ),
        QuoteModel(
            id = "5",
            author = "Alejandro Escamilla",
            width = 5000,
            height = 3334,
            text = "I want my country to be one where people are not limited, so that they can pursue their own dreams, interests, and ambitions without worrying about the inequalities of life and the opinions of others.",
            downloadUrl = "https://picsum.photos/id/5/5000/3334",
            imageUrl = "https://unsplash.com/photos/LF8gK8-HGSg"
        ),
        QuoteModel(
            id = "6",
            author = "Alejandro Escamilla",
            width = 5000,
            height = 3333,
            text = "I want my country to be one where people are not limited, so that they can pursue their own dreams, interests, and ambitions without worrying about the inequalities of life and the opinions of others.",
            downloadUrl = "https://picsum.photos/id/6/5000/3333",
            imageUrl = "https://unsplash.com/photos/tAKXap853rY"
        ),
        QuoteModel(
            id = "7",
            author = "Alejandro Escamilla",
            width = 4728,
            height = 3168,
            text = "I want my country to be one where people are not limited, so that they can pursue their own dreams, interests, and ambitions without worrying about the inequalities of life and the opinions of others.",
            downloadUrl = "https://picsum.photos/id/7/4728/3168",
            imageUrl = "https://unsplash.com/photos/BbQLHCpVUqA"
        ),
        QuoteModel(
            id = "8",
            author = "Alejandro Escamilla",
            width = 5000,
            height = 3333,text = "I want my country to be one where people are not limited, so that they can pursue their own dreams, interests, and ambitions without worrying about the inequalities of life and the opinions of others.",
            downloadUrl = "https://picsum.photos/id/8/5000/3333",
            imageUrl = "https://unsplash.com/photos/xII7efH1G6o"
        ),
        QuoteModel(
            id = "9",
            author = "Alejandro Escamilla",
            width = 5000,
            height = 3269,
            text = "I want my country to be one where people are not limited, so that they can pursue their own dreams, interests, and ambitions without worrying about the inequalities of life and the opinions of others.",
            downloadUrl = "https://picsum.photos/id/9/5000/3269",
            imageUrl = "https://unsplash.com/photos/ABDTiLqDhJA"
        )
    )
}
