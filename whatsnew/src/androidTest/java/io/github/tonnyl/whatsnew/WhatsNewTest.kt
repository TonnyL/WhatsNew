package io.github.tonnyl.whatsnew

import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.FlakyTest
import androidx.test.rule.ActivityTestRule
import io.github.tonnyl.whatsnew.item.WhatsNewItem
import io.github.tonnyl.whatsnew.test.R
import io.github.tonnyl.whatsnew.util.PresentationOption
import org.hamcrest.core.Is.`is`
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Android test cases for [WhatsNew].
 */
@RunWith(AndroidJUnit4::class)
class WhatsNewTest {

    @JvmField
    @Rule
    var activityRule = ActivityTestRule(MockActivity::class.java)

    @Test
    @FlakyTest
    @Throws(Throwable::class)
    fun testConstruction_newInstance() {
        val whatsNew = WhatsNew.newInstance()

        Assert.assertNotNull(whatsNew)
    }

    @Test
    @FlakyTest
    @Throws(Throwable::class)
    fun testField_presentationOption() {
        val whatsNew = WhatsNew.newInstance()

        Assert.assertThat(whatsNew.presentationOption, `is`(PresentationOption.IF_NEEDED))

        whatsNew.presentationOption = PresentationOption.DEBUG
        Assert.assertThat(whatsNew.presentationOption, `is`(PresentationOption.DEBUG))

        whatsNew.presentationOption = PresentationOption.ALWAYS
        Assert.assertThat(whatsNew.presentationOption, `is`(PresentationOption.ALWAYS))

        whatsNew.presentationOption = PresentationOption.NEVER
        Assert.assertThat(whatsNew.presentationOption, `is`(PresentationOption.NEVER))
    }

    @Test
    @FlakyTest
    @Throws(Throwable::class)
    fun testField_title() {
        val whatsNew = WhatsNew.newInstance()

        Assert.assertEquals(whatsNew.titleText, "What's New")
        whatsNew.titleText = "Test title"
        Assert.assertEquals(whatsNew.titleText, "Test title")

        Assert.assertEquals(whatsNew.titleColor, Color.parseColor("#000000"))
        whatsNew.titleColor = ContextCompat.getColor(activityRule.activity, R.color.colorPrimary)
        Assert.assertEquals(whatsNew.titleColor, Color.parseColor("#3F51B5"))
    }

    @Test
    @FlakyTest
    @Throws(Throwable::class)
    fun testBackgroundColor() {
        val whatsNew = WhatsNew.newInstance()
        Assert.assertEquals(whatsNew.backgroundColor, -1)
        whatsNew.backgroundColor = ContextCompat.getColor(activityRule.activity, R.color.colorAccent)
        Assert.assertEquals(whatsNew.backgroundColor, Color.parseColor("#FF4081"))
    }

    @Test
    @FlakyTest
    @Throws(Throwable::class)
    fun testField_button() {
        val whatsNew = WhatsNew.newInstance()

        Assert.assertEquals(whatsNew.buttonText, "Continue")
        whatsNew.buttonText = "Got it!"
        Assert.assertEquals(whatsNew.buttonText, "Got it!")

        Assert.assertEquals(whatsNew.buttonTextColor, Color.parseColor("#FFEB3B"))
        whatsNew.buttonTextColor = ContextCompat.getColor(activityRule.activity, R.color.colorAccent)
        Assert.assertEquals(whatsNew.buttonTextColor, Color.parseColor("#FF4081"))

        Assert.assertEquals(whatsNew.buttonBackground, Color.parseColor("#000000"))
        whatsNew.buttonBackground = ContextCompat.getColor(activityRule.activity, R.color.colorPrimaryDark)
        Assert.assertEquals(whatsNew.buttonBackground, Color.parseColor("#303F9F"))
    }

