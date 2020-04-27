package com.example.jumi.merona;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jumi.merona.data.JoinData;
import com.example.jumi.merona.data.JoinResponse;
import com.example.jumi.merona.network.RetrofitClient;
import com.example.jumi.merona.network.ServiceApi;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class JoinActivity extends AppCompatActivity implements View.OnClickListener{
    EditText id, pwd, pwConfirm, name, major, nickname, phone;
    String tid, tpwd, tpwConfirm, tname, tmajor, tnickname, tphone;
    CheckBox inform_check;
    private ServiceApi service;


    boolean pwCheck;

    private final String closePopup_1 = "Close Popup_1"; // 이용약관 팝업
    private final String closePopup_2 = "Close Popup_2"; // 정보제공 팝업
    String result_1, result_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("회원가입");

        ab.setDisplayShowTitleEnabled(false);


        service = RetrofitClient.getClient().create(ServiceApi.class);

        id = (EditText) findViewById(R.id.InputId);
        pwd = (EditText) findViewById(R.id.InputPw);
        pwConfirm = (EditText) findViewById(R.id.InputConfirmPw);
        name = (EditText) findViewById(R.id.InputName);
        major = (EditText) findViewById(R.id.InputMajor);
        nickname = (EditText) findViewById(R.id.InputNum);
        phone = (EditText) findViewById(R.id.phone);
        inform_check = (CheckBox) findViewById(R.id.inform_check); // 이용약관 및 정보 동의
        inform_check.setOnClickListener(this);


        Button joinBtn = (Button) findViewById(R.id.Joinbtn); // 회원가입
        Button watchBtn1 = (Button) findViewById(R.id.watch_btn1); // 이용약관 보기
        Button watchBtn2 = (Button) findViewById(R.id.watch_btn2); // 개인정보제공 보기
        joinBtn.setOnClickListener(this);
        watchBtn1.setOnClickListener(this);
        watchBtn2.setOnClickListener(this);

    } //onCreate() 종료

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //데이터 받기
                result_1 = data.getStringExtra("result_1"); // 이용약관
                result_1.toString();
            }
        }

        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                //데이터 받기
                result_2 = data.getStringExtra("result_2");
                result_2.toString();
            }
        }

        if(result_1 != null && result_2 != null ) {
            if (result_1.equals(closePopup_1) && result_2.equals(closePopup_2)) { // 정보 제공, 이용 약관 모두 확인 시
                inform_check.setChecked(true); // 체크 박스 체크
                inform_check.setEnabled(false); // 체크 박스 사용 불가 상태
            }

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Joinbtn: // 회원가입 버튼
                tid = id.getText().toString();
                tpwd = pwd.getText().toString();
                tname = name.getText().toString();
                tmajor = major.getText().toString();
                tnickname = nickname.getText().toString();
                tphone = phone.getText().toString();
                tpwConfirm = pwConfirm.getText().toString();

                pwCheck = Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%^&*()-])(?=.*[a-zA-Z]).{6,16}$", tpwd);

                //유효성 검사
                if(tid.trim().length() == 0 || tpwd.trim().length() == 0 || tpwConfirm.trim().length() == 0 || tmajor.trim().length() == 0 || tname.trim().length() == 0 || tphone.trim().length() == 0){
                    Toast.makeText(this, "빈칸 없이 모두 입력하세요!", Toast.LENGTH_SHORT).show();
                    Log.d("minsu", "공백 발생");
                    return;
                }

                else if(!tpwd.equals(tpwConfirm)){
                    Toast.makeText(this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();


                }

                else if(!pwCheck){
                    Toast.makeText(this, "비밀번호는 6~16자 영문 대 소문자, 숫자, 특수문자의 조합을 사용하세요!", Toast.LENGTH_SHORT).show();

                }

                else if(spaceCheck(tpwd)){
                    Toast.makeText(this, "비밀번호에 공백을 사용할 수 없습니다!", Toast.LENGTH_SHORT).show();

                }

                else if(inform_check.isChecked() == false){
                    Toast.makeText(this, "이용약관 및 사용자 정보제공 \n동의는 필수입니다!", Toast.LENGTH_SHORT).show();

                }
                else {
                    startJoin(new JoinData(tname, tid, tpwd, tnickname, tmajor, tphone));

                }

        }


    }

    private void startJoin(JoinData data) {
        service.userJoin(data).enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                JoinResponse result = response.body();
                Toast.makeText(JoinActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();

                if (result.getCode() == 200) {
                    finish();
                }
            }

            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {
                Toast.makeText(JoinActivity.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 에러 발생", t.getMessage());
            }
        });
    }


    public boolean spaceCheck(String spaceCheck) // 문자열 안에 스페이스 체크
    {
        for(int i = 0; i < spaceCheck.length(); i++)
        {

            if(spaceCheck.charAt(i) == ' ')
                return true;

        }
        return false;
    }

    private void gotoNext(boolean flag) {
        if(flag) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

}