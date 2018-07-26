package br.com.mblabs.desafio.data.remote.model.network

import br.com.mblabs.desafio.data.remote.model.entity.item
import br.com.mblabs.desafio.data.remote.model.entity.itemList
import br.com.mblabs.desafio.util.Constants
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.Endpoint.LOADITENS)
    fun loadMenu(): Observable<item>

    companion object Factory {

        fun create(): ApiService {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.Endpoint.BASE_URL)
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }

}
