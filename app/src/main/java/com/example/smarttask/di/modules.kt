package com.example.smarttask.di


import android.content.Context
import com.app.pravasiuttrakhandi.repository.RegisterRepository
import com.example.smarttask.app.MyApp
import com.example.smarttask.networking.provideApiProvider
import com.example.smarttask.networking.provideApiService
import com.example.smarttask.networking.provideHttpClient
import com.example.smarttask.networking.provideLoggingInterceptor
import com.example.smarttask.ui.InitialViewModel
import com.example.smarttask.utility.InAppUpdateClient

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkingModules = module {
    single {
        provideLoggingInterceptor()
    }
    single {
        provideHttpClient(get())
    }
    single {
        provideApiProvider(get())
    }
    single {
        provideApiService(get())
    }
}

fun provideAppContext(): Context {
    return MyApp.appContext!!
}

val inAppUpdateModules = module {
    provideAppContext()
    single {
        InAppUpdateClient(get())
    }
}

@ExperimentalCoroutinesApi
val registerRepositoryModule = module {
    single {
        RegisterRepository(get())
    }
}

@ExperimentalCoroutinesApi
val initialViewModelModule = module {
    viewModel {
        InitialViewModel(get())
    }
}

// inject adapters
val contactsAdaptersModule = module {
//    factory {
//        ContactsAdapter()
//    }
}


