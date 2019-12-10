package vn.ekingdom.mobile.icafeemployee.model

import java.util.*

data class Order(
    var orderId: String? = "",
    var orderCode: String? = "",
    var orderDate: Date? = null,
    var storeId: String? = "",
    var customerId: Int? = 0,
    var workStationId: Int? = 0,
    var customerFullName: String? = "",
    var employeeId: Int? = 0
)