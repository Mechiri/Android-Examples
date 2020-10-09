package com.example.week4lab;

public class RecycleViewList {

    private String image;
    private String imageName;

    public RecycleViewList(String image, String imageName) {
        this.image = image;
        this.imageName = imageName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String ifImage) {
        this.image = ifImage;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String ifImageName) {
        this.imageName = ifImageName;
    }
}
