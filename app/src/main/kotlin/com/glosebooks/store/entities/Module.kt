package com.glosebooks.store.entities

import com.google.gson.annotations.Expose

/**
 * Created by Julien on 09/01/2018.
 */
data class Module(@Expose val type: String = "",
                  @Expose val books: List<Book> = arrayListOf())