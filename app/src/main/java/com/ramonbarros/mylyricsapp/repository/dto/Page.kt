package com.ramonbarros.mylyricsapp.repository.dto

data class Info(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String

)



data class Page(
    val info: Info,
    val results: List<CharacterDTO>

)