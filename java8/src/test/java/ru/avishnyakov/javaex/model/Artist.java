package ru.avishnyakov.javaex.model;

public class Artist {
    private String name;
    private String city;

    public Artist() {
    }

    public Artist(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isFrom(String city) {
        return this.city.equals(city);
    }
}
