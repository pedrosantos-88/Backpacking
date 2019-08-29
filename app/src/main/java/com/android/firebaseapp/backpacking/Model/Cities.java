package com.android.firebaseapp.backpacking.Model;

import android.widget.ImageView;

public class Cities {
    int ImageCity;
    String NameCity;
    String NameCountry;
    String AboutCity;

    public Cities() {
    }

    public void setAboutCity(String aboutCity) {
        this.AboutCity = aboutCity;
    }



    public int getImageCity() {
        return ImageCity;
    }

    public void setImageCity(int imageCity) {
        ImageCity = imageCity;
    }

    public String getNameCity() {
        return NameCity;
    }

    public void setNameCity(String nameCity) {
        NameCity = nameCity;
    }

    public String getNameCountry() {
        return NameCountry;
    }

    public void setNameCountry(String nameCountry) {
        NameCountry = nameCountry;
    }

    public String getAboutCity() {
        return AboutCity;
    }

    public Cities(int imageCity, String nameCity , String nameCountry , String aboutCity) {
        ImageCity = imageCity;
        NameCity = nameCity;
        NameCountry = nameCountry;
         AboutCity = aboutCity;

    }

}
