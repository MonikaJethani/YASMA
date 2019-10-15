package com.monika.jethani.yasma.albums.api

import com.monika.jethani.yasma.albums.models.Album
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface AlbumsApi {

    @GET("/albums")
    fun getAlbum(): Single<Response<List<Album>>>
}