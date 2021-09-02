package com.lazior.demonews.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lazior.demonews.retrofit.RetrofitService
import com.lazior.demonews.adapter.NewsAdapter
import com.lazior.demonews.databinding.ActivityMainBinding
import com.lazior.demonews.repository.NewsRepository
import com.lazior.demonews.viewmodel.NewsViewModel
import com.lazior.demonews.viewmodel.NewsViewModelFactory

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var _activityMainBinding: ActivityMainBinding
    private lateinit var newsViewModel: NewsViewModel
    private val retrofitService = RetrofitService.getInstance()
    private val adapter = NewsAdapter();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_activityMainBinding.root)

        // create viewModel
        newsViewModel = ViewModelProvider(this, NewsViewModelFactory(NewsRepository(retrofitService)))
            .get(NewsViewModel::class.java)

        // set adapter
        _activityMainBinding.mainRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        _activityMainBinding.mainRecycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        _activityMainBinding.mainRecycler.adapter = adapter

        // observe newsList
        newsViewModel.newsList.observe(this, Observer {
            adapter.setData(it)
        })

        // get all news
        newsViewModel.getAllNews()
    }
}