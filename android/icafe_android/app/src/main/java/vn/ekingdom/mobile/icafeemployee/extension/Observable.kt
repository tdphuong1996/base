package vn.ekingdom.mobile.icafeemployee.extension

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

fun <T>Observable<T>.outOnMainThread (): Observable<T> {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun <T>Observable<Response<T>>.responseOutOnMain (): Observable<T> {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map{ it.body() }
}