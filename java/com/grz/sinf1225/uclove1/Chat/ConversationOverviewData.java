package com.grz.sinf1225.uclove1.Chat;

/**
 * Created by simon on 4/05/16.
 */
public class ConversationOverviewData
{
    public int m_profilePictureRes;
    public String m_pseudo, m_lastMessage;
    public boolean m_lastMessageRead;

    public ConversationOverviewData(int profilePictureRes, String pseudo, String lastMessage, boolean lastMessageRead)
    {
        m_profilePictureRes = profilePictureRes;
        m_pseudo = pseudo;
        m_lastMessage = lastMessage;
        m_lastMessageRead = lastMessageRead;
    }
}
