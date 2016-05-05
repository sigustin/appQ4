package com.grz.sinf1225.uclove1;

import android.provider.BaseColumns;

/**
 * Created by simon on 5/05/16.
 */
public final class Database
{
    public static final class Contract
    {
        public Contract() {}

        public static abstract class UserEntries implements BaseColumns
        {
            public static final String TABLE_NAME = "User";
            public static final String COL_NAME_PSEUDO = "Pseudo", COL_NAME_LAST_NAME = "LastName", COL_NAME_FIRST_NAME = "FirstName",
                    COL_NAME_BIRTH_DATE = "BirthDate", COL_NAME_GENDER = "Gender", COL_NAME_LOVE_STATUS = "LoveStatus",
                    COL_NAME_REGISTRATION_DATE = "RegistrationDate", COL_NAME_HEIGHT = "Height", COL_NAME_DESCRIPTION = "Description",
                    COL_NAME_SMOKER = "Smoker", COL_NAME_INTERESTED_IN = "IneterstedIn", COL_NAME_PROFILE_PICTURE = "ProfilePicture",
                    COL_NAME_CHILDREN_NB = "ChildrenNb", COL_NAME_COUNTRY = "Country", COL_NAME_CITY = "City", COL_NAME_PASSWORD = "Password";
            public static final String COL_NAME_LAST_NAME_VISIBILITY = "LastNameVisibility", COL_NAME_FIRST_NAME_VISIBILITY = "FirstNameVisibility",
                    COL_NAME_BIRTH_DATE_VISIBILITY = "BirthDateVisibility", COL_NAME_GENDER_VISIBILITY = "GenderVisibility",
                    COL_NAME_LOVE_STATUS_VISIBILITY = "LoveStatusVisibility", COL_NAME_HEIGHT_VISIBILITY = "HeightVisibility",
                    COL_NAME_SMOKER_VISIBILITY = "SmokerVisibility", COL_NAME_CHILDREN_NB_VISIBILITY = "ChildrenNbVisibility",
                    COL_NAME_CITY_VISIBILITY = "CityVisibility";
        }
    }
}
