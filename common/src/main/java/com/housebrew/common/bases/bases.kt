package com.housebrew.common.bases

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.multidex.MultiDexApplication
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.facebook.drawee.backends.pipeline.Fresco
import com.housebrew.common.di.KodeinViewModelInjector
import com.housebrew.common.di.diModule
import io.reactivex.disposables.CompositeDisposable
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein

open class BaseActivity : AppCompatActivity(), KodeinAware {
    override val kodein: Kodein by closestKodein()
    protected val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        KodeinViewModelInjector.setContainerProvider { kodein }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

abstract class BaseViewModel : ViewModel(), LifecycleObserver {
    val compositeDisposable by lazy { CompositeDisposable() }
}

open class BaseFragment : Fragment(), KodeinAware {
    override val kodein: Kodein by closestKodein()
    val compositeDisposable by lazy { CompositeDisposable() }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}

open class BaseContext : MultiDexApplication(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(diModule(this@BaseContext))
    }

    override fun onCreate() {
        super.onCreate()
        KodeinViewModelInjector.setContainerProvider { kodein }
    }
}
