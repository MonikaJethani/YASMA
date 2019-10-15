package com.monika.jethani.yasma.photos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.monika.jethani.yasma.photos.repo.PhotosVMRepository

class PhotosVMFactory(private val repo: PhotosVMRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotosViewModel(repo) as T
    }
}