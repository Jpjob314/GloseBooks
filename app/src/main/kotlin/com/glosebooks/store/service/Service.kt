package com.glosebooks.store.service

import com.glosebooks.store.entities.BookList
import com.glosebooks.store.service.`interface`.IService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Julien on 09/01/2018.
 */
class Service {
    var gloseService: IService?

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.glose.com/v1/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        gloseService = retrofit.create(IService::class.java)
    }

    /**function to get book list**/
    fun getBooks(): Call<BookList>? {
        return gloseService?.getBookLists()
    }
}