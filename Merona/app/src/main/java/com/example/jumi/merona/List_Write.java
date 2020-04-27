package com.example.jumi.merona;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jumi.merona.data.BoardData;
import com.example.jumi.merona.data.BoardResponse;
import com.example.jumi.merona.network.ServiceApi;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class List_Write extends Activity implements View.OnClickListener {
    EditText mstart, marrive, mprice, mcontent, mnum;
    String tstart, tarrive, tcontent, tprice;
    int tnum;

    private ServiceApi service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write);
        mstart = (EditText) findViewById(R.id.InputStart);
        marrive = (EditText) findViewById(R.id.InputEnd);
        mprice = (EditText) findViewById(R.id.InputCash);
        mcontent = (EditText) findViewById(R.id.InputContent);

        Button write = (Button) findViewById(R.id.Writebtn); // 회원가입
        write.setOnClickListener(this);
    } //onCreate() 종료

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Writebtn: // 글 작성
                tstart = mstart.getText().toString();
                tarrive = marrive.getText().toString();
                tprice = mprice.getText().toString();
                tcontent = mcontent.getText().toString();

                if (tprice.trim().length() == 0 || tcontent.trim().length() == 0) {
                    Toast.makeText(this, "내용과 가격은 빠짐없이 입력하세요!", Toast.LENGTH_SHORT).show();
                    Log.d("minsu", "공백 발생");
                    return;
                } else {
                    startWrite(new BoardData(10, tcontent, tstart, tarrive, "201514736","2019-11-29",Integer.parseInt(tprice)));
                    Intent intent = new Intent(this, List_Activity.class);
                    startActivity(intent);
                    finish();
                }
        }

    }

    private void startWrite(BoardData data){
        service.boardWrite(data).enqueue(new Callback<BoardResponse>() {
            @Override
            public void onResponse(Call<BoardResponse> call, Response<BoardResponse> response) {
                BoardResponse result = response.body();
                Toast.makeText(List_Write.this, result.getMessage(), Toast.LENGTH_SHORT).show();

                if (result.getCode() == 200) {
                    finish();
                }
            }

            @Override
            public void onFailure(Call<BoardResponse> call, Throwable t) {
                Toast.makeText(List_Write.this, "글쓰기 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("글쓰기 에러 발생", t.getMessage());
            }
        });
    }
}
