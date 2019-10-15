package com.monika.jethani.yasma

import com.monika.jethani.yasma.albums.api.AlbumsApi
import com.monika.jethani.yasma.albums.repo.AlbumsVMRepository
import com.monika.jethani.yasma.comments.api.CommentsApi
import com.monika.jethani.yasma.comments.repo.CommentsVMRepository
import com.monika.jethani.yasma.photos.api.PhotosApi
import com.monika.jethani.yasma.photos.repo.PhotosVMRepository
import com.monika.jethani.yasma.posts.api.PostsApi
import com.monika.jethani.yasma.posts.repo.PostsVMRepository

object RepositoryFactory {

    fun createPostRepository(): PostsVMRepository {
        val postApi = RestUtil.instance.retrofit.create(PostsApi::class.java)
        return PostsVMRepository(postApi)
    }


    fun createAlbumRepository(): AlbumsVMRepository {
        val albumsApi = RestUtil.instance.retrofit.create(AlbumsApi::class.java)
        return AlbumsVMRepository(albumsApi)
    }

    fun createCommentsRepository(): CommentsVMRepository {
        val commentsApi = RestUtil.instance.retrofit.create(CommentsApi::class.java)
        return CommentsVMRepository(commentsApi)
    }

    fun createPhotosRepository(): PhotosVMRepository {
        val photosApi = RestUtil.instance.retrofit.create(PhotosApi::class.java)
        return PhotosVMRepository(photosApi)
    }

}