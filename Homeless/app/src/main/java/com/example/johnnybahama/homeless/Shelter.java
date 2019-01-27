package com.example.johnnybahama.homeless;


import android.location.Location;

//import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class Shelter {

    private Double lat;
    private Double lng;
    private String name;
    private int numPosts;
    public String description;
    private String ID;
    private ArrayList<soloPost> soloPost = new ArrayList<soloPost>();
    private ArrayList<com.example.johnnybahama.homeless.groupPost> groupPost = new ArrayList<com.example.johnnybahama.homeless.groupPost>();


    //private int votes;



    //private ArrayList<Comment> comments = new ArrayList<Comment>();


    public Shelter(Double lat, Double lng, String name, int numPosts, String description){
        this.lat = lat;
        this.lng = lng;
        this.description = description;
        this.name = name;
        this.numPosts = numPosts;

    }




    //public void upVote(){
    //  votes ++;
    //}
    //public void downVote(){
    //  votes --;
    //}

    public void editBody(String description) {
        this.description = description ;
    }

    public void editName(String name) {
        this.name = name ;
    }

    public int getNumPosts() {
        return numPosts;
    }

    public void setLocation(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }


    public Double getLat(){ return lat;}
    public Double getLng(){ return lng;}

//    public void deleteComment(Comment Comment){
//        comments.remove(Comment);
//    }
//
//    public void addComment(Comment Comment){
//        comments.add(Comment);
//    }


    //public int getVotes() {
    //  return votes;
    //}

    public String getName() {
        return this.name;
    }




    public String getDescription(){
        return this.description;
    }

    public String getID(){
        return this.ID;
    }

    public void setID(String ID){
        this.ID = ID;
    }

    public ArrayList<com.example.johnnybahama.homeless.groupPost> getGroupPost() {
        return groupPost;
    }

    public ArrayList<com.example.johnnybahama.homeless.soloPost> getSoloPost() {
        return soloPost;
    }
/*
    public boolean isInRegion(LatLng middleScreen, int radius){
//        if(thisproperty == false)return false

        float distanceFloats[] = new float[] {1};
        Location.distanceBetween(middleScreen.latitude,middleScreen.latitude, lat, lng, distanceFloats);

        if(distanceFloats[0] < radius) {
            return true;
        }
        return false;

    }*/


}

