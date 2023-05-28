package com.yusril.pitjarustest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.calculateDiff
import androidx.recyclerview.widget.RecyclerView
import com.yusril.pitjarustest.R
import com.yusril.pitjarustest.data.model.Stores
import com.yusril.pitjarustest.databinding.ItemListStoreBinding

class StoreAdapter : RecyclerView.Adapter<StoreAdapter.ViewHolder>() {

    private var stores : MutableList<Stores> = ArrayList()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListStoreBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
             stores[position].apply {
                 tvNamaStore.text = storeName
                 tvAddressStore.text = address
                 tvLatitude.text = latitude
                 tvLongitude.text = longitude
             }
        }
    }

    override fun getItemCount(): Int {
        return stores.size
    }

    inner class ViewHolder( val binding: ItemListStoreBinding) : RecyclerView.ViewHolder(binding.root) {

    }
    fun updateData(newStores: List<Stores>) {
         val diffCallBack = DiffUtilCallBackStore(stores,newStores)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        stores.clear()
        stores.addAll(newStores)
        diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }

    private class DiffUtilCallBackStore (
        private val oldList: List<Stores>,
        private val newList: List<Stores>) :DiffUtil.Callback(){
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

    }

}