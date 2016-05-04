package com.grz.sinf1225.uclove1;

/**
 * Created by simon on 3/05/16.
 */
public class OverviewData
{
    static final int REQUEST = 0, FRIEND = 1, REJECTION = 2, ONESELF = 3;

    public int m_profilePictureRes;
    public String m_pseudo, m_age, m_city;
    public int m_relationshipStatus;

    public OverviewData(int profilePictureRes, String pseudo, String age, String city, int relationshipStatus)
    {
        m_profilePictureRes = profilePictureRes;
        m_pseudo = pseudo;
        m_age = age;
        m_city = city;
        m_relationshipStatus = relationshipStatus;
    }
}
