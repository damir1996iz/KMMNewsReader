package com.damikkg.kmmnewsapi.domain.models

enum class NewsTypes (val path:String)
{
    Any(""),
    All("all"),
    Business("business"),
    Sports("sports"),
    Politics("politics"),
    Sciense("science")
}