package io.github.tonnyl.whatsnew.item

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes

/**
 * Created by lizhaotailang on 30/11/2017.
 */
data class WhatsNewItem(
        var title: String,
        var content: String
) : Parcelable {

    var imageRes: Int? = null

    constructor(title: String, content: String, @DrawableRes image: Int? = null) : this(title, content) {
        imageRes = image
    }

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(content)
        parcel.writeValue(imageRes)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<WhatsNewItem> {
        override fun createFromParcel(parcel: Parcel): WhatsNewItem = WhatsNewItem(parcel)
        override fun newArray(size: Int): Array<WhatsNewItem?> = arrayOfNulls(size)
    }

}