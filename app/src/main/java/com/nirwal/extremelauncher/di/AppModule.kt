package com.nirwal.extremelauncher.di

import com.nirwal.extremelauncher.ui.screen.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel{MainViewModel(androidContext())}
}
