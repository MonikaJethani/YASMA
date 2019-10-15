package com.monika.jethani.yasma.comments.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.monika.jethani.yasma.comments.repo.CommentsVMRepository

class CommentsVMFactory(private val repo: CommentsVMRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CommentsViewModel(repo) as T
    }
}