package br.com.mblabs.desafio.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import br.com.mblabs.desafio.R
import br.com.mblabs.desafio.data.remote.model.entity.item
import br.com.mblabs.desafio.data.remote.model.entity.itemList
import br.com.mblabs.desafio.data.remote.model.network.ApiServiceProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(application: Application) : BaseViewModel(application) {

    val item = MutableLiveData<item>()

    fun loadData() {
        ApiServiceProvider.apiService
                .loadMenu()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->

                    item.value = response

                }, { error ->

                    Log.i("test_error", error.message.toString())
                    errorMessage.value = getApplication<Application>().getString(R.string.item_error_load)
                })

    }
}
