package io.github.tonnyl.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import io.github.tonnyl.sample.databinding.ActivityMainBinding
import io.github.tonnyl.whatsnew.WhatsNew
import io.github.tonnyl.whatsnew.item.WhatsNewItem
import io.github.tonnyl.whatsnew.util.PresentationOption

class MainActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.textView.setOnClickListener {
            WhatsNew.newInstance(
                    WhatsNewItem("Nice Icons", "Completely customize colors, texts and icons.", R.drawable.ic_heart),
                    WhatsNewItem("Such Easy", "Setting this up only takes 2 lines of code, impressive you say?", R.drawable.ic_thumb_up),
                    WhatsNewItem("Very Sleep", "It helps you get more sleep by writing less code.", R.drawable.ic_satisfied_face),
                    WhatsNewItem("Text Only", HtmlCompat.fromHtml("No icons? Just go with plain text <a href=\"https://github.com/TonnyL/WhatsNew\">or HTML</a>.", HtmlCompat.FROM_HTML_MODE_COMPACT), WhatsNewItem.NO_IMAGE_RES_ID)
            ).apply {
                presentationOption = PresentationOption.DEBUG
            }
                    .presentAutomatically(this@MainActivity)

//            val whatsnew = WhatsNew.newInstance(
//                    WhatsNewItem("Admin Sat", "Ahora puedes sincronizar tus facturas con el SAT usando la funcion Admin SAT.", R.drawable.ic_heart),
//                    WhatsNewItem("Such Easy", "Setting this up only takes 2 lines of code, impressive you say?", R.drawable.ic_thumb_up),
//                    WhatsNewItem("Very Sleep", "It helps you get more sleep by writing less code.", R.drawable.ic_satisfied_face),
//                                                WhatsNewItem("Text Only", "No icons? Just go with plain text.", WhatsNewItem.NO_IMAGE_RES_ID))
//
//            with(whatsnew) {
//                presentationOption = PresentationOption.DEBUG
//                itemLayoutOption = ItemLayoutOption.LIKE_IOS
//                iconColor = Color.parseColor("#348AC7")
//                titleColor = Color.parseColor("#348AC7")
//                titleText = "What's up"
//                buttonText = "Got it!"
//                buttonBackground = Color.parseColor("#30D0C7")
//                buttonTextColor = Color.WHITE
//                titleSize = 28.0f
//                itemContentColor = Color.parseColor("#808080")
//                itemTitleColor = ContextCompat.getColor(this@MainActivity, R.color.colorAccent)
//            }
//
//            whatsnew.presentAutomatically(this@MainActivity)
        }
    }
}
