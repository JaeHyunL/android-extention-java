package com.smsing.GoLee;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

//인트로 화면 구현
public class SplashActivity extends Activity {

    //onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //ImageView 변수설정
        ImageView introimage = (ImageView) findViewById(R.id.gif_image);
        //GlideDrawableImageViewTarget ImageView(introimage이라는 변수로설정)
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(introimage);
        //이미지를 로드해줍니다.
        Glide.with(this).load(R.drawable.intro).into(gifImage);
        startLoading();
    }

    private void startLoading() {
        //Handler가필요한주요이유:
        //작업스레드에서는 메인스레드의 변수를 참조하거나 변경할 수는 있지만, 메인스레 드에서 정의된 UI(예: 위젯, View 등)을 변경할 수는 없다.
        //작업스레드에서메인스레드의UI를변경할필요가 있을경우,작업스레드는 Handler를 통해 메인스레드에게 UI를 변경하라고 알릴 수 있다.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 5000);
    }

}
