package com.example.jumi.merona;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.jumi.merona.network.ServiceApi;

import java.util.ArrayList;

public class List_Activity extends Activity{
    private Activity activity;
    public static Context context;
    private ArrayList<Item> m_arr;
    private List_Adapter adapter;
    ListView list;
    Button btn;

    private ServiceApi service;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        // Intent i = getIntent();

        btn=(Button)findViewById(R.id.btn_write);
        btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(List_Activity.this, List_Write.class);
                startActivity(intent);
                finish();
            }
        });

        init();


    }

    public void init(){
        list=(ListView)findViewById(R.id.listView);
        setList();
    }

    private void setList(){
        m_arr = new ArrayList<Item>();
        ListView lv = (ListView)findViewById(R.id.listView);
        m_arr.add(new Item(2,"가는길에태워다주기","덕진광장","공대 7호관"," ","2015-22-22","홍길동",1000));
        m_arr.add(new Item(3,"급하게 샤프빌려주실분"," ","자연대 5호관","301호","2015-22-22","홍길동",2000));
        m_arr.add(new Item(4,"컴퓨터공학부과사에 서류제출","구정문","공대 7호관","컴퓨터공학부과사","2015-22-22","홍길동",1500));

        /**
         GET List Users
         **/
     /*   Call<BoardList> call2 = service.doGetBoardList();
        call2.enqueue(new Callback<BoardList>() {
            @Override
            public void onResponse(Call<BoardList> call, Response<BoardList> response) {

                BoardList boardList = response.body();
                //Toast.makeText(getApplicationContext(), text + " page\n" + total + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();

                List<BoardList.Datum> datumList = boardList.data;


                for (BoardList.Datum datum : datumList) {
                    m_arr.add(new Item(datum.bNum, datum.bTitle, datum.spotStart, datum.spotEnd,"403호",datum.bDate,datum.sid, datum.bPrice));
                }
            }

            @Override
            public void onFailure(Call<BoardList> call, Throwable t) {
                call.cancel();
            }
        });*/


        adapter = new List_Adapter(this, m_arr);
        lv.setAdapter(adapter);
        //lv.setDivider(null); 구분선을 없에고 싶으면 null 값을 set합니다.
        //lv.setDividerHeight(5); 구분선의 굵기를 좀 더 크게 하고싶으면 숫자로 높이 지정가능.
    }
    public void listUpdate(){
        adapter.notifyDataSetChanged();
    }




}
