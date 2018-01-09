package com.glosebooks.store

import com.brianegan.bansa.BaseStore
import com.glosebooks.dispatcher.INIT
import com.glosebooks.dispatcher.applicationReducer

/**
 * Created by Julien on 09/01/2018.
 */
class Application : android.app.Application() {
    override fun onCreate() {
        super.onCreate()

        store.dispatch(INIT()) // Initialize the store
    }
}

val store = BaseStore(ApplicationState(), applicationReducer)