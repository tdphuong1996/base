package vn.ekingdom.mobile.icafeemployee.di.repository

import io.reactivex.Observable

class PostRepository (private val postApi: PostApi){

    fun test (): Observable<MutableList<Post>> {
        return postApi.getPosts().responseOutOnMain()
    }
}