package com.minwook.imagesearch.DAO

import com.google.gson.annotations.SerializedName

class ImageSearchRes {
    @SerializedName("meta")
    var meta: Meta? = null
    @SerializedName("documents")
    var documents: ArrayList<Documents>? = null
}