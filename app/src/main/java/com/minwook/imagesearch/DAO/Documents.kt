package com.minwook.imagesearch.DAO

import com.google.gson.annotations.SerializedName

class Documents {

    @SerializedName("collection")
    var collection: String? = null
    @SerializedName("thumbnail_url")
    var thumbnail_url: String? = null
    @SerializedName("image_url")
    var image_url: String? = null
    @SerializedName("width")
    var width: Int? = null
    @SerializedName("height")
    var height: Int? = null
    @SerializedName("display_sitename")
    var display_sitename: String? = null
    @SerializedName("doc_url")
    var doc_url: String? = null
    @SerializedName("datetime")
    var datetime: String? = null
}