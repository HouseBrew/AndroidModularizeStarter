package com.housebrew.common.di

import android.arch.lifecycle.ViewModel
import com.housebrew.common.apis.NewsService
import com.housebrew.common.apis.getRetrofit
import com.housebrew.common.bases.BaseContext
import com.housebrew.common.extensions.AppSchedulerProvider
import com.housebrew.common.extensions.SchedulerProvider
import org.kodein.di.Kodein
import org.kodein.di.android.androidModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.eagerSingleton
import org.kodein.di.generic.singleton
import java.lang.ref.WeakReference

fun diModel(baseContext: BaseContext) = Kodein.Module {
    import(androidModule(baseContext))
    bind<SchedulerProvider>() with eagerSingleton { AppSchedulerProvider() }
    bind<NewsService>() with singleton { getRetrofit(baseContext).create(NewsService::class.java) }
}

object KodeinViewModelInjector {
    val container: Kodein
        get() = internalContainerProvider?.invoke()
            ?: throw IllegalStateException("Container provider not set yet")

    private var internalContainerProvider: (() -> Kodein)? = null

    fun setContainerProvider(containerProvider: () -> Kodein) {
        internalContainerProvider = containerProvider
    }

    fun <ViewModelT : ViewModel> getTestViewModel(type: Class<ViewModelT>): ViewModelT? =
        TestViewModelsHolder.getTestViewModel(type)

    fun <ViewModelT : ViewModel> overrideInjectionRuleForTesting(
        type: Class<ViewModelT>, viewModel: ViewModel
    ) = TestViewModelsHolder.overrideInjectionRuleForTesting(type, viewModel)

    fun <ViewModelT : ViewModel> clearInjectionRuleForTesting(type: Class<ViewModelT>) =
        TestViewModelsHolder.clearInjectionRuleForTesting(type)

    fun clearAllInjectionRulesForTesting() = TestViewModelsHolder.reset()
}

@Suppress("UNCHECKED_CAST")
internal object TestViewModelsHolder {
    private val VIEW_MODEL_NAME = typeName(ViewModel::class.java)

    private val testViewModels = mutableMapOf<String, WeakReference<ViewModel>>()

    fun <ViewModelT : ViewModel> getTestViewModel(type: Class<ViewModelT>): ViewModelT? =
        synchronized(testViewModels) {
            testViewModels[key(type)]?.get()?.let {
                @Suppress("UNCHECKED_CAST") it as ViewModelT
            }
        }

    fun <ViewModelT : ViewModel> overrideInjectionRuleForTesting(
        type: Class<ViewModelT>, viewModel: ViewModel
    ): Unit = synchronized(testViewModels) {
        testViewModels[key(type)] = WeakReference(viewModel)
    }

    fun <ViewModelT : ViewModel> clearInjectionRuleForTesting(
        type: Class<ViewModelT>
    ): Unit = synchronized(testViewModels) {
        testViewModels.remove(key(type))
    }

    fun reset() = testViewModels.clear()

    private fun <T> key(type: Class<T>): String =
        typeNamesForThisAndParents(type).last { !it.contains("mock", ignoreCase = true) }

    private fun <T> typeNamesForThisAndParents(source: Class<T>): List<String> {
        val parent = source.superclass
        return listOf(typeName(source)) + when {
            parent == null || typeName(parent) == VIEW_MODEL_NAME -> emptyList()
            else -> typeNamesForThisAndParents(parent)
        }
    }

    private fun <T> typeName(type: Class<T>): String = type.name
}