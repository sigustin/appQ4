package com.grz.sinf1225.uclove1.Matching;

import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 * Created by simon on 6/05/16.
 */
public class Filter implements Serializable
{
    public static String EXTRA_FILTER = "UCLove.FILTER";

    //private int age = new int;
    private String pseudo;
    private String firstName;
    private String familyName;
    private String birthDate;
    private String gender;
    private String loveStatus;
    private String interestedIn;
    private double height;
    private String smoker;
    private int childrenNb;
    private String country;
    private String city;

    public Filter(String pseudo, String firstName, String familyName, String birthDate, String gender, String loveStatus, double height, String smoker,
                  int childrenNb, String country, String city)
    {
        this.pseudo = pseudo;
        this.firstName = firstName;
        this.familyName = familyName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.loveStatus = loveStatus;
        this.height = height;
        this.smoker = smoker;
        this.childrenNb = childrenNb;
        this.country = country;
        this.city = city;
    }

    public String getPseudo()
    {
        return this.pseudo;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public String getFamilyName()
    {
        return  this.familyName;
    }

    public String getBirthDate()
    {
        return this.birthDate;
    }

    public String getGender()
    {
        return this.gender;
    }

    public String getLoveStatus()
    {
        return this.loveStatus;
    }

    public double getHeight()
    {
        return this.height;
    }

    public String getSmoker()
    {
        return this.smoker;
    }

    public int getChildrenNb()
    {
        return this.childrenNb;
    }

    public String getCountry()
    {
        return this.country;
    }

    public String getCity()
    {
        return this.city;
    }
}
