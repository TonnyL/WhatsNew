package io.github.tonnyl.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import io.github.tonnyl.whatsnew.WhatsNew;
import io.github.tonnyl.whatsnew.item.WhatsNewItem;
import io.github.tonnyl.whatsnew.util.PresentationOption;

/**
 * Created by lizhaotailang on 01/12/2017.
 */

public class AnotherActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WhatsNew.newInstance(
                        new WhatsNewItem("Nice Icons", "Completely customize colors, texts and icons.", R.drawable.ic_heart),
                        new WhatsNewItem("Such Easy", "Setting this up only takes 2 lines of code, impressive you say?", R.drawable.ic_thumb_up),
                        new WhatsNewItem("Very Sleep", "It helps you get more sleep by writing less code.", R.drawable.ic_satisfied_face),
                        new WhatsNewItem("Text Only", "No icons? Just go with plain text.")
                ).presentAutomatically(AnotherActivity.this);

                /*WhatsNew whatsNew = WhatsNew.newInstance(
                        new WhatsNewItem("Nice Icons", "Completely customize colors, texts and icons.", R.drawable.ic_heart),
                        new WhatsNewItem("Such Easy", "Setting this up only takes 2 lines of code, impressive you say?", R.drawable.ic_thumb_up),
                        new WhatsNewItem("Very Sleep", "It helps you get more sleep by writing less code.", R.drawable.ic_satisfied_face),
                        new WhatsNewItem("Text Only", "No icons? Just go with plain text."));

                whatsNew.setPresentationOption(PresentationOption.DEBUG);

                whatsNew.setTitleColor(ContextCompat.getColor(AnotherActivity.this, R.color.colorAccent));
                whatsNew.setTitleText("What's Up");

                whatsNew.setButtonText("Got it!");
                whatsNew.setButtonBackground(ContextCompat.getColor(AnotherActivity.this, R.color.colorPrimaryDark));
                whatsNew.setButtonTextColor(ContextCompat.getColor(AnotherActivity.this, R.color.colorAccent));

                whatsNew.setItemTitleColor(ContextCompat.getColor(AnotherActivity.this, R.color.colorAccent));
                whatsNew.setItemContentColor(Color.parseColor("#808080"));

                whatsNew.presentAutomatically(AnotherActivity.this);*/
            }
        });

    }
}
