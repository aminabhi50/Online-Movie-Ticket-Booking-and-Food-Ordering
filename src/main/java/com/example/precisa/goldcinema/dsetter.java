package com.example.precisa.goldcinema;

/**
 * Created by Precisa on 24-May-18.
 */

public class dsetter {
    private String mname,cast,img,p,d,desc;
    public dsetter(String mname,String cast,String img,String p,String d,String desc)
    {
        this.setMname(mname);
        this.setCast(cast);
        this.setImage(img);
        this.setP(p);
        this.setD(d);
        this.setDesc(desc);
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getCast() {
        return cast;
    }

    public void setImage(String image) {
        this.img = image;
    }

    public String getImage() {
        return img;
    }

    public void setP(String p) {
        this.p = p;
    }

    public void setD(String d) {
        this.d = d;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getP() {
        return p;
    }

    public String getD() {
        return d;
    }

    public String getDesc() {
        return desc;
    }
}
