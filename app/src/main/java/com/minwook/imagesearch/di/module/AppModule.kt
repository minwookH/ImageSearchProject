package com.minwook.imagesearch.di.module

import com.minwook.imagesearch.present.ImageListContract
import com.minwook.imagesearch.present.ImageListPresentImpl
import com.minwook.imagesearch.ui.activity.MainActivity
import org.koin.dsl.module


val appModule = module {

    single<ImageListContract.Present> { (view: MainActivity) ->  ImageListPresentImpl(view) }
}