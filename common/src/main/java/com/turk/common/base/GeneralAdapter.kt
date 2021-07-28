package com.turk.common.base

import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.turk.common.extension.dataBind


/**
 * Adapter which can be used for all list types
 * @param variableId Provide a variable which is used to populate the item defined in xml layout like this
 *   <variable
 *      name="variableName"
 *      type="VariableType" />
 *
 * @param layoutResource  id of the layout item
 * @param diffCallback provide a diffUtil implementation of the DTO used to populate this item.
 * @param listOfClickableIds if the item has multiple click listeners implemented you have to provide
 * the ids of all the clickable views
 */

class GeneralAdapter<T>(
        private val variableId: Int,
        private val layoutResource: Int,
        diffCallback: DiffUtil.ItemCallback<T>,
        private val listOfClickableIds: List<Int> = mutableListOf()

) :
        ListAdapter<T, GeneralAdapter<T>.ViewHolder>(diffCallback) {

    /**
     * THis is a generic item click listener used to implement the click of adapter items
     */
    var clickListener: (T, View) -> Unit = { _, _ -> }

    /**
     * THis is a specific item views click listener. used to implement multiple clicks in an item
     */
    var clickListenerSpecific: (T, View) -> Unit = { _, _ -> }

    /**
     * This is used for setting the value of the view which are bind to the item. It will provide
     * the bind view back to the invoke location
     */
    var customBindings: (T, bindView: ViewDataBinding) -> Unit = { _, _ -> }

    inner class ViewHolder(private val itemBinding: ViewDataBinding) :
            RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {
        init {
            itemBinding.root.setOnClickListener(this)
            if (listOfClickableIds.isNotEmpty())
                listOfClickableIds.map { id ->
                    itemBinding.root.findViewById<View>(id).setOnClickListener(this)
                }

        }

        fun bind(item: T) {
            customBindings(item, itemBinding)
            itemBinding.setVariable(variableId, item)
            itemBinding.executePendingBindings()
        }

        override fun onClick(v: View?) {
            (getItem(layoutPosition)).run {
                if (listOfClickableIds.isEmpty())
                    clickListener(this, v!!)
                else
                    clickListenerSpecific(this, v!!)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(parent.dataBind(layoutResource))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(getItem(position))

}

