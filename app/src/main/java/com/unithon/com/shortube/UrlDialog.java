package com.unithon.com.shortube;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UrlDialog extends Dialog {
    EditText url;
    Button btn;
    public UrlDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);   //다이얼로그의 타이틀바를 없애주는 옵션입니다.
//        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  //다이얼로그의 배경을 투명으로 만듭니다.
        setContentView(R.layout.activity_dialog);     //다이얼로그에서 사용할 레이아웃입니다.
        url = (EditText) findViewById(R.id.urlEdit);

        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btn = (Button) findViewById(R.id.urlSubmitBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();   //다이얼로그를 닫는 메소드입니다.
            }
        });

    }
}
