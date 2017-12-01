package io.github.tonnyl.sample

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import io.github.tonnyl.whatsnew.WhatsNew
import io.github.tonnyl.whatsnew.item.WhatsNewItem
import io.github.tonnyl.whatsnew.util.PresentationOption
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.setOnClickListener {
            WhatsNew.newInstance(
                    WhatsNewItem("Nice Icons", "Completely customize colors, texts and icons.", R.drawable.ic_heart),
                    WhatsNewItem("Such Easy", "Setting this up only takes 2 lines of code, impressive you say?", R.drawable.ic_thumb_up),
                    WhatsNewItem("Very Sleep", "It helps you get more sleep by writing less code.", R.drawable.ic_satisfied_face),
                    WhatsNewItem("Text Only", "No icons? Just go with plain text.")
            ).apply {
                presentationOption = PresentationOption.DEBUG
            }
                    .presentAutomatically(this@MainActivity)

            /*val whatsnew = WhatsNew.newInstance(
                    WhatsNewItem("Nice Icons", "Completely customize colors, texts and icons.", R.drawable.ic_heart),
                    WhatsNewItem("Such Easy", "Setting this up only takes 2 lines of code, impressive you say?", R.drawable.ic_thumb_up),
                    WhatsNewItem("Very Sleep", "It helps you get more sleep by writing less code.", R.drawable.ic_satisfied_face),
                    WhatsNewItem("Text Only", "No icons? Just go with plain text."))

            with(whatsnew) {
                presentationOption = PresentationOption.DEBUG

                titleColor = ContextCompat.getColor(this@MainActivity, R.color.colorAccent)
                titleText = "What's Up"

                buttonText = "Got it!"
                buttonBackground = ContextCompat.getColor(this@MainActivity, R.color.colorPrimaryDark)
                buttonTextColor = ContextCompat.getColor(this@MainActivity, R.color.colorAccent)

                itemContentColor = Color.parseColor("#808080")
                itemTitleColor = ContextCompat.getColor(this@MainActivity, R.color.colorAccent)
            }

            whatsnew.presentAutomatically(this@MainActivity)*/
        }
    }
}
