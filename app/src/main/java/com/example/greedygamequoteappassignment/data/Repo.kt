package com.example.greedygamequoteappassignment.data


class QuoteRepository {
    fun getQuotes(): List<QuoteModel> = QuoteData.getQuotes()
}
