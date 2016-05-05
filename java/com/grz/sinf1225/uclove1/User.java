package com.grz.sinf1225.uclove1;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;

public class User
{
    public static final int PRIVATE = 0;
    public static final int FRIENDS = 1;
    public static final int PUBLIC = 2;

    public static final  int DEFAULT_VISIBILITY = PUBLIC;

    private String pseudo;
    private String familyName;
    private String firstName;
    private String birthDate;
    private String gender;
    private String loveStatus;
    private String registrationDate;
    private double height;
    private String description;
    private boolean smoker;
    private String interestedIn;
    private byte[] profilPicture;
    private List<byte[]> picture = new ArrayList<byte[]>();
    private int childrenNb;
    private String country;
    private String city;
    private boolean[] disponibility = new boolean[7];
    private int familyNameVisibility;
    private int firstNameVisibility;
    private int birthDateVisibility;
    private int genderVisibility;
    private int loveStatusVisibility;
    private int heightVisibility;
    private int smokerVisibility;
    private int childrenNbVisibility;
    private int cityVisibility;

    public User(String pseudo, String firstName, String familyName, String birthDate, String gender, String loveStatus, String registrationDate,
                double height, String description, boolean smoker, String interestedIn, byte[] profilePicture, List<byte[]> pictures, int childrenNb,
                String country, String city, boolean[] disponibility, int familyNameVisibility, int firstNameVisibility, int birthDateVisibility,
                int genderVisibility, int loveStatusVisibility, int heightVisibility, int smokerVisibility, int childrenNbVisibility, int cityVisibility)
    {
        this.pseudo = pseudo;
        this.familyName = familyName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.loveStatus = loveStatus;
        this.registrationDate = registrationDate;
        this.height = height;
        this.description = description;
        this.smoker = smoker;
        this.interestedIn = interestedIn;
        this.profilPicture = profilePicture;
        this.picture = pictures;
        this.childrenNb = childrenNb;
        this.country = country;
        this.city = city;
        this.disponibility = disponibility;
        this.familyNameVisibility = familyNameVisibility;
        this.firstNameVisibility = firstNameVisibility;
        this.birthDateVisibility = birthDateVisibility;
        this.genderVisibility = genderVisibility;
        this.loveStatusVisibility = loveStatusVisibility;
        this.heightVisibility = heightVisibility;
        this.smokerVisibility = smokerVisibility;
        this.childrenNbVisibility = childrenNbVisibility;
        this.cityVisibility = cityVisibility;
    }

    public User(String pseudo)
    {
        this.familyName = Database.getFamilyName(pseudo);
        this.firstName = Database.getFirstName(pseudo);
        this.birthDate = Database.getBirthDate(pseudo);
        this.gender = Database.getGender(pseudo);
        this.loveStatus = Database.getLoveStatus(pseudo);
        this.registrationDate = Database.getRegistrationDate(pseudo);
        this.height = Database.getHeight(pseudo);
        this.description = Database.getDescription(pseudo);
        this.smoker = Database.getSmoker(pseudo);
        this.interestedIn = Database.getInterestedIn(pseudo);
        this.profilPicture = Database.getProfilePicture(pseudo);
        this.picture = Database.getPictures(pseudo);
        this.childrenNb = Database.getChildrenNb(pseudo);
        this.country = Database.getCountry(pseudo);
        this.city = Database.getCity(pseudo);
        this.disponibility = Database.getDisponibility(pseudo);
        this.familyNameVisibility = Database.getFamilyNameVisibility(pseudo);
        this.firstNameVisibility = Database.getFirstNameVisibility(pseudo);
        this.birthDateVisibility = Database.getBirthDateVisibility(pseudo);
        this.genderVisibility = Database.getGenderVisibility(pseudo);
        this.loveStatusVisibility = Database.getLoveStatusVisibility(pseudo);
        this.heightVisibility = Database.getHeightVisibility(pseudo);
        this.smokerVisibility = Database.getSmokerVisibility(pseudo);
        this.childrenNbVisibility = Database.getChildrenNbVisibility(pseudo);
        this.cityVisibility = Database.getCityVisibility(pseudo);
    }


    public String getPseudo()
    {
        return this.pseudo;
    }

    public String getFamilyName()
    {
        return this.familyName;
    }

