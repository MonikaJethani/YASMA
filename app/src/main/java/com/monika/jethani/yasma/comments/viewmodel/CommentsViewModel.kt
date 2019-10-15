package com.monika.jethani.yasma.comments.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.monika.jethani.yasma.comments.models.Comment
import com.monika.jethani.yasma.comments.repo.CommentsVMRepository
import com.monika.jethani.yasma.posts.models.Post
import com.monika.jethani.yasma.posts.repo.PostsVMRepository

class CommentsViewModel(val commentsRespository: CommentsVMRepository) : ViewModel() {

    private val comments: MutableLiveData<List<Comment>> = MutableLiveData()
    val allComments: LiveData<List<Comment>> = comments

    fun getComments(id: String) {
        commentsRespository
            .fetchComments(id)
            .subscribe {
                comments.postValue(it)

            }
    }

}