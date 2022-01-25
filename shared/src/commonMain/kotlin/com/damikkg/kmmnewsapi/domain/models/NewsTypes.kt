package com.damikkg.kmmnewsapi.domain.models

enum class NewsTypes (val path:String, val desc:String)
{
    Cached("","All cached news"),
    All("all", "Uncategorized news"),
    Business("business", "Business news"),
    Sports("sports", "Sport news"),
    Politics("politics", "Politic news"),
    Sciense("science", "Sciense news")
}