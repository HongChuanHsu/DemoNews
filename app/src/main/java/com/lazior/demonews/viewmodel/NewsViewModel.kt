package com.lazior.demonews.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lazior.demonews.model.MyResponse
import com.lazior.demonews.model.News
import com.lazior.demonews.repository.NewsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    val newsList = MutableLiveData<List<News>>()

    fun getAllNews() {
        repository.getAllNews().enqueue(object : Callback<MyResponse> {
            override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                newsList.postValue(response.body()?.articles)
            }

            override fun onFailure(call: Call<MyResponse>, t: Throwable) {

            }
        })
    }
}