package io.github.tonnyl.sample;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.github.tonnyl.sample.databinding.ActivityMainBinding;
import io.github.tonnyl.whatsnew.WhatsNew;
import io.github.tonnyl.whatsnew.item.WhatsNewItem;

/**
 * Created by lizhaotailang on 01/12/2017.
 */

public class AnotherActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WhatsNew.newInstance(
                        new WhatsNewItem("Nice Icons", "Completely customize colors, texts and icons.", R.drawable.ic_heart),
                        new WhatsNewItem("Such Easy", "Setting this up only takes 2 lines of code, impressive you say?", R.drawable.ic_thumb_up),
                        new WhatsNewItem("Very Sleep", "It helps you get more sleep by writing less code.", R.drawable.ic_satisfied_face),
                        new WhatsNewItem("Text Only", "No icons? Just go with plain text.", WhatsNewItem.NO_IMAGE_RES_ID)
                ).presentAutomatically(AnotherActivity.this);

//                WhatsNew whatsNew = WhatsNew.newInstance(
//                        new WhatsNewItem("Nice Icons", "Completely customize colors, texts and icons.", R.drawable.ic_heart),
//                        new WhatsNewItem("Such Easy", "Setting this up only takes 2 lines of code, impressive you say?", R.drawable.ic_thumb_up),
//                        new WhatsNewItem("Very Sleep", "It helps you get more sleep by writing less code.", R.drawable.ic_satisfied_face),
//                        new WhatsNewItem("Text Only", "No icons? Just go with plain text.", WhatsNewItem.NO_IMAGE_RES_ID));
//
//                whatsNew.setPresentationOption(PresentationOption.DEBUG);
//
//                whatsNew.setTitleColor(ContextCompat.getColor(AnotherActivity.this, R.color.colorAccent));
//                whatsNew.setTitleText("What's Up");
//
//                whatsNew.setButtonText("Got it!");
//                whatsNew.setButtonBackground(ContextCompat.getColor(AnotherActivity.this, R.color.colorPrimaryDark));
//                whatsNew.setButtonTextColor(ContextCompat.getColor(AnotherActivity.this, R.color.colorAccent));
//
//                whatsNew.setItemTitleColor(ContextCompat.getColor(AnotherActivity.this, R.color.colorAccent));
//                whatsNew.setItemContentColor(Color.parseColor("#808080"));
//
//                whatsNew.presentAutomatically(AnotherActivity.this);
            }
        });

    }
}
