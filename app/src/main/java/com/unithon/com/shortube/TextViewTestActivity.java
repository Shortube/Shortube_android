package com.unithon.com.shortube;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by macbook on 2018. 7. 28..
 */

public class TextViewTestActivity extends AppCompatActivity {

    private static final String TAG = TextViewTestActivity.class.getSimpleName();
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_test);
        textView = findViewById(R.id.tv_test);


        SpannableString ss = new SpannableString("Android is a Software stack");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Toast.makeText(TextViewTestActivity.this, "hello world", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan, 22, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        regexTest();
    }

    public void regexTest(){
//        String pattern = "^[a-zA-Z]*$";
//        String input = "내가...? (짜장면 싫다고) 안 했는데..?1:1:11";
//        String pattern = "(.*)([0-9]{1,2}:)*[0-9]{1,2}:[0-9]{1,2}";
//        boolean i = Pattern.matches(pattern, input);
//        if(i==true) {
//            System.out.println(input+"는 패턴에 일치함.");
//        }
//        else {
//            System.out.println("패턴 일치하지 않음.");
//        }

        Pattern pattern = Pattern.compile("([0-9]{1,2}:)*([0-9]{1,2}:[0-9]{1,2})");
        Matcher matcher = pattern.matcher("2:22:11 내가...? (짜장면 싫다고) 안 했는데..?1:1:11");

        List<SectionData> sectionDataList = new ArrayList<>();
        int matched = 1;

        while(matcher.find()){
            System.out.println(matcher.group());
        }

//        if(matcher.matches()){
//            Log.d(TAG, "matched Count : " + matcher.groupCount());
//            Log.d(TAG, "matched 1 : " + matcher.group(1));
//            Log.d(TAG, "matched 2 : " + matcher.group(2));
//        }

    }
}
