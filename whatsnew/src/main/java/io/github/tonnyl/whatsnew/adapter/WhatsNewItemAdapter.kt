package io.github.tonnyl.whatsnew.adapter

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import io.github.tonnyl.whatsnew.databinding.WhatsnewItemIosBinding
import io.github.tonnyl.whatsnew.databinding.WhatsnewItemBinding
import io.github.tonnyl.whatsnew.item.WhatsNewItem
import io.github.tonnyl.whatsnew.util.ItemLayoutOption


/**
 * Created by lizhaotailang on 30/11/2017.
 */
class WhatsNewItemAdapter(
    private val mData: List<WhatsNewItem>,
    private val mContext: Context,
    private val mItemLayoutOption: ItemLayoutOption

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var titleColor: Int = Color.BLACK
    var contentColor: Int = Color.parseColor("#808080")
    var iconColor: Int = Color.BLACK

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position <= mData.size) {
            when (mItemLayoutOption){
                ItemLayoutOption.NORMAL -> {
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
                ItemLayoutOption.LIKE_IOS -> {
                    with((holder as ItemViewHolder2).binding) {
                        if (mData[position].imageRes != 0) {
                            AppCompatResources.getDrawable(mContext, mData[position].imageRes)?.let { drawable ->
                                itemImageView.setImageDrawable(drawable)
                                val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT as Int, RelativeLayout.LayoutParams.WRAP_CONTENT as Int)
                                params.addRule(RelativeLayout.CENTER_VERTICAL)
                                itemImageView.layoutParams = params
                                DrawableCompat.setTint(drawable, iconColor)
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

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (mItemLayoutOption){
            ItemLayoutOption.NORMAL -> {
                ItemViewHolder(WhatsnewItemBinding.inflate(LayoutInflater.from(mContext), parent, false))
            }
            ItemLayoutOption.LIKE_IOS -> {
                ItemViewHolder2(WhatsnewItemIosBinding.inflate(LayoutInflater.from(mContext), parent, false))
            }
        }
    }

    override fun getItemCount(): Int = mData.size

    class ItemViewHolder(val binding: WhatsnewItemBinding) : RecyclerView.ViewHolder(binding.root)
    class ItemViewHolder2(val binding: WhatsnewItemIosBinding) : RecyclerView.ViewHolder(binding.root)

}