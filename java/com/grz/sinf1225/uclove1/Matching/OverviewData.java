package com.grz.sinf1225.uclove1.Matching;

import com.grz.sinf1225.uclove1.User;

/**
 * Created by simon on 3/05/16.
 */
public class OverviewData
{
    public int m_profilePictureRes;
    public String m_pseudo, m_age, m_city;
    public User.RelationshipType m_relationshipStatus;

    public OverviewData(int profilePictureRes, String pseudo, String age, String city, User.RelationshipType relationshipStatus)
    {
        m_profilePictureRes = profilePictureRes;
        m_pseudo = pseudo;
        m_age = age;
        m_city = city;
        m_relationshipStatus = relationshipStatus;
    }
}
