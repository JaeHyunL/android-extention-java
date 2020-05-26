package com.smsing.GoLee;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ConnectFlaskServer  {

    /**
    * 변수 선언부 serverdomain 과 url은 항상 고정적인 값임.
     */

    final static String serverdomain ="http://13.125.164.239/post";
    final static String url = "url";

    /**
    * 서버에 Post 요청을 보내는 함수
    * @author 이재현
    * @param  murl 검색하고자하는 URL
    * @return 서버 요청 결과 반환값
     */
    public Request<JSONObject> restfulRequest(String murl){

        /**
         * RequestQueue  서버 요청자.
         * 서버에 요청을 보내는 역할
         */
        RequestQueue requestQueue = Volley.newRequestQueue(HomeFragment.mContext);
        /**
         *Json 형태로 Post를 요청하기 위해서 JSONObject를 사용함
         */
        JSONObject object = new JSONObject();
        try {
            object.put(url,murl);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("JsonExecption",e.toString());
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                serverdomain,
                object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(HomeFragment.mContext,
                                response.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error","Error getting response");
                    }
                });

        /**
         * RequestQueue 객체의 add( ) 함수에 Request 객체를
         * 매개변수로 지정하여 호출하면 서버 연동이 발생합니다
         */
        requestQueue.add(jsonObjectRequest);

        return requestQueue.add(jsonObjectRequest);

    }

}
