package io.github.tonnyl.whatsnew.adapter

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import io.github.tonnyl.whatsnew.databinding.WhatsnewItemBinding
import io.github.tonnyl.whatsnew.item.WhatsNewItem

/**
 * Created by lizhaotailang on 30/11/2017.
 */
class WhatsNewItemAdapter(
    private val mData: List<WhatsNewItem>,
    private val mContext: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var titleColor: Int = Color.BLACK
    var contentColor: Int = Color.parseColor("#808080")
    var iconColor: Int = Color.BLACK

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position <= mData.size) {
            with((holder as ItemViewHolder).binding) {
                if (mData[position].imageRes != 0) {
                    AppCompatResources.getDrawable(mContext, mData[position].imageRes)?.let { drawable ->
                        DrawableCompat.setTint(drawable, iconColor)

                        itemTitleTextView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                    }
                }
                itemTitleTextView.gravity = Gravity.CENTER_VERTICAL
                itemTitleTextView.compoundDrawablePadding = 16
                itemTitleTextView.text = mData[position].title
                itemTitleTextView.setTextColor(titleColor)

                itemContentTextView.text = mData[position].content
                itemContentTextView.setTextColor(contentColor)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(WhatsnewItemBinding.inflate(LayoutInflater.from(mContext), parent, false))
    }

    override fun getItemCount(): Int = mData.size

    class ItemViewHolder(val binding: WhatsnewItemBinding) : RecyclerView.ViewHolder(binding.root)

}