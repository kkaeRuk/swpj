<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="javamysql.ConnectDB"%>
<%

	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String name = request.getParameter("name");
	String major = request.getParameter("major");
	String num = request.getParameter("num");
	String phone = request.getParameter("phone");
	
	ConnectDB connectDB = ConnectDB.getInstance();
	
	String returns = connectDB.joindb(id,pwd,name,major,num,phone);
	
%>

    