    public String getFirstName()
    {
        return this.firstName;
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

    public String getRegistrationDate()
    {
        return this.registrationDate;
    }

    public double getHeight()
    {
        return this.height;
    }

    public String getDescription()
    {
        return this.description;
    }

    public boolean getSmoker()
    {
        return this.smoker;
    }

    public String getInterestedIn()
    {
        return this.interestedIn;
    }

    public byte[] getProfilePicture()
    {
        return this.profilPicture;
    }

    public List<byte[]> getPicture()
    {
        return this.picture;
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

    public boolean[] getDisponibility()
    {
        return this.disponibility;
    }

    public int getFamilyNameVisibility()
    {
        return this.familyNameVisibility;
    }

    public int getFirstNameVisibility()
    {
        return this.firstNameVisibility;
    }

    public int getBirthDateVisibility()
    {
        return this.birthDateVisibility;
    }

    public int getGenderVisibility()
    {
        return this.genderVisibility;
    }

    public int getLoveStatusVisibility()
    {
        return this.loveStatusVisibility;
    }

    public int getHeightVisibility()
    {
        return this.heightVisibility;
    }

    public int getSmokerVisibility()
    {
        return this.smokerVisibility;
    }

    public int getChildrenNbVisibility()
    {
        return this.childrenNbVisibility;
    }

    public int getCityVisibility()
    {
        return this.cityVisibility;
    }




    public void setFamilyName(String familyName)
    {
        this.familyName = familyName;
        Database.updateFamilyName(this, familyName);
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
        Database.updateFirstName(this, firstName);
    }

    public void setBirthDate(String birthDate)
    {
        this.birthDate = birthDate;
        Database.updateBirthDate(this, birthDate);
    }

    public void setGender(String gender)
    {
        this.gender = gender;
        Database.updateGender(this, gender);
    }

    public void setLoveStatus(String loveStatus)
    {
        this.loveStatus = loveStatus;
        Database.updateLoveStatus(this, loveStatus);
    }

    public void setHeight(double height)
    {
        this.height = height;
        Database.updateHeight(this, height);
    }

    public void setDescription(String description)
    {
        this.description = description;
        Database.updateDescription(this, description);
    }

    public void setSmoker(boolean smoker)
    {
        this.smoker = smoker;
        Database.updateSmoker(this, smoker);
    }

    public void setInterestedIn(String interestedIn)
    {
        this.interestedIn = interestedIn;
        Database.updateInterestedIn(this, interestedIn);
    }

    public void setProfilPicture(byte[] profilPicture)
    {
        this.profilPicture = profilPicture;
        Database.updateProfilePicture(this, profilPicture);
    }

    public void setChildrenNb(int childrenNb)
    {
        this.childrenNb = childrenNb;
        Database.updateChildrenNb(this, childrenNb);
    }

    public void setCountry(String country)
    {
        this.country = country;
        Database.updateCountry(this, country);
    }

    public void setCity(String city)
    {
        this.city = city;
        Database.updateCity(this, city);
    }

    public void setPassword(String password)
    {
        Database.updatePassword(this, password);
    }

    public void setDisponibility(boolean[] disponibility)
    {
        this.disponibility = disponibility;
        Database.updateDisponibility(this, disponibility);
    }

    public void setFamilyNameVisibility(int familyNameVisibility)
    {
        this.familyNameVisibility = familyNameVisibility;
        Database.updateFamilyNameVisibility(this, familyNameVisibility);
    }

    public void setFirstNameVisibility(int firstNameVisibility)
    {
        this.firstNameVisibility = firstNameVisibility;
        Database.updateFirstNameVisibility(this, firstNameVisibility);
    }

    public void setBirthDateVisibility(int birthDateVisibility)
    {
        this.birthDateVisibility = birthDateVisibility;
        Database.updateBirthDateVisibility(this, birthDateVisibility);
    }

    public void setGenderVisibility(int genderVisibility)
    {
        this.genderVisibility = genderVisibility;
        Database.updateGenderVisibility(this, genderVisibility);
    }

    public void setLoveStatusVisibility(int loveStatusVisibility)
    {
        this.loveStatusVisibility = loveStatusVisibility;
        Database.updateLoveStatusVisibility(this, loveStatusVisibility);
    }

    public void setHeightVisibility(int heightVisibility)
    {
        this.heightVisibility = heightVisibility;
        Database.updateHeightVisibility(this, heightVisibility);
    }

    public void setSmokerVisibility(int smokerVisibility)
    {
        this.smokerVisibility = smokerVisibility;
        Database.updateSmokerVisibility(this, smokerVisibility);
    }

    public void setChildrenNbVisibility(int childrenNbVisibility)
    {
        this.childrenNbVisibility = childrenNbVisibility;
        Database.updateChildrenNbVisibility(this, childrenNbVisibility);
    }

    public void setCityVisibility(int cityVisibility)
    {
        this.cityVisibility = cityVisibility;
        Database.updateCityVisibility(this, cityVisibility);
    }
}
