package io.github.tonnyl.whatsnew.adapter

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.tonnyl.whatsnew.R
import io.github.tonnyl.whatsnew.item.WhatsNewItem
import kotlinx.android.synthetic.main.item.view.*

/**
 * Created by lizhaotailang on 30/11/2017.
 */
class ItemsAdapter(private val mData: Array<WhatsNewItem>, private val mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var titleColor: Int = Color.parseColor("#000000")
    var contentColor: Int = Color.parseColor("#808080")

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position <= mData.size) {
            with(holder as ItemViewHolder) {
                with(itemView) {
                    mData[position].imageRes?.let { itemTitleTextView.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(mContext, it), null, null, null) }
                    itemTitleTextView.compoundDrawablePadding = 16
                    itemTitleTextView.text = mData[position].title
                    itemTitleTextView.setTextColor(titleColor)

                    itemContentTextView.text = mData[position].content
                    itemContentTextView.setTextColor(contentColor)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, parent, false))
    }

    override fun getItemCount(): Int = mData.size

    inner class ItemViewHolder(mItemView: View) : RecyclerView.ViewHolder(mItemView)
}