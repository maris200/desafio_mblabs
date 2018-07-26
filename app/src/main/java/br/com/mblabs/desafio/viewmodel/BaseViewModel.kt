package br.com.mblabs.desafio.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.widget.EditText

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    val errorMessage = MutableLiveData<String>()

    fun getString(resId: Int): String {
        return getApplication<Application>().getString(resId)
    }

    companion object {
        @JvmStatic
        @BindingAdapter("app:errorText")
        fun setErrorMessage(view: EditText, errorMessage: String) {
            view.error = errorMessage
        }
    }
}
