package com.example.jumi.merona;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentAlert fragmentAlert = new FragmentAlert();
    private FragmentHome fragmentHome = new FragmentHome();
    private FragmentSetting fragmentSetting = new FragmentSetting();
    private UserData userDATA;
    private View navheadermain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_main);


        /////toolbar 설정
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         //기본타이틀 안보여줌
        getSupportActionBar().setDisplayShowTitleEnabled(false);



        //////Navigation Drawer 설정
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //user 정보 가져오기
        Intent intent = getIntent();
        userDATA = (UserData)intent.getSerializableExtra("userDATA");

        //사용자 정보 바꾸기
        navheadermain = navigationView.getHeaderView(0); //nav_header_main 레이아웃 가져오기

        final TextView txtNick = (TextView)navheadermain.findViewById(R.id.textNick);
        final TextView txtId = (TextView)navheadermain.findViewById(R.id.textId);
        final TextView txtMajor = (TextView)navheadermain.findViewById(R.id.textMajor);
        final TextView txtPhone = (TextView)navheadermain.findViewById(R.id.textPhone);

        txtNick.setText(userDATA.nickname);
        txtId.setText(userDATA.studentid);
        txtMajor.setText(userDATA.major);
        txtPhone.setText(userDATA.phonenumber);




        /////Bottom Navigation 설정
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();
        //item 선택될때 리스너
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.alertItem:
                        transaction.replace(R.id.frameLayout, fragmentAlert).commitAllowingStateLoss();
                        break;
                    case R.id.homeItem:
                        transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();
                        break;
                    case R.id.settingItem:
                        transaction.replace(R.id.frameLayout, fragmentSetting).commitAllowingStateLoss();
                        break;
                }
                return true;
            }
        });



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }


    //네비게이션드로어 이벤트
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) { //menu select

        int id = item.getItemId();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (id == R.id.nav_profilechg) {
            transaction.replace(R.id.frameLayout, fragmentAlert).commitAllowingStateLoss();
        } else if (id == R.id.nav_simburm) {
            transaction.replace(R.id.frameLayout, fragmentSetting).commitAllowingStateLoss();
        }
        else if (id == R.id.nav_logout) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(MainActivity.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();

        }
        //네비게이션 드로어 닫기 : 왼쪽
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            Toast.makeText(MainActivity.this, userDATA.nickname, Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
