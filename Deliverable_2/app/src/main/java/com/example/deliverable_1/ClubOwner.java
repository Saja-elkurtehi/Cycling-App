package com.example.deliverable_1;

public class ClubOwner {
    private String socialMedia;
    private String name;
    private String phone;

    // Required default constructor for Firebase
    public ClubOwner() {
    }

    public ClubOwner(String socialMedia, String name, String phone) {
        this.socialMedia = socialMedia;
        this.name = name;
        this.phone = phone;
    }

    public String getSocialMedia() {
        return socialMedia;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

}

