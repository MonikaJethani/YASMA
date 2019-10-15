package com.monika.jethani.yasma.photos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.monika.jethani.yasma.photos.models.Photo
import com.monika.jethani.yasma.photos.repo.PhotosVMRepository

class PhotosViewModel(val photosRespository: PhotosVMRepository) : ViewModel() {

    private val photos: MutableLiveData<List<Photo>> = MutableLiveData()
    val allPhotos: LiveData<List<Photo>> = photos

    fun getPhotos(id: String) {
        photosRespository
            .fetchPhotos(id)
            .subscribe {
                photos.postValue(it)

            }
    }

}