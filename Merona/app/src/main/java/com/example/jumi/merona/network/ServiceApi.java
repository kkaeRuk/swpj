package com.example.jumi.merona.network;

import com.example.jumi.merona.data.BoardData;
import com.example.jumi.merona.data.BoardList;
import com.example.jumi.merona.data.BoardResponse;
import com.example.jumi.merona.data.JoinData;
import com.example.jumi.merona.data.JoinResponse;
import com.example.jumi.merona.data.LoginData;
import com.example.jumi.merona.data.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/user/join")
    Call<JoinResponse> userJoin(@Body JoinData data);

    @POST("/board/write")
    Call<BoardResponse> boardWrite(@Body BoardData data);
    //boardWrite ; creatUser

    @GET("/board/main")
    Call<BoardList> doGetBoardList();
    //doGetBoardList : doGetUserList
}
