package com.example.yaatris;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class AdvnetureModel {
    public String imageName;
    public String imageURL;
    public int image;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String adventureName;
    public String sponsor;
    public String location;
    public String checkpoints;
    public String from;
    public String to;
    public String desc;
    public String price;
    public String cmail;

    public String getImageName() {
        return imageName;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        Adventures a = new Adventures();
        ArrayList<Company> companies = new ArrayList<>();
        companies = a.getCompanies();
//        int randomNum = ThreadLocalRandom.current().nextInt(0, companies.size() );
//        this.sponsor = companies.get(randomNum).name;
        for(int i =0 ; i< companies.size(); i++){
            String compmail = companies.get(i).email;
            if(compmail.equals(sponsor)){
                this.sponsor = companies.get(i).name;
                break;
            }
        }
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getAdventureName() {
        return adventureName;
    }

    public void setAdventureName(String adventureName) {
        this.adventureName = adventureName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(String checkpoints) {
        this.checkpoints = checkpoints;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCmail() {
        return cmail;
    }

    public void setCmail(String cmail) {
        this.cmail = cmail;
    }
}
