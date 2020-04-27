package com.example.jumi.merona;

public class Item {
    public int Num;
    public String Title;
    public String Start;
    public String Arrive;
    public String Arrive_detail;
    public String Date;
    public String Writer;
    public int Price;

    public Item(int num, String title, String start, String arrive, String arrive_detail, String date, String name, int price){
        Num = Num;
        Title=title;
        Start=start;
        Arrive=arrive;
        Date=date;
        Writer=name;
        Arrive_detail=arrive_detail;
        Price=price;
    }
    public int getNum(){return Num;}

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getStart() {
        return Start;
    }

    public void setStart(String start) {
        Start = start;
    }

    public String getArrive() {
        return Arrive;
    }

    public void setArrive(String arrive) {
        Arrive = arrive;
    }

    public String getArrive_detail() {
        return Arrive_detail;
    }

    public void setArrive_detail(String arrive_detail) {
        Arrive_detail = arrive_detail;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

}
