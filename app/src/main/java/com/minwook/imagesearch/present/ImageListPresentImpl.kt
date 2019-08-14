package com.minwook.mytriphistory.kotlin.present

import android.util.Log
import com.minwook.imagesearch.DAO.Documents
import com.minwook.imagesearch.model.NetworkCallback
import com.minwook.mytriphistory.kotlin.model.ImageListModel

class ImageListPresentImpl(var view: ImageListPresent.View): ImageListPresent, NetworkCallback {
    val model: ImageListModel = ImageListModel(this)

    override fun loadListData(query: String, page: Int, size: Int) {
        view.showProgress()
        view.hideNoticeText()
        model.dataLoad(query, page, size)
    }

    override fun getResponse(data: ArrayList<Documents>?, status: Int) {
        view.hideProgress()
        view.response(data, status)
    }

    override fun noticeText(text: String) {
        view.showNoticeText(text)
    }
}