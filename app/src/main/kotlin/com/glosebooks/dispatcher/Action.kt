package com.glosebooks.dispatcher

import com.brianegan.bansa.Action
import com.glosebooks.store.ApplicationState
import com.glosebooks.store.entities.Book

/**
 * Created by Julien on 09/01/2018.
 */
data class INIT(val state: ApplicationState = ApplicationState()) : Action
data class LOADING(val info: String) : Action
data class LOAD_FINISHED(val books: List<Book>) : Action
data class BOOK_CLICKED(val id: String) : Action