package com.shaw.xsplash.di

import com.shaw.xsplash.model.repo.NewRepository
import com.shaw.xsplash.viewmodel.NewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created on 2019/6/5.
 * @author XCZ
 */
val viewModuleModule = module {
    viewModel { NewViewModel(get()) }
}

val remoteModule = module {

}

val localModule = module {

}

val repoModule = module {
    single { NewRepository() }
}

val appModule = listOf(viewModuleModule, remoteModule, localModule, repoModule)