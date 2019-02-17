package io.github.tonnyl.sample

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import io.github.tonnyl.whatsnew.WhatsNew
import io.github.tonnyl.whatsnew.item.WhatsNewItem
import io.github.tonnyl.whatsnew.util.PresentationOption

/**
 * Created by thomhurst on 03/02/2019.
 */

class BackgroundColorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.background_color_activity)

        val whatsNew = WhatsNew.newInstance(
                WhatsNewItem("Nice Icons", "Completely customize colors, texts and icons.", R.drawable.ic_heart),
                WhatsNewItem("Such Easy", "Setting this up only takes 2 lines of code, impressive you say?", R.drawable.ic_thumb_up),
                WhatsNewItem("Very Sleep", "It helps you get more sleep by writing less code.", R.drawable.ic_satisfied_face),
                WhatsNewItem("Text Only", "No icons? Just go with plain text.", WhatsNewItem.NO_IMAGE_RES_ID))

        whatsNew.presentationOption = PresentationOption.DEBUG

        whatsNew.backgroundColorResource = R.color.colorPrimaryDark
        whatsNew.titleColor = ContextCompat.getColor(this, android.R.color.white)
        whatsNew.titleText = "What's Up"

        whatsNew.buttonText = "Got it!"
        whatsNew.buttonBackground = ContextCompat.getColor(this, android.R.color.white)
        whatsNew.buttonTextColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)

        whatsNew.itemTitleColor = ContextCompat.getColor(this, android.R.color.white)
        whatsNew.itemContentColor = Color.WHITE
        whatsNew.iconColor = Color.WHITE

        whatsNew.presentAutomatically(this)


//        io.github.tonnyl.whatsnew.item.whatsNew {
//            presentationOption = PresentationOption.DEBUG
//            item {
//                title = "Yo"
//                content = "Ye"
//                imageRes = R.drawable.ic_heart
//            }
//            item {
//                title = "Yo2"
//                content = "Ye2"
//                imageRes = R.drawable.ic_thumb_up
//            }
//            item {
//                title = "Yo3"
//                content = "Ye3"
//                imageRes = R.drawable.ic_satisfied_face
//            }
//            item {
//                title = "Yo4"
//                content = "Ye4"
//            }

//            backgroundColorResource = R.color.colorPrimaryDark
//
//            titleColor = Color.WHITE
//            titleText = "What's Up"
//
//            buttonBackground = Color.WHITE
//            buttonTextColor = ContextCompat.getColor(this@BackgroundColorActivity, R.color.colorPrimaryDark)
//            buttonText = "Got it!"
//
//            iconColor = Color.WHITE
//            itemTitleColor = Color.WHITE
//            itemContentColor = Color.WHITE
//        }
    }
}
