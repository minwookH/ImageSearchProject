package com.minwook.imagesearch.present

import com.minwook.imagesearch.DAO.Documents
import java.util.ArrayList

object ImageListContract {
    interface Present {
        fun loadListData(query: String, page: Int, size: Int)
        fun noticeText(text: String)
    }

    interface View {
        fun response(list: ArrayList<Documents>?, status: Int)
        fun showProgress()
        fun hideProgress()
        fun showNoticeText(text: String)
        fun hideNoticeText()
    }
}