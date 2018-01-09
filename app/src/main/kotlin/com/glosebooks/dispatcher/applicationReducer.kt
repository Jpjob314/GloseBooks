package com.glosebooks.dispatcher

import com.brianegan.bansa.Reducer
import com.github.andrewoma.dexx.kollection.toImmutableList
import com.glosebooks.store.ApplicationState

/**
 * Created by Julien on 09/01/2018.
 * Action events. Update the state of the app here according to each action
 */
val applicationReducer = Reducer<ApplicationState> { state, action ->
    when (action) {
        is INIT -> ApplicationState()
        is LOADING -> state
        is BOOK_CLICKED -> state
        is LOAD_FINISHED -> state.copy(
                books = action.books.toImmutableList())
        else -> state
    }
}