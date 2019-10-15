package com.monika.jethani.yasma.photos.repo

import com.monika.jethani.yasma.photos.api.PhotosApi
import com.monika.jethani.yasma.photos.models.Photo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PhotosVMRepository(val photoApi: PhotosApi) {

    fun fetchPhotos(albumId: String): Observable<List<Photo>> {
        return Observable.create { emitter ->

            photoApi.getPhotos(albumId)
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