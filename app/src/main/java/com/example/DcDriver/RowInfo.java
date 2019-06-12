package com.example.DcDriver;

public class RowInfo {
    public String imageUrl;
    public String name;
    public String carnum;

    public RowInfo() {

    }

    public  RowInfo(String imageUrl, String name, String carnum) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.carnum = carnum;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }
}
