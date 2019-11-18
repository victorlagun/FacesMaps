package com.example.victor.facesmaps.remote

import com.example.victor.facesmaps.BASE_URL
import com.example.victor.facesmaps.model.Page
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Remote {

    @GET("users")
    fun users(@Query("page") page: Int): Observable<Page>

    companion object Factory {
        fun create(): Remote {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(Remote::class.java)
        }
    }

}