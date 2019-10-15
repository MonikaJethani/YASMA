package com.monika.jethani.yasma.posts.repo

import com.monika.jethani.yasma.posts.api.PostsApi
import com.monika.jethani.yasma.posts.models.Post
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostsVMRepository(val postApi: PostsApi) {

    fun fetchPosts(): Observable<List<Post>> {
        return Observable.create { emitter ->

            postApi.getPosts()
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