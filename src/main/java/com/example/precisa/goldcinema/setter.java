package com.example.precisa.goldcinema;

/**
 * Created by Precisa on 01-Apr-18.
 */

public class setter {
    private String mname,lang,img,type;
    public setter(String mname,String lang,String img,String type)
    {
        this.setMname(mname);
        this.setLang(lang);
        this.setImage(img);
        this.setType(type);
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }

    public void setImage(String image) {
        this.img = image;
    }

    public String getImage() {
        return img;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
