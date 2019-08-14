package com.minwook.imagesearch.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.minwook.imagesearch.DAO.Documents
import com.minwook.mytriphistory.kotlin.adapter.ImageListAdapter
import com.minwook.mytriphistory.kotlin.present.ImageListPresent
import com.minwook.mytriphistory.kotlin.present.ImageListPresentImpl
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import com.minwook.imagesearch.R


class MainActivity : AppCompatActivity(), ImageListPresent.View{

    private val imageList = arrayListOf<Documents>()
    private lateinit var contentsAdapter: ImageListAdapter
    private lateinit var present: ImageListPresentImpl
    var pageCount: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager = LinearLayoutManager(applicationContext)
        contentsAdapter = ImageListAdapter(this)

        image_list.layoutManager = layoutManager
        image_list.adapter = contentsAdapter
        image_list.addOnScrollListener(ListOnScrollListener())

        present = ImageListPresentImpl(this)
        present.noticeText("검색어를 입력하세요")

        search_view.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (TextUtils.isEmpty(search_view.getText().toString())){
                    contentsAdapter.clearList()
                    present.noticeText("검색어를 입력하세요")
                }else{
                    pageCount = 1
                    val text = search_view.getText().toString()
                    contentsAdapter.clearList()
                    present.loadListData(text, pageCount, 10)
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
    }

    override fun response(list: ArrayList<Documents>?, status: Int) {
        when(status) {
            200 -> {
                val itemPosition = imageList.size
                list?.let {
                    imageList.addAll(it)
                    contentsAdapter.addList(it)
                    contentsAdapter.notifyDataSetChanged()
                }
            }
            201 -> {
                present.noticeText("데이터가 없습니다")
            }
            400 -> {
                present.noticeText("네트워크 에러가 발생했습니다")
            }
            else -> {}

        }
    }


    inner class ListOnScrollListener: RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            Log.d("test", "ListOnScrollListener onScrollStateChanged start")
            super.onScrollStateChanged(recyclerView, newState)

            if(!image_list.canScrollVertically(1) && !TextUtils.isEmpty(search_view.getText().toString())){
                pageCount = pageCount + 1
                present.loadListData(search_view.getText().toString(), pageCount, 10)
            }
        }
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun hideNoticeText() {
        empty_text_view.visibility = View.GONE
        image_list.visibility = View.VISIBLE
    }

    override fun showNoticeText(text: String) {
        empty_text_view.text = text
        empty_text_view.visibility = View.VISIBLE
        image_list.visibility = View.GONE
    }
}
