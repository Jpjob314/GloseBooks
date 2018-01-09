package com.glosebooks.store.service.`interface`

import com.glosebooks.store.entities.BookList
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Julien on 09/01/2018.
 */

interface IService {
    @GET("booklists/free-books?_version=20150601")
    fun getBookLists(): Call<BookList>
}