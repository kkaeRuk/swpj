package com.example.jumi.merona;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jumi.merona.data.LoginData;
import com.example.jumi.merona.data.LoginResponse;
import com.example.jumi.merona.network.RetrofitClient;
import com.example.jumi.merona.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    EditText userId, userPwd;
    Button loginBtn, signUp;
    String loginid, loginpwd;
    private ServiceApi service;
    Intent intent;
    UserData udata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userId = findViewById(R.id.LoginId);
        userPwd = findViewById(R.id.LoginPw);
        loginBtn = findViewById(R.id.login);
        signUp = findViewById(R.id.signup);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        loginBtn.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                loginid = userId.getText().toString();
                loginpwd = userPwd.getText().toString();
                startLogin(new LoginData(loginid, loginpwd));
                break;

            case R.id.signup : // 회원가입
                intent = new Intent(this, JoinActivity.class);
                startActivity(intent);
                break;

        }
    }

    private void startLogin(LoginData data) {
        service.userLogin(data).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result = response.body();
                Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                if(result.getCode()==200) {
                    udata = result.getUserInfo();
                    gotoNext(true);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());
            }
        });
    }

    private void gotoNext(boolean flag) {
        if(flag) {
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("userDATA", udata);
            startActivity(intent);
            finish();
        }
    }
}


