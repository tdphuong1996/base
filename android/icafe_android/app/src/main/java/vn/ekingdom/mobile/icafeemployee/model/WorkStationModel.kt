package vn.ekingdom.mobile.icafeemployee.model

data class WorkStationModel (
    var workStationId: Int = 0,
    var workStationGroupId: String? = "",
    var storeId: String? = "",
    var workStationNo: String? = "",
    var workStationName: String? = "",
    var status: String? = ""
)

enum class WSStatus(val status: String) {
    A("A"),  U("U"), M("M"), E("E")
}