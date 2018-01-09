package com.glosebooks.store.entities

import com.google.gson.annotations.Expose

/**
 * Created by Julien on 09/01/2018.
 */
data class Book(@Expose val id: String = "",
                @Expose val title: String = "",
                @Expose val slug: String = "",
                @Expose val authors: List<Author> = arrayListOf(),
                @Expose val document: String = "",
                @Expose val image: String = "",
                @Expose val largeImage: String = "",
                @Expose val mediumImage: String = "",
                @Expose val shortTitle: String = "")