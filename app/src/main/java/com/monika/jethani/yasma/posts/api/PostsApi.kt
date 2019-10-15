package com.monika.jethani.yasma.posts.api

import com.monika.jethani.yasma.posts.models.Post
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET


interface PostsApi {

    @GET("/posts")
    fun getPosts(): Single<Response<List<Post>>>
}