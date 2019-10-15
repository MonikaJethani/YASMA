package com.monika.jethani.yasma.photos.api

import com.monika.jethani.yasma.photos.models.Photo
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PhotosApi {

    @GET("/albums/{albumId}/photos")
    fun getPhotos(@Path("albumId") albumId: String): Single<Response<List<Photo>>>
}