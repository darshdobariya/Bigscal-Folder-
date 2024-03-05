package com.example.gfgbasics.database.firebase.firestore.introslider;

public class SliderModel {

    // string variable for storing title,
    // image url and description.
    private String title;
    private String heading;
    private String imgUrl;

    public SliderModel() {
        // empty constructor is required
        // when using firebase
    }

    // creating getter methods.
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeading() {
        return heading;
    }

    // creating setter methods
    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    // constructor for our modal class
    public SliderModel(String title, String heading, String imgUrl) {
        this.title = title;
        this.heading = heading;
        this.imgUrl = imgUrl;
    }
}
