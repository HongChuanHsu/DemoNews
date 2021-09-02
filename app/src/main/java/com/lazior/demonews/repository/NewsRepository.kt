package com.lazior.demonews.repository

import com.lazior.demonews.retrofit.RetrofitService

class NewsRepository(private val retrofitService: RetrofitService) {
    fun getAllNews() = retrofitService.getAllNews("YOUR_API_KEY")
}