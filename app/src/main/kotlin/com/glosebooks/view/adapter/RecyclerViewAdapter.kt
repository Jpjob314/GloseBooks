package com.glosebooks.view.adapter

import android.support.v7.widget.RecyclerView
import trikita.anvil.RenderableRecyclerViewAdapter

/**
 * Created by Julien on 09/01/2018.
 * RecyclerViewAdapter using anvil that manage init, view, udates...
 */
class RecyclerViewAdapter<M, VM>(
        val mapModelToViewModel: (M) -> VM,
        val render: (VM) -> Unit,
        val getId: (List<M>, Int) -> Long = { m, i -> RecyclerView.NO_ID },
        val hasStableIds: Boolean = false
) : RenderableRecyclerViewAdapter() {
    var models: List<M> = listOf()

    init {
        setHasStableIds(hasStableIds)
    }

    /** getItemCount
     * @return the number of items
     */
    override fun getItemCount(): Int {
        return models.size
    }

    /** getItemId
     * @param position : the position of the item
     * @return a generated long id from json original id
     */
    override fun getItemId(position: Int): Long {
        return getId(models, position)
    }

    override fun view(holder: RecyclerView.ViewHolder) {
        render(mapModelToViewModel(models[holder.adapterPosition]))
    }

    fun update(newModels: List<M>): RecyclerViewAdapter<M, VM> {
        if (models.equals(newModels).not()) {
            this.models = newModels;
            notifyDataSetChanged()
        }

        return this
    }
}
