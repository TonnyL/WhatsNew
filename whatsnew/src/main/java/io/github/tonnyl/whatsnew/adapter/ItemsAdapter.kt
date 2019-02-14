package io.github.tonnyl.whatsnew.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import io.github.tonnyl.whatsnew.R
import io.github.tonnyl.whatsnew.item.WhatsNewItem
import kotlinx.android.synthetic.main.item.view.*

/**
 * Created by lizhaotailang on 30/11/2017.
 */
class ItemsAdapter(private val mData: Array<WhatsNewItem>, private val mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var titleColor: Int = Color.BLACK
    var contentColor: Int = Color.parseColor("#808080")
    var iconColor: Int = Color.BLACK

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position <= mData.size) {
            with(holder as ItemViewHolder) {
                with(itemView) {
                    mData[position].imageRes?.let {
                        AppCompatResources.getDrawable(mContext, it)?.let { drawable ->
                            DrawableCompat.setTint(drawable, iconColor)
                            itemTitleTextView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                        }
                    }
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