package com.grz.sinf1225.uclove1.Dating;

import java.util.List;

/**
 * Created by simon on 4/05/16.
 */
public class DateData
{
    public String m_friendPseudo, m_dateTime;
    public int m_friendProfilePictureRes;

    public DateData(String friendPseudo, int friendProfilePictureRes, String dateTime)
    {
        m_friendPseudo = friendPseudo;
        m_friendProfilePictureRes = friendProfilePictureRes;
        m_dateTime = dateTime;
    }
}
