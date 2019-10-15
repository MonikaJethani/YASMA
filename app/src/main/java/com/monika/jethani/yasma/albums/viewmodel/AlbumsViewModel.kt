package com.monika.jethani.yasma.albums.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.monika.jethani.yasma.albums.models.Album
import com.monika.jethani.yasma.albums.repo.AlbumsVMRepository


class AlbumsViewModel(val albumsRespository: AlbumsVMRepository) : ViewModel() {

    private val albums: MutableLiveData<List<Album>> = MutableLiveData()
    val allAlbums: LiveData<List<Album>> = albums

    fun getAlbums() {
        albumsRespository
            .fetchAlbums()
            .subscribe {
                albums.postValue(it)

            }
    }

}