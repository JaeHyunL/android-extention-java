package com.smsing.GoLee;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;

import org.json.JSONObject;

public class HomeFragment extends Fragment {


    public static Context mContext ;

    /**
    * onCreateView 생명주기
     * @return 뷰를 반환함
     */
    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View homeview = inflater.inflate(R.layout.homefragment,container ,false);
        final EditText urlText = (EditText) homeview.findViewById(R.id.urlInputText);
        Button searchButton = (Button) homeview.findViewById(R.id.searchBtn);
        /* Context를 전역으로 뿌려주기 위해서 mContext라는 변수에 할당함 */
        mContext =  this.getContext();
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = urlText.getText().toString();
                try {
                    ConnectFlaskServer connectFlaskServer = new ConnectFlaskServer();
                    connectFlaskServer.restfulRequest(msg);
                }catch(Exception e){
                    Log.d("HomeFragmentError",e.toString());
                }
            }

        });

        return homeview;

    }
}
