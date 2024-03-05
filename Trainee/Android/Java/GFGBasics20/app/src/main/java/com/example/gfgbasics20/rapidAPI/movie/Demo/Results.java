package com.example.gfgbasics20.rapidAPI.movie.Demo;
import java.util.List;

import com.google.gson.annotations.SerializedName;

   
public class Results {

   @SerializedName("imageurl")
   List<String> imageurl;

   @SerializedName("genre")
   List<String> genre;

   @SerializedName("imdbid")
   String imdbid;

   @SerializedName("title")
   String title;

   @SerializedName("imdbrating")
   double imdbrating;

   @SerializedName("released")
   int released;

   @SerializedName("type")
   String type;

   @SerializedName("synopsis")
   String synopsis;


    public void setImageurl(List<String> imageurl) {
        this.imageurl = imageurl;
    }
    public List<String> getImageurl() {
        return imageurl;
    }
    
    public void setGenre(List<String> genre) {
        this.genre = genre;
    }
    public List<String> getGenre() {
        return genre;
    }
    
    public void setImdbid(String imdbid) {
        this.imdbid = imdbid;
    }
    public String getImdbid() {
        return imdbid;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    
    public void setImdbrating(double imdbrating) {
        this.imdbrating = imdbrating;
    }
    public double getImdbrating() {
        return imdbrating;
    }
    
    public void setReleased(int released) {
        this.released = released;
    }
    public int getReleased() {
        return released;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
    public String getSynopsis() {
        return synopsis;
    }
    
}