package com.glosebooks

import com.brianegan.bansa.BaseStore
import com.glosebooks.dispatcher.INIT
import com.glosebooks.dispatcher.LOAD_FINISHED
import com.glosebooks.dispatcher.applicationReducer
import com.glosebooks.store.Application
import com.glosebooks.store.ApplicationState
import com.glosebooks.store.entities.Book
import com.glosebooks.store.service.Service
import com.glosebooks.store.store
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTests {
    @Test
    fun applicationInit() {
        val application = Application()
        store.dispatch(INIT())
        assertTrue(store.state.books.isEmpty())
    }

    @Test
    fun getStore(){
        val testStore = BaseStore(ApplicationState(), applicationReducer)
        assertNotNull(testStore)
    }

    @Test
    fun addBook(){
        var books : List<Book> = arrayListOf();
        books = books.plus(Book(id = "Test", title = "Test"))
        store.dispatch(LOAD_FINISHED(books))
        assertEquals(1, store.state.books.size)
    }

    @Test
    fun addMultipleBook(){
        var books : List<Book> = arrayListOf();
        books = books.plus(Book(id = "Test1", title = "Test1"))
        books = books.plus(Book(id = "Test2", title = "Test2"))
        books = books.plus(Book(id = "Test3", title = "Test3"))
        store.dispatch(LOAD_FINISHED(books))
        assertEquals(3, store.state.books.size)
    }

    @Test
    fun serviceReturnBooks() {
        val api = Service()
        val books = api.getBooks()?.execute()

        assertNotNull(books)
        assertNotNull(books?.body())
        assertNotNull(books?.body()?.modules)
        assertNotNull(books?.body()?.modules?.get(1))
        assertNotNull(books?.body()?.modules?.get(1)?.books)
    }
}
