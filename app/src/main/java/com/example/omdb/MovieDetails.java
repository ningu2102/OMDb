package com.example.omdb;

public class MovieDetails
{
    String title, year, imageurl;

    public  MovieDetails()
    {

    }
    public MovieDetails(String title, String year, String imageurl) {
        this.title = title;
        this.year = year;
        this.imageurl = imageurl;
    }






    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
