package com.monika.jethani.yasma.albums.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.monika.jethani.yasma.albums.repo.AlbumsVMRepository

class AlbumsVMFactory(private val repo: AlbumsVMRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AlbumsViewModel(repo) as T
    }
}