package com.monika.jethani.yasma.comments.repo

import com.monika.jethani.yasma.comments.api.CommentsApi
import com.monika.jethani.yasma.comments.models.Comment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CommentsVMRepository(val commentApi: CommentsApi) {

    fun fetchComments(id: String): Observable<List<Comment>> {
        return Observable.create { emitter ->

            commentApi.getComments(id)
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