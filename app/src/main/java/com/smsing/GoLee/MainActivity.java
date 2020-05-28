package com.smsing.GoLee;


import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /* FrameLayout에 각 메뉴의 Fragment를 바꿔 줌 */
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private SubFragment subfragment = new SubFragment();
    private HomeFragment homefragment = new HomeFragment();
    private SettingFragment settingfragment = new SettingFragment();

    /**
    * @author 이재현
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
        BottomNavigationOperate();

    }

    /**
     * 바텀 네비게이션 비치
     * @reuturn 바텀네비게이션
     * @author 이재현
     */
    private void BottomNavigationOperate(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        bottomNavigationView.setSelectedItemId(R.id.action_home);
        transaction.replace(R.id.frame_layout, homefragment).commitAllowingStateLoss();
        bottomNavigationView.setOnNavigationItemSelectedListener(

                new BottomNavigationView.OnNavigationItemSelectedListener() {
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


}