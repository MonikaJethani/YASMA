package com.monika.jethani.yasma.albums.repo

import com.monika.jethani.yasma.albums.api.AlbumsApi
import com.monika.jethani.yasma.albums.models.Album
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AlbumsVMRepository(val albumsApi: AlbumsApi) {

    fun fetchAlbums(): Observable<List<Album>> {
        return Observable.create { emitter ->

            albumsApi.getAlbum()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.body() != null) {
                        emitter.onNext(it.body()!!)
                    }
                }, {
                    it.printStackTrace()
                })

        }
    }
}