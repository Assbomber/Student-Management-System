package com.example.studentmanagementsystem;

class item {

    private String Id;
    private String name;
    private String sec;
    private String phone;
    private String fee;

    public item(String id,String name,String sec,String phone,String fee){
        this.Id=id;
        this.name=name;
        this.sec=sec;
        this.phone=phone;
        this.fee=fee;
    }

    public String getFee() {
        return fee;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getSec() {
        return sec;
    }
}
