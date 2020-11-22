package com.example.shoplist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoplist.utils.CheckIfWorking
import com.example.shoplist.databinding.LayoutShopItemBinding
import com.example.shoplist.model.Shop

/**
 * Created by sopovardidze
 */
class ShopAdapter(
        private val items : List<Shop>,
        private val context : Context
) : RecyclerView.Adapter<ShopAdapter.ShopViewHolder>() {

    inner class ShopViewHolder(var binding: LayoutShopItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val binding = LayoutShopItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShopViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        val curItem = items[position]
        holder.binding.apply {
            Glide.with(context).load(curItem.backgroundUrl).into(shopMainImage)
            Glide.with(context).load(curItem.logoUrl).into(shopIcon)
            shopNameText.text = curItem.name
            if(!CheckIfWorking.checkWorkingHours(curItem.workingHours)){
                transparentItemsContainer.visibility = View.VISIBLE
                nextWorkingDay.text = CheckIfWorking.checkNextWorkingDay(curItem.workingHours)
            }
        }
    }
}