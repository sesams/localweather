package com.ks.idustest.api.response

import com.google.gson.annotations.SerializedName

class LocationResponse {
    @SerializedName("title")
    var title: String? = null

    @SerializedName("location_type")
    var locationType: String? = null

    @SerializedName("woeid")
    var woeid: Int? = null

    @SerializedName("latt_long")
    var lattLong: String? = null
}