    @Test
    @FlakyTest
    @Throws(Throwable::class)
    fun testField_itemText() {
        val whatsNew = WhatsNew.newInstance(
                WhatsNewItem("Nice Icons", "Completely customize colors, texts and icons.", R.drawable.ic_heart),
                WhatsNewItem("Such Easy", "Setting this up only takes 2 lines of code, impressive you say?", R.drawable.ic_thumb_up),
                WhatsNewItem("Very Sleep", "It helps you get more sleep by writing less code.", R.drawable.ic_satisfied_face),
                WhatsNewItem("Text Only", "No icons? Just go with plain text.", WhatsNewItem.NO_IMAGE_RES_ID))

        // In fact, WhatsNew is just a dialog fragment.
        // All the instances are passed by bundle.
        Assert.assertNotNull(whatsNew.mItems)

        whatsNew.presentationOption = PresentationOption.DEBUG
        whatsNew.presentAutomatically(activityRule.activity)

        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Assert.assertThat(whatsNew.mItems[0].title, `is`("Nice Icons"))
        Assert.assertThat(whatsNew.mItems[0].content, `is`("Completely customize colors, texts and icons."))
        Assert.assertThat(whatsNew.mItems[0].imageRes, `is`(R.drawable.ic_heart))

        Assert.assertThat(whatsNew.mItems[1].title, `is`("Such Easy"))
        Assert.assertThat(whatsNew.mItems[1].content, `is`("Setting this up only takes 2 lines of code, impressive you say?"))
        Assert.assertThat(whatsNew.mItems[1].imageRes, `is`(R.drawable.ic_thumb_up))

        Assert.assertThat(whatsNew.mItems[2].title, `is`("Very Sleep"))
        Assert.assertThat(whatsNew.mItems[2].content, `is`("It helps you get more sleep by writing less code."))
        Assert.assertThat(whatsNew.mItems[2].imageRes, `is`(R.drawable.ic_satisfied_face))

        Assert.assertThat(whatsNew.mItems[3].title, `is`("Text Only"))
        Assert.assertThat(whatsNew.mItems[3].content, `is`("No icons? Just go with plain text."))
        Assert.assertEquals(whatsNew.mItems[3].imageRes, 0)
    }

    @Test
    @FlakyTest
    @Throws(Throwable::class)
    fun testField_itemColor() {
        val whatsNew = WhatsNew.newInstance()

        Assert.assertNull(whatsNew.itemTitleColor)
        whatsNew.itemTitleColor = ContextCompat.getColor(activityRule.activity, R.color.colorAccent)
        Assert.assertEquals(whatsNew.itemTitleColor, Color.parseColor("#FF4081"))

        Assert.assertNull(whatsNew.itemContentColor)
        whatsNew.itemContentColor = ContextCompat.getColor(activityRule.activity, R.color.colorPrimaryDark)
        Assert.assertEquals(whatsNew.itemContentColor, Color.parseColor("#303F9F"))
    }

    @Test
    @FlakyTest
    @Throws(Throwable::class)
    fun testPresentation_showDismiss() {
        val whatsNew = WhatsNew.newInstance()
        whatsNew.presentationOption = PresentationOption.DEBUG

        whatsNew.presentAutomatically(activityRule.activity)

        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Assert.assertTrue(whatsNew.dialog.isShowing)

        // Click the button.
        onView(withId(R.id.button)).perform(ViewActions.click())

        Assert.assertNull(whatsNew.dialog)
    }

    @Test
    @FlakyTest
    @Throws(Throwable::class)
    fun testPresentation_presentAutomatically() {
        // When the presentation option is NEVER,
        // the dialog should not show at all.
        val wn1 = WhatsNew.newInstance()
        wn1.presentationOption = PresentationOption.NEVER
        wn1.presentAutomatically(activityRule.activity)

        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Assert.assertNull(wn1.dialog)

        // When the presentation option is DEBUG,
        // the dialog should present always.
        val wn2 = WhatsNew.newInstance()
        wn2.presentationOption = PresentationOption.DEBUG
        wn2.presentAutomatically(activityRule.activity)

        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Assert.assertNotNull(wn2.dialog)
        Assert.assertTrue(wn2.dialog.isShowing)
    }

}
