package com.smsing.GoLee;


import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    // 변수 선언 부
    // 서버 주소값 및 url 포멧은 항상 고정적  .
    final static String serverdomain ="http://13.125.164.239/post";
    final static String url = "url";

    // FrameLayout에 각 메뉴의 Fragment를 바꿔 줌
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private SubFragment subfragment = new SubFragment();
    private HomeFragment homefragment = new HomeFragment();
    private SettingFragment settingfragment = new SettingFragment();


    // Oncreate Activity 생명주기
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, SplashActivity.class);
        restfulRequest();
        startActivity(intent);
        BottomNavigationOperate();

    }


    private void BottomNavigationOperate(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        // 첫 화면 지정
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, homefragment).commitAllowingStateLoss();
        // bottomNavigationView의 아이템이 선택될 때 호출될 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        //TODO 처음 실행했을때 화면 종류에 관계없이 바텀네비게이션이 첫번째 인덱스에 고정되어있음
        //TODO 수정필요
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.action_sub: {
                        transaction.replace(R.id.frame_layout, subfragment).commitAllowingStateLoss();
                        Toast.makeText(getApplicationContext(),"menu1",Toast.LENGTH_LONG).show();
                        break;
                    }
                    case R.id.action_home: {
                        transaction.replace(R.id.frame_layout, homefragment).commitAllowingStateLoss();
                        Toast.makeText(getApplicationContext(),"menu2",Toast.LENGTH_LONG).show();
                        break;
                    }
                    case R.id.action_setting: {
                        transaction.replace(R.id.frame_layout, settingfragment).commitAllowingStateLoss();
                        Toast.makeText(getApplicationContext(),"menu3",Toast.LENGTH_LONG).show();
                        break;
                    }
                }
                return true;
            }
        });
    }


    //RestfulRequest ( get 요청 함 )
    public void restfulRequest(){
        //RequestQueue  서버 요청자. 다른 Request 클래스들의 정보대로 서버에 요청을 보내는 역할
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        // Json 형태로 Post를 요청하기 위해서 JSONObject를 사용함
        JSONObject object = new JSONObject();
        try {
            //TODO value 값은 메신저에서 하드코딩 방식이 아닌 메시지에서 받은
            // 변수값을 넣어주는 작업을 해야함
            object.put(url,"www.naver.com");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("Object Log Point", object.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, serverdomain, object,
                new Response.Listener<JSONObject>() {
                    // jsonObjectRequest 정상적으로 실행했을 경우 반환값
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("OnResponse","String Response : "+ response.toString());
                    }
                },
                //응답실패시
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error","Error getting response");
                    }
                });
        //RequestQueue 객체의 add( ) 함수에 Request 객체를 매개변수로 지정하여 호출하면 서버 연동이 발생합니다.
        requestQueue.add(jsonObjectRequest);
    }

}