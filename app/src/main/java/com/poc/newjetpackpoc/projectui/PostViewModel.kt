package com.poc.newjetpackpoc.projectui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poc.newjetpackpoc.networklayer.Repository
import com.poc.newjetpackpoc.networklayer.retrofit.model.RetrofitModels
import kotlinx.coroutines.launch

class PostViewModel(private val repository: Repository) : ViewModel() {
    private val _posts = MutableLiveData<RetrofitModels>();
    val post : LiveData<RetrofitModels> = _posts
    private val TAG = "PostViewModel"

    init {
        fetchPostData()
    }

    private fun fetchPostData() {
        viewModelScope.launch{
            try {

                val response = repository.getPostResponse();
                _posts.value = response.body()

            }catch (e: Exception){
                Log.d(TAG, e.toString())
            }

        }
    }


}
