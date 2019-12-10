package vn.ekingdom.mobile.icafeemployee.data.repository.response

class BaseResponse<T> {
    var code: Int? = 0
    var message: String? = ""
    var data: T? = null
}