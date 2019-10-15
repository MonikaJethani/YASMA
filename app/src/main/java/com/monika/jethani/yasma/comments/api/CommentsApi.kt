package com.monika.jethani.yasma.comments.api

import com.monika.jethani.yasma.comments.models.Comment
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentsApi {

    @GET("/posts/{postId}/comments")
    fun getComments(@Path("postId") postId: String): Single<Response<List<Comment>>>
}