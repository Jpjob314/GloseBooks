package com.glosebooks.store.entities

import com.google.gson.annotations.Expose

/**
 * Created by Julien on 09/01/2018.
 */
data class BookList(@Expose val id: String = "",
                    @Expose val title: String = "",
                    @Expose val slug: String = "",
                    @Expose val modules: List<Module> = arrayListOf())