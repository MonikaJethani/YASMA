package com.monika.jethani.yasma.posts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.monika.jethani.yasma.posts.models.Post
import com.monika.jethani.yasma.posts.repo.PostsVMRepository

class PostsViewModel(val postsRespository: PostsVMRepository) : ViewModel() {

    private val posts: MutableLiveData<List<Post>> = MutableLiveData()
    val allPosts: LiveData<List<Post>> = posts

    fun getPosts() {
        postsRespository
            .fetchPosts()
            .subscribe {
                posts.postValue(it)

            }
    }

}