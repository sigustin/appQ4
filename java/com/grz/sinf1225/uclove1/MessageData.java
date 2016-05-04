package com.grz.sinf1225.uclove1;

/**
 * Created by simon on 4/05/16.
 */
public class MessageData
{
    public String m_senderPseudo, m_message, m_sendingDate, m_readingDate;

    public MessageData(String senderPseudo, String message, String sendingDate, String readingDate)
    {
        m_senderPseudo = senderPseudo;
        m_message = message;
        m_sendingDate = sendingDate;
        m_readingDate = readingDate;
    }
}
