package com.glosebooks.store

import com.github.andrewoma.dexx.kollection.ImmutableList
import com.github.andrewoma.dexx.kollection.immutableListOf
import com.glosebooks.store.entities.Book

/**
 * Created by Julien on 09/01/2018.
 * ApplicationState data class. This app only contains an immutable list of books
 */
data class ApplicationState(val books: ImmutableList<Book> = immutableListOf())
