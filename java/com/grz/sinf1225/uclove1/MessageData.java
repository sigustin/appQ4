package com.grz.sinf1225.uclove1;

/**
 * Created by simon on 4/05/16.
 */
public class MessageData
{
    public String m_senderPseudo, m_message;
    public boolean m_messageRead;

    public MessageData(String senderPseudo, String message, boolean messageRead)
    {
        m_senderPseudo = senderPseudo;
        m_message = message;
        m_messageRead = messageRead;
    }
}
