package com.glosebooks.view

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import com.brianegan.bansa.Store
import com.glosebooks.store.ApplicationState
import com.glosebooks.view.adapter.RecyclerViewAdapter
import trikita.anvil.Anvil
import trikita.anvil.DSL
import trikita.anvil.RenderableView
import trikita.anvil.recyclerview.v7.RecyclerViewv7DSL
import java.util.*

/**
 * Created by Julien on 09/01/2018.
 */
class RootView(c: Context, val store: Store<ApplicationState>) : RenderableView(c) {
    val stateChangeSubscription = store.subscribe { Anvil.render() }
    val mapModelToViewModel = buildMapModelToViewModel(store)
    val adapter = RecyclerViewAdapter(
            mapModelToViewModel,
            ::bookView,
            { models, pos ->
                UUID.nameUUIDFromBytes(models[pos].id.toByteArray()).getMostSignificantBits();
            }, true
    )

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stateChangeSubscription.unsubscribe()
    }

    /** function that create view using anvil*/
    override fun view() {
        DSL.frameLayout {
            DSL.padding(DSL.dip(16), DSL.dip(16), DSL.dip(16), 0)

            RecyclerViewv7DSL.recyclerView {
                DSL.id(1)
                DSL.GROW
                DSL.init {
                    RecyclerViewv7DSL.layoutManager(GridLayoutManager(context, 2))
                    RecyclerViewv7DSL.itemAnimator(DefaultItemAnimator())
                    RecyclerViewv7DSL.hasFixedSize(false)
                    DSL.size(DSL.FILL, DSL.FILL)
                }

                RecyclerViewv7DSL.adapter(adapter.update(store.state.books))
            }
        }
    }
}
