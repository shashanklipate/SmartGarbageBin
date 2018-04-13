package com.example.nitesh.smartgarbagetracksystem;

/**
 * Created by Nitesh on 12-Apr-18.
 */

public class Product {



private String Image,Complainter_Name,Location,Remark;
    public Product(){

    }

    public Product( String Image ,String Complainter_Name, String Location,String Remark) {
        this.Complainter_Name =Complainter_Name;
        this.Location = Location;
        this.Remark = Remark;
        this.Image = Image;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getComplainter_Name() {
        return Complainter_Name;
    }

    public void setComplainter_Name(String complainter_Name) {
        Complainter_Name = complainter_Name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
