package com.example.testcomposeapp.data.repository

import com.example.testcomposeapp.data.Forcast
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class HtmlParserImpl {

    private val url = "https://world-weather.ru/pogoda/russia/saint_petersburg/biometeorology/"

    fun getTodayForcast(): Forcast {
        val nodes = getDocument().getElementsByClass("biometric")

        val firstColumn = nodes.first()?.getElementsByClass("bio col-1")?.first()?.data() ?: ""
        val secondColumn = nodes.first()?.getElementsByClass("bio col-2")?.first()?.data()?.toInt() ?: 0
        val thirdColumn = nodes.first()?.getElementsByClass("bio col-3")?.first()?.data() ?: ""

        return Forcast(firstColumn, secondColumn, thirdColumn)
    }

    private fun getDocument(): Document {
        return Jsoup.connect(url).get()
    }
}