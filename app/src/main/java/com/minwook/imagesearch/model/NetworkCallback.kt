package com.minwook.imagesearch.model

import com.minwook.imagesearch.DAO.Documents

interface NetworkCallback {
    fun getResponse(data: ArrayList<Documents>?, status:Int)
}