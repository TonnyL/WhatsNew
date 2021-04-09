package io.github.tonnyl.whatsnew.item

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @param title Item title.
 * @param content Item description.
 * @param imageRes Item icon. Pass [WhatsNewItem.NO_IMAGE_RES_ID] if you do not want to show any icon.
 */
@Parcelize
data class WhatsNewItem(
        var title: String,
        var content: CharSequence,
        var imageRes: Int
) : Parcelable {

    companion object {

        const val NO_IMAGE_RES_ID = 0

    }

}