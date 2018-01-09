package com.glosebooks.store.entities

import com.google.gson.annotations.Expose

/**
 * Created by Julien on 09/01/2018.
 */
data class Author(@Expose val slug: String = "",
                  @Expose val name: String = "")