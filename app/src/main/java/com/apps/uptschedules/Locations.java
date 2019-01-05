package com.apps.uptschedules;

class Locations {
    public int imageId;
    public String address;
    public String name;

    public Locations() {
    }

    public Locations(String name, String address, int imageId) {
        this.imageId = imageId;
        this.address = address;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Locations{" +
                "imageId=" + imageId +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
