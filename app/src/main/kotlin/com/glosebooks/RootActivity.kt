package com.glosebooks

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.glosebooks.dispatcher.LOADING
import com.glosebooks.dispatcher.LOAD_FINISHED
import com.glosebooks.store.service.Service
import com.glosebooks.store.store
import com.glosebooks.view.RootView
import java.io.IOException

/**
 * Created by Julien on 09/01/2018.
 */
open class RootActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(RootView(this, store))

        val api = Service()
        store.dispatch(LOADING("GetFreeBooks"))
        Thread(Runnable {
            try {
                val books = api.getBooks()?.execute()
                store.dispatch(LOAD_FINISHED(books?.body()?.modules?.get(1)?.books!!))
            } catch (e: IOException) {
                store.dispatch(LOAD_FINISHED(arrayListOf()))
            }
        }).start()
    }
}