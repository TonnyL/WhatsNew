package io.github.tonnyl.whatsnew

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.tonnyl.whatsnew.adapter.WhatsNewItemAdapter
import io.github.tonnyl.whatsnew.databinding.WhatsnewMainBinding
import io.github.tonnyl.whatsnew.item.WhatsNewItem
import io.github.tonnyl.whatsnew.util.ItemLayoutOption
import io.github.tonnyl.whatsnew.util.PresentationOption

/**
 * Created by lizhaotailang on 30/11/2017.
 */
class WhatsNew : DialogFragment() {

    val mItems: ArrayList<WhatsNewItem> by lazy {
        requireArguments().getParcelableArrayList<WhatsNewItem>(ARGUMENT) as ArrayList<WhatsNewItem>
    }
    var presentationOption: PresentationOption = PresentationOption.IF_NEEDED
    var itemLayoutOption: ItemLayoutOption = ItemLayoutOption.NORMAL
    var titleText: CharSequence = "What's New"
    var titleColor: Int = Color.parseColor("#000000")
    var titleSize: Float = 18.0f
    var itemTitleColor: Int? = null
    var itemContentColor: Int? = null
    var iconColor: Int? = null
    var backgroundColorResource: Int = android.R.color.white
    var backgroundColor: Int = -1
    var buttonBackground: Int = Color.parseColor("#000000")
    var buttonText: String = "Continue"
    var buttonTextColor: Int = Color.parseColor("#FFEB3B")

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        WhatsnewMainBinding.inflate(layoutInflater)
    }

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // The title text view.
        with(binding.titleTextView) {
            text = titleText
            textSize = titleSize
            setTextColor(titleColor)
        }

        // The recycler view.
        with(binding.itemsRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = WhatsNewItemAdapter(mItems, requireContext(), itemLayoutOption).apply {
                this@WhatsNew.itemContentColor?.let { this@apply.contentColor = it }
                this@WhatsNew.itemTitleColor?.let { this@apply.titleColor = it }
                this@WhatsNew.iconColor?.let { this@apply.iconColor = it }
            }
        }

        // The button.
        with(binding.button) {
            text = buttonText
            setTextColor(buttonTextColor)
            setBackgroundColor(buttonBackground)
            setOnClickListener { dismiss() }
        }

        // Make the dialog fullscreen.
        val window = dialog?.window ?: return
        if (backgroundColor != -1) {
            window.setBackgroundDrawable(ColorDrawable(backgroundColor))
        } else {
            window.setBackgroundDrawableResource(backgroundColorResource)
        }
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
                        .filter { it.isNotEmpty() && it.isNotBlank() }
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
                        .filter { it.isNotEmpty() && it.isNotBlank() }
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
                            && (nowVersionCode >= 0 && lastVersionCode >= 0 && nowVersionCode > lastVersionCode)
                        ) {

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