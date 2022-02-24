package com.example.precisa.goldcinema;

/**
 * Created by Precisa on 24-May-18.
 */

public class fsetter {
    public String Fname,Price,Img;
    int fquantity=0;
    public fsetter(String fname,String price,String img)
    {
        Fname = fname;
        Price = price;
        Img = img;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        this.Fname = fname;
    }

    public void setPrice(String price) {
        this.Price = price;
    }

    public String getPrice() {
        return Price;
    }

    public void setImage(String image) {
        this.Img = image;
    }

    public String getImage() {
        return Img;
    }
}
