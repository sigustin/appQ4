package com.grz.sinf1225.uclove1.Dating;

import android.content.Context;

import com.grz.sinf1225.uclove1.Database;
import com.grz.sinf1225.uclove1.R;
import com.grz.sinf1225.uclove1.User;

/**
 * Created by simon on 5/05/16.
 */
public class Meeting
{
    private String[] pseudos = new String[2];
    private String meetingDay, location;

    public Meeting(String pseudo1, String pseudo2, String location)
    {
        this.pseudos[0] = pseudo1;
        this.pseudos[1] = pseudo2;
        this.location = location;
    }

    public String[] getUserPseudos()
    {
        return this.pseudos;
    }

    public String getDate()
    {
        return this.meetingDay;
    }

    public String getLocation()
    {
        return this.location;
    }

    public void setMeetingDay(String meetingDay)
    {
        this.meetingDay = meetingDay;
        Database.updateMeetingDay(this, meetingDay);
    }

    public void setLocation(String location)
    {
        this.location = location;
        Database.updateLocation(this, location);
    }

    public boolean[] findSameDisponibilities()
    {
        boolean[] disponibilityUser1 = Database.getDisponibilityDates(this.pseudos[0]);
        boolean[] disponibilityUser2 = Database.getDisponibilityDates(this.pseudos[1]);
        boolean[] sameDisponibility = new boolean[7];

        for(int i = 0; i < 7; i++)
        {
            sameDisponibility[i] = disponibilityUser1[i] && disponibilityUser2[i];
        }

        return sameDisponibility;
    }

    public boolean arrangeDay(Context context)
    {
        boolean[] possibleDays = this.findSameDisponibilities();
        boolean foundDate = false;
        for (int i=0; i<7; i++)
        {
            if (possibleDays[i] == true)
            {
                switch (i)
                {
                    case 0:
                        this.meetingDay = context.getResources().getString(R.string.monday);
                        break;
                    case 1:
                        this.meetingDay = context.getResources().getString(R.string.tuesday);
                        break;
                    case 2:
                        this.meetingDay = context.getResources().getString(R.string.wednesday);
                        break;
                    case 3:
                        this.meetingDay = context.getResources().getString(R.string.thursday);
                        break;
                    case 4:
                        this.meetingDay = context.getResources().getString(R.string.friday);
                        break;
                    case 5:
                        this.meetingDay = context.getResources().getString(R.string.saturday);
                        break;
                    case 6:
                        this.meetingDay = context.getResources().getString(R.string.sunday);
                        break;
                }
                foundDate = true;
            }
        }

        return foundDate;
    }

    public void save()
    {
        Database.addMeeting(this);
    }
}
