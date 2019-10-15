package com.monika.jethani.yasma.posts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.monika.jethani.yasma.posts.repo.PostsVMRepository

class PostsVMFactory(private val repo: PostsVMRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostsViewModel(repo) as T
    }
}