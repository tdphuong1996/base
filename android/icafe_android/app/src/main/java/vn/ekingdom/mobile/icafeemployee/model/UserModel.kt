package vn.ekingdom.mobile.icafeemployee.model

data class UserModel(
    var userId: Int = 0,
    var userGroupId: Int = 0,
    var userName: String = "Unknown",
    var fullName: String = "Unknown",
    var email: String = "Unknown",
    var userNumber: String = "Unknown",
    var password: String = "Unknown",
    var storeId: String = "Unknown",
    var phoneNumber: String = "Unknown",
    var nickName: String = "Unknown",
    var avatar: String = "Unknown",
    var birthday: String = "Unknown",
    var gender: Int = 0,
    var provinceId: String = "Unknown",
    var distristId: String = "Unknown",
    var address: String = "Unknown"
) {
}