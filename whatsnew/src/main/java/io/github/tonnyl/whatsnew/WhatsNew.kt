package io.github.tonnyl.whatsnew

import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.tonnyl.whatsnew.adapter.ItemsAdapter
import io.github.tonnyl.whatsnew.item.WhatsNewItem
import io.github.tonnyl.whatsnew.util.PresentationOption
import kotlinx.android.synthetic.main.whatsnew_main.*

/**
 * Created by lizhaotailang on 30/11/2017.
 */
class WhatsNew : DialogFragment() {

    val mItems: ArrayList<WhatsNewItem> by lazy {
        val args = requireNotNull(arguments) {
            "arguments must not be null"
        }
        args.getParcelableArrayList<WhatsNewItem>(ARGUMENT)
    }
    var presentationOption: PresentationOption = PresentationOption.IF_NEEDED
    var titleText: CharSequence = "What's New"
    var titleColor: Int = Color.parseColor("#000000")
    var itemTitleColor: Int? = null
    var itemContentColor: Int? = null
    var iconColor: Int? = null
    var backgroundColorResource: Int = android.R.color.white
    var buttonBackground: Int = Color.parseColor("#000000")
    var buttonText: String = "Continue"
    var buttonTextColor: Int = Color.parseColor("#FFEB3B")

    companion object {

        const val TAG = "WhatsNew"

        const val ARGUMENT = "argument"

        private const val LAST_VERSION_CODE = "LAST_VERSION_CODE"
        private const val LAST_VERSION_NAME = "LAST_VERSION_NAME"

        @JvmStatic
        fun newInstance(vararg items: WhatsNewItem): WhatsNew = this.newInstance(items.toList())

        @JvmStatic
        fun newInstance(items: List<WhatsNewItem>): WhatsNew {
            val list = ArrayList<WhatsNewItem>()
            list.addAll(items)

            val bundle = Bundle().apply {
                putParcelableArrayList(ARGUMENT, list)
            }

            return WhatsNew().apply { arguments = bundle }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.whatsnew_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // The title text view.
        with(titleTextView) {
            text = titleText
            setTextColor(titleColor)
        }

        // The recycler view.
        with(itemsRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = ItemsAdapter(mItems, requireContext()).apply {
                this@WhatsNew.itemContentColor?.let { this@apply.contentColor = it }
                this@WhatsNew.itemTitleColor?.let { this@apply.titleColor = it }
                this@WhatsNew.iconColor?.let { this@apply.iconColor = it }
            }
        }

        // The button.
        with(button) {
            text = buttonText
            setTextColor(buttonTextColor)
            setBackgroundColor(buttonBackground)
            setOnClickListener { dismiss() }
        }

        // Make the dialog fullscreen.
        val window = dialog.window ?: return
        window.setBackgroundDrawableResource(backgroundColorResource)
        window.decorView.setPadding(0, 0, 0, 0)
        with(window.attributes) {
            gravity = Gravity.BOTTOM
            width = WindowManager.LayoutParams.MATCH_PARENT
            height = WindowManager.LayoutParams.WRAP_CONTENT
        }

        // Animate.
        window.setWindowAnimations(R.style.WhatsNewDialogAnimation)
    }

    fun presentAutomatically(activity: AppCompatActivity) {

        when (presentationOption) {
            PresentationOption.DEBUG -> {
                show(activity.supportFragmentManager, TAG)
            }
            PresentationOption.NEVER -> {
                return
            }
            else -> {
                // Obtain the last version code from sp.
                val lastVersionCode = PreferenceManager.getDefaultSharedPreferences(activity)
                        .getInt(LAST_VERSION_CODE, 0)
                var nowVersionCode: Int

                var lastFirstNumOfVersionName = 0
                var nowFirstNumOfVersionName = 0
                var lastSecondNumOfVersionName = 0
                var nowSecondNumOfVersionName = 0

                try {
                    var tmp: String
                    activity.packageManager
                            .getPackageInfo(activity.packageName, 0)
                            .let {
                                nowVersionCode = it.versionCode
                                tmp = it.versionName
                            }

                    // Obtain the first two numbers of current version name.
                    tmp.split("\\.".toRegex())
                            .filter { !it.isEmpty() && !it.isBlank() }
                            .apply {
                                if (size >= 1) {
                                    nowFirstNumOfVersionName = this[0].toInt()
                                }

                                if (size >= 2) {
                                    nowSecondNumOfVersionName = this[1].toInt()
                                }
                            }

                    // Obtain the first two numbers of last version name.
                    val versionName = PreferenceManager.getDefaultSharedPreferences(activity)
                            .getString(LAST_VERSION_NAME, "") ?: return

                    versionName.split("\\.".toRegex())
                            .filter { !it.isEmpty() && !it.isBlank() }
                            .apply {
                                if (size >= 1) {
                                    lastFirstNumOfVersionName = this[0].toInt()
                                }

                                if (size >= 2) {
                                    lastSecondNumOfVersionName = this[1].toInt()
                                }
                            }

                    if (presentationOption == PresentationOption.ALWAYS) {
                        if (nowVersionCode >= 0 && nowVersionCode > lastVersionCode) {

                            // Show the dialog.
                            show(activity.supportFragmentManager, TAG)
                            // Save the latest version code to sp.
                            PreferenceManager.getDefaultSharedPreferences(activity)
                                    .edit()
                                    .putInt(LAST_VERSION_CODE, nowVersionCode)
                                    .apply()
                        }
                    } else { // presentationOption == PresentationOption.IF_NEEDED
                        if (((nowFirstNumOfVersionName >= 0 && nowFirstNumOfVersionName > lastFirstNumOfVersionName)
                                        || (nowSecondNumOfVersionName >= 0 && nowSecondNumOfVersionName > lastSecondNumOfVersionName))
                                && (nowVersionCode >= 0 && lastVersionCode >= 0 && nowVersionCode > lastVersionCode)) {

                            // Show the dialog.
                            show(activity.supportFragmentManager, TAG)
                            // Save the latest version name to sp.
                            PreferenceManager.getDefaultSharedPreferences(activity)
                                    .edit()
                                    .putInt(LAST_VERSION_CODE, nowVersionCode)
                                    .putString(LAST_VERSION_NAME, "$nowFirstNumOfVersionName.$nowSecondNumOfVersionName")
                                    .apply()
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

        }

    }

}