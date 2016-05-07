package com.grz.sinf1225.uclove1;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.grz.sinf1225.uclove1.Chat.ConversationOverviewData;
import com.grz.sinf1225.uclove1.Chat.MessageData;
import com.grz.sinf1225.uclove1.Dating.Meeting;
import com.grz.sinf1225.uclove1.Matching.Filter;

import java.security.PublicKey;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by simon on 5/05/16.
 */
public final class Database
{
    public static abstract class UserEntries implements BaseColumns
    {
        public static final String TABLE_NAME = "User";
        public static final String COL_PSEUDO = "Pseudo", COL_LAST_NAME = "LastName", COL_FIRST_NAME = "FirstName",
                COL_BIRTH_DATE = "BirthDate", COL_GENDER = "Gender", COL_LOVE_STATUS = "LoveStatus",
                COL_REGISTRATION_DATE = "RegistrationDate", COL_HEIGHT = "Height", COL_DESCRIPTION = "Description",
                COL_SMOKER = "Smoker", COL_INTERESTED_IN = "InterestedIn", COL_PROFILE_PICTURE = "ProfilePicture",
                COL_CHILDREN_NB = "ChildrenNb", COL_COUNTRY = "Country", COL_CITY = "City", COL_PASSWORD = "Password";
        public static final String COL_LAST_NAME_VISIBILITY = "LastNameVisibility", COL_FIRST_NAME_VISIBILITY = "FirstNameVisibility",
                COL_BIRTH_DATE_VISIBILITY = "BirthDateVisibility", COL_GENDER_VISIBILITY = "GenderVisibility",
                COL_LOVE_STATUS_VISIBILITY = "LoveStatusVisibility", COL_HEIGHT_VISIBILITY = "HeightVisibility",
                COL_SMOKER_VISIBILITY = "SmokerVisibility", COL_CHILDREN_NB_VISIBILITY = "ChildrenNbVisibility",
                COL_CITY_VISIBILITY = "CityVisibility";
    }

    public static abstract class RelationshipEntries implements BaseColumns
    {
        public static final String TABLE_NAME = "Relationship";
        public static final String COL_USER1 = "User1", COL_USER2 = "User2", COL_RELATIONSHIP_TYPE = "RelationshipType",
                COL_REQUEST_DATE = "RequestDate";
    }

    public static abstract class PicturesEntries implements BaseColumns
    {
        public static final String TABLE_NAME = "Pictures";
        public static final String COL_PSEUDO = "Pseudo", COL_PICTURE = "Picture";
    }

    public static abstract class MessageEntries implements BaseColumns
    {
        public static final String TABLE_NAME = "Message";
        public static final String COL_SENDER = "Sender", COL_SENDING_DATE = "SendingDate",
                COL_RECEIVER = "Receiver", COL_RECEPTION_DATE = "ReceptionDate", COL_MSG = "Msg";
    }

    public static abstract class DisponibilityEntries implements BaseColumns
    {
        public static final String TABLE_NAME = "Disponibility";
        public static final String COL_PSEUDO = "Pseudo", COL_DISPONIBILITY_DATE = "DisponibilityDate";
    }

    public static abstract class AppointmentEntries implements BaseColumns
    {
        public static final String TABLE_NAME = "Appointment";
        public static final String COL_USER1 = "User1", COL_USER2 = "User2",
                COL_DATE = "Date", COL_LOCATION = "Location";
    }

    public static final String SQL_CREATE_USER = "CREATE TABLE User(Pseudo TEXT NOT NULL PRIMARY KEY, LastName TEXT NOT NULL, FirstName TEXT NOT NULL, BirthDate DATE NOT NULL, Gender TEXT NOT NULL, LoveStatus TEXT NOT NULL DEFAULT 'Single', RegistrationDate DATE NOT NULL, Height INTEGER, Description TEXT, Smoker BOOLEAN, InterestedIn TEXT, ProfilePicture BLOB, ChildrenNb INTEGER, Country TEXT NOT NULL, City TEXT NOT NULL, Password TEXT NOT NULL, LastNameVisibility TEXT NOT NULL DEFAULT 'Public', FirstNameVisibility TEXT NOT NULL DEFAULT 'Public', BirthDateVisibility TEXT NOT NULL DEFAULT 'Public', GenderVisibility TEXT NOT NULL DEFAULT 'Public', LoveStatusVisibility TEXT NOT NULL DEFAULT 'Public', HeightVisibility TEXT NOT NULL DEFAULT 'Public', SmokerVisibility TEXT NOT NULL DEFAULT 'Public',  ChildrenNbVisibility TEXT NOT NULL DEFAULT 'Public', CityVisibility TEXT NOT NULL DEFAULT 'Public');";
    public static final String SQL_CREATE_RELATIONSHIP = "CREATE TABLE Relationship(User1 TEXT NOT NULL, User2 TEXT NOT NULL, RelationshipType TEXT NOT NULL DEFAULT 'Request', RequestDate TEXT NOT NULL, PRIMARY KEY (User1, User2), FOREIGN KEY (User1) REFERENCES User, FOREIGN KEY (User2) REFERENCES User);";
    public static final String SQL_CREATE_PICTURES = "CREATE TABLE Pictures(Pseudo TEXT NOT NULL, Picture BLOB NOT NULL, PRIMARY KEY (Pseudo, Picture), FOREIGN KEY (Pseudo) REFERENCES User);";
    public static final String SQL_CREATE_MESSAGE = "CREATE TABLE Message(Sender TEXT NOT NULL, SendingDate DATE NOT NULL, Receiver TEXT NOT NULL, ReceptionDate DATE, Msg TEXT NOT NULL, PRIMARY KEY (Sender, SendingDate, Receiver), FOREIGN KEY (Sender) REFERENCES User, FOREIGN KEY (Receiver) REFERENCES User);";
    public static final String SQL_CREATE_DISPONIBILITY = "CREATE TABLE Disponibility(Pseudo TEXT NOT NULL, DisponibilityDate DATE NOT NULL, PRIMARY KEY (Pseudo, DisponibilityDate), FOREIGN KEY (Pseudo) REFERENCES User);";
    public static final String SQL_CREATE_APPOINTMENT = "CREATE TABLE Appointment(User1 TEXT NOT NULL, User2 TEXT NOT NULL, Date DATE, Location TEXT, PRIMARY KEY (User1, User2, Date), FOREIGN KEY (User1) REFERENCES User, FOREIGN KEY (User2) REFERENCES User);";

    public static final String REQUEST = "Request", FRIENDS = "Friends", REJECTION = "Rejection";

    public static final String EXTRA_IS_REGISTRATION = "UCLove.REGISTRATION";

    public enum UserInformation
    {
        PSEUDO, LAST_NAME, FIRST_NAME, BIRTH_DATE, AGE, GENDER, LOVE_STATUS, REGISTRATION_DATE, HEIGHT, DESCRIPTION,
        SMOKER, INTERESTED_IN, PROFILE_PICTURE, CHILDREN_NB, COUNTRY, CITY, PASSWORD;
    }

    public static class DBHelper extends SQLiteOpenHelper
    {
        public static final int DATABASE_VERSION = 2;
        public static final String DATABASE_NAME = "UCLoveDB.sqlite";

        public DBHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(SQL_CREATE_USER);
            db.execSQL(SQL_CREATE_RELATIONSHIP);
            db.execSQL(SQL_CREATE_PICTURES);
            db.execSQL(SQL_CREATE_MESSAGE);
            db.execSQL(SQL_CREATE_DISPONIBILITY);
            db.execSQL(SQL_CREATE_APPOINTMENT);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS " + UserEntries.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + RelationshipEntries.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + PicturesEntries.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + MessageEntries.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + DisponibilityEntries.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + AppointmentEntries.TABLE_NAME);
            onCreate(db);
        }
    }


    private static DBHelper helper;
    private static SQLiteDatabase writeDB, readDB;

    public static void tmpDrop()
    {
        writeDB = helper.getWritableDatabase();
        writeDB.execSQL("DROP TABLE IF EXISTS " + AppointmentEntries.TABLE_NAME);
        writeDB.close();
    }

    public static void resetDatabase()
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + UserEntries.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RelationshipEntries.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PicturesEntries.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MessageEntries.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DisponibilityEntries.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + AppointmentEntries.TABLE_NAME);

        db.execSQL(SQL_CREATE_USER);
        db.execSQL(SQL_CREATE_RELATIONSHIP);
        db.execSQL(SQL_CREATE_PICTURES);
        db.execSQL(SQL_CREATE_MESSAGE);
        db.execSQL(SQL_CREATE_DISPONIBILITY);
        db.execSQL(SQL_CREATE_APPOINTMENT);
        db.close();
    }

    public static void tmpCreate()
    {
        readDB = helper.getReadableDatabase();
        readDB.execSQL(SQL_CREATE_APPOINTMENT);
        readDB.close();
    }

    public static void init(Context context)
    {
        if (helper == null)
            helper = new DBHelper(context);
    }

    public static void tmpWrite(int date)
    {
        ContentValues values = new ContentValues();
        values.put(DisponibilityEntries.COL_PSEUDO, "Testpseudo");
        values.put(DisponibilityEntries.COL_DISPONIBILITY_DATE, Integer.toString(date));

        writeDB = helper.getWritableDatabase();
        long newRowId = writeDB.insert(DisponibilityEntries.TABLE_NAME, null, values);
        writeDB.close();
    }

    public static void tmpRead()
    {
        String[] projection = {DisponibilityEntries.COL_PSEUDO, DisponibilityEntries.COL_DISPONIBILITY_DATE};
        String[] selectionArgs = {"Testpseudo"};

        readDB = helper.getReadableDatabase();
        Cursor c = readDB.query(DisponibilityEntries.TABLE_NAME, projection, DisponibilityEntries.COL_PSEUDO +"=?", selectionArgs, null, null, null, null);

        if (c.moveToFirst())
        {
            String pseudo = c.getString(c.getColumnIndexOrThrow(DisponibilityEntries.COL_PSEUDO));
            int date = c.getInt(c.getColumnIndexOrThrow(DisponibilityEntries.COL_DISPONIBILITY_DATE));

            Log.d("DB", pseudo + " " + date);

            c.close();
        }

        readDB.close();
    }

    public static void tmpUpdate(int newDate)
    {
        ContentValues values = new ContentValues();
        values.put(DisponibilityEntries.COL_DISPONIBILITY_DATE, newDate);

        /*writeDB = helper.getWritableDatabase();
        writeDB.update(DisponibilityEntries.TABLE_NAME, values, DisponibilityEntries.COL_PSEUDO +"=?", new String[] {"Testpseudo"});
        //writeDB.execSQL("UPDATE " +DisponibilityEntries.TABLE_NAME+ " SET " +DisponibilityEntries.COL_DISPONIBILITY_DATE+ "=" +newDate+ " WHERE " +DisponibilityEntries.COL_PSEUDO+ "='Testpseudo';");
        writeDB.close();*/

        tmpDelete("Testpseudo");
        tmpWrite(newDate);
    }

    public static void tmpDelete(String pseudo)
    {
        writeDB = helper.getWritableDatabase();
        writeDB.delete(DisponibilityEntries.TABLE_NAME, DisponibilityEntries.COL_PSEUDO +"=?", new String[] {pseudo});
        writeDB.close();
    }


    //User
    public static boolean isPseudoTaken(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_PSEUDO},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            cursor.close();
            return true;
        }
        Log.e("DB", "Cursor empty");
        return false;
    }

    public static void addNewUser(User newUser, String password)
    {
        if (newUser != null)
        {
            Log.d("DB", "Adding new user : " +newUser.getPseudo());

            ContentValues values = new ContentValues();
            values.put(UserEntries.COL_PSEUDO, newUser.getPseudo());
            values.put(UserEntries.COL_LAST_NAME, newUser.getFamilyName());
            values.put(UserEntries.COL_FIRST_NAME, newUser.getFirstName());
            values.put(UserEntries.COL_BIRTH_DATE, newUser.getBirthDate());
            values.put(UserEntries.COL_GENDER, newUser.getGender());
            values.put(UserEntries.COL_LOVE_STATUS, newUser.getLoveStatus());
            values.put(UserEntries.COL_REGISTRATION_DATE, newUser.getRegistrationDate());
            values.put(UserEntries.COL_HEIGHT, newUser.getHeight());
            values.put(UserEntries.COL_DESCRIPTION, newUser.getDescription());
            values.put(UserEntries.COL_SMOKER, newUser.getSmoker());
            values.put(UserEntries.COL_INTERESTED_IN, newUser.getInterestedIn());
            values.put(UserEntries.COL_PROFILE_PICTURE, newUser.getProfilePicture());
            values.put(UserEntries.COL_CHILDREN_NB, newUser.getChildrenNb());
            values.put(UserEntries.COL_COUNTRY, newUser.getCountry());
            values.put(UserEntries.COL_CITY, newUser.getCity());
            values.put(UserEntries.COL_PASSWORD, password);
            values.put(UserEntries.COL_LAST_NAME_VISIBILITY, newUser.getFamilyNameVisibility());
            values.put(UserEntries.COL_FIRST_NAME_VISIBILITY, newUser.getFirstNameVisibility());
            values.put(UserEntries.COL_BIRTH_DATE_VISIBILITY, newUser.getBirthDateVisibility());
            values.put(UserEntries.COL_GENDER_VISIBILITY, newUser.getGenderVisibility());
            values.put(UserEntries.COL_LOVE_STATUS_VISIBILITY, newUser.getLoveStatusVisibility());
            values.put(UserEntries.COL_HEIGHT_VISIBILITY, newUser.getHeightVisibility());
            values.put(UserEntries.COL_SMOKER_VISIBILITY, newUser.getSmokerVisibility());
            values.put(UserEntries.COL_CHILDREN_NB_VISIBILITY, newUser.getChildrenNbVisibility());
            values.put(UserEntries.COL_CITY_VISIBILITY, newUser.getCityVisibility());

            writeDB = helper.getWritableDatabase();
            writeDB.insert(UserEntries.TABLE_NAME, null, values);
            writeDB.close();
        }
    }

    public static String getFamilyName(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_LAST_NAME},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            String answer = cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_LAST_NAME));
            cursor.close();
            return answer;
        }
        Log.e("DB", "Cursor empty");
        return null;
    }

    public static String getFirstName(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_FIRST_NAME},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            String answer = cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_FIRST_NAME));
            cursor.close();
            return answer;
        }
        Log.e("DB", "Cursor empty");
        return null;
    }

    public static String getBirthDate(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_BIRTH_DATE},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            String answer = cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_BIRTH_DATE));
            cursor.close();
            return answer;
        }
        Log.e("DB", "Cursor empty");
        return null;
    }

    public static String getGender(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_GENDER},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            String answer = cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_GENDER));
            cursor.close();
            return answer;
        }
        Log.e("DB", "Cursor empty");
        return null;
    }

    public static String getLoveStatus(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_LOVE_STATUS},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            String answer = cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_LOVE_STATUS));
            cursor.close();
            return answer;
        }
        Log.e("DB", "Cursor empty");
        return null;
    }

    public static String getRegistrationDate(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_REGISTRATION_DATE},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            String answer = cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_REGISTRATION_DATE));
            cursor.close();
            return answer;
        }
        Log.e("DB", "Cursor empty");
        return null;
    }

    public static double getHeight(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_HEIGHT},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            double answer = cursor.getDouble(cursor.getColumnIndexOrThrow(UserEntries.COL_HEIGHT));
            cursor.close();
            return answer;
        }
        Log.e("DB", "Cursor empty");
        return 0.0;
    }

    public static String getDescription(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_DESCRIPTION},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            String answer = cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_DESCRIPTION));
            cursor.close();
            return answer;
        }
        Log.e("DB", "Cursor empty");
        return null;
    }

    public static boolean getSmoker(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_SMOKER},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            if (cursor.getInt(cursor.getColumnIndexOrThrow(UserEntries.COL_SMOKER)) == 1)
                return true;
            return false;
        }
        Log.e("DB", "Cursor empty");
        return false;
    }

    public static String getInterestedIn(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_INTERESTED_IN},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            String answer = cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_INTERESTED_IN));
            cursor.close();
            return answer;
        }
        Log.e("DB", "Cursor empty");
        return null;
    }

    public static int getProfilePicture(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_PROFILE_PICTURE},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            int answer = cursor.getInt(cursor.getColumnIndexOrThrow(UserEntries.COL_PROFILE_PICTURE));
            cursor.close();
            return answer;
        }
        Log.e("DB", "Cursor empty");
        return 0;
    }

    public static int getChildrenNb(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_CHILDREN_NB},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            int answer = cursor.getInt(cursor.getColumnIndexOrThrow(UserEntries.COL_CHILDREN_NB));
            cursor.close();
            return answer;
        }
        Log.e("DB", "Cursor empty");
        return 0;
    }

    public static String getCountry(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_COUNTRY},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            String answer = cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_COUNTRY));
            cursor.close();
            return answer;
        }
        Log.e("DB", "Cursor empty");
        return null;
    }

    public static String getCity(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_CITY},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            String answer = cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_CITY));
            cursor.close();
            return answer;
        }
        Log.e("DB", "Cursor empty");
        return null;
    }

    public static int getFamilyNameVisibility(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_LAST_NAME_VISIBILITY},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            String visibility = cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_LAST_NAME_VISIBILITY));
            cursor.close();
            if (visibility.equals("Private"))
                return User.PRIVATE;
            else if (visibility.equals("Friends"))
                return User.FRIENDS;
            else if (visibility.equals("Public"))
                return User.PUBLIC;
            else
                return -1;
        }
        Log.e("DB", "Cursor empty");
        return -1;
    }

    public static int getFirstNameVisibility(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_FIRST_NAME_VISIBILITY},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            String visibility = cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_FIRST_NAME_VISIBILITY));
            cursor.close();
            if (visibility.equals("Private"))
                return User.PRIVATE;
            else if (visibility.equals("Friends"))
                return User.FRIENDS;
            else if (visibility.equals("Public"))
                return User.PUBLIC;
            else
                return -1;
        }
        Log.e("DB", "Cursor empty");
        return -1;
    }

    public static int getBirthDateVisibility(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_BIRTH_DATE_VISIBILITY},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            String visibility = cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_BIRTH_DATE_VISIBILITY));
            cursor.close();
            if (visibility.equals("Private"))
                return User.PRIVATE;
            else if (visibility.equals("Friends"))
                return User.FRIENDS;
            else if (visibility.equals("Public"))
                return User.PUBLIC;
            else
                return -1;
        }
        Log.e("DB", "Cursor empty");
        return -1;
    }

    public static int getGenderVisibility(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_GENDER_VISIBILITY},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            String visibility = cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_GENDER_VISIBILITY));
            cursor.close();
            if (visibility.equals("Private"))
                return User.PRIVATE;
            else if (visibility.equals("Friends"))
                return User.FRIENDS;
            else if (visibility.equals("Public"))
                return User.PUBLIC;
            else
                return -1;
        }
        Log.e("DB", "Cursor empty");
        return -1;
    }

    public static int getLoveStatusVisibility(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_LOVE_STATUS_VISIBILITY},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            String visibility = cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_LOVE_STATUS_VISIBILITY));
            cursor.close();
            if (visibility.equals("Private"))
                return User.PRIVATE;
            else if (visibility.equals("Friends"))
                return User.FRIENDS;
            else if (visibility.equals("Public"))
                return User.PUBLIC;
            else
                return -1;
        }
        Log.e("DB", "Cursor empty");
        return -1;
    }

    public static int getHeightVisibility(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_HEIGHT_VISIBILITY},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            String visibility = cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_HEIGHT_VISIBILITY));
            cursor.close();
            if (visibility.equals("Private"))
                return User.PRIVATE;
            else if (visibility.equals("Friends"))
                return User.FRIENDS;
            else if (visibility.equals("Public"))
                return User.PUBLIC;
            else
                return -1;
        }
        Log.e("DB", "Cursor empty");
        return -1;
    }

    public static int getSmokerVisibility(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_SMOKER_VISIBILITY},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            String visibility = cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_SMOKER_VISIBILITY));
            cursor.close();
            if (visibility.equals("Private"))
                return User.PRIVATE;
            else if (visibility.equals("Friends"))
                return User.FRIENDS;
            else if (visibility.equals("Public"))
                return User.PUBLIC;
            else
                return -1;
        }
        Log.e("DB", "Cursor empty");
        return -1;
    }

    public static int getChildrenNbVisibility(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_CHILDREN_NB_VISIBILITY},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            String visibility = cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_CHILDREN_NB_VISIBILITY));
            cursor.close();
            if (visibility.equals("Private"))
                return User.PRIVATE;
            else if (visibility.equals("Friends"))
                return User.FRIENDS;
            else if (visibility.equals("Public"))
                return User.PUBLIC;
            else
                return -1;
        }
        Log.e("DB", "Cursor empty");
        return -1;
    }

    public static int getCityVisibility(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_CITY_VISIBILITY},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            String visibility = cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_CITY_VISIBILITY));
            cursor.close();
            if (visibility.equals("Private"))
                return User.PRIVATE;
            else if (visibility.equals("Friends"))
                return User.FRIENDS;
            else if (visibility.equals("Public"))
                return User.PUBLIC;
            else
                return -1;
        }
        Log.e("DB", "Cursor empty");
        return -1;
    }

    public static int getPasswordLength(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_PASSWORD},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
            return (cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_PASSWORD)).length());
        Log.e("DB", "Cursor empty");
        return 0;
    }


    public static void updateUser(String pseudo, String columnName, String newEntry)
    {
        ContentValues values = new ContentValues();
        values.put(columnName, newEntry);

        Log.d("DB", "Update " +pseudo+ " -> " +columnName+ " : " +newEntry);

        writeDB = helper.getWritableDatabase();
        writeDB.update(UserEntries.TABLE_NAME, values, UserEntries.COL_PSEUDO +"=?", new String[] {pseudo});
        writeDB.close();
    }

    public static void updateUser(String pseudo, String columnName, int newEntry)
    {
        ContentValues values = new ContentValues();
        values.put(columnName, newEntry);

        writeDB = helper.getWritableDatabase();
        writeDB.update(UserEntries.TABLE_NAME, values, UserEntries.COL_PSEUDO +"=?", new String[] {pseudo});
        writeDB.close();
    }

    public static void updateUser(String pseudo, String columnName, double newEntry)
    {
        ContentValues values = new ContentValues();
        values.put(columnName, newEntry);

        writeDB = helper.getWritableDatabase();
        writeDB.update(UserEntries.TABLE_NAME, values, UserEntries.COL_PSEUDO +"=?", new String[] {pseudo});
        writeDB.close();
    }

    public static void updateUser(String pseudo, String columnName, boolean newEntry)
    {
        ContentValues values = new ContentValues();
        values.put(columnName, newEntry);

        writeDB = helper.getWritableDatabase();
        writeDB.update(UserEntries.TABLE_NAME, values, UserEntries.COL_PSEUDO +"=?", new String[] {pseudo});
        writeDB.close();
    }

    public static void updateUser(String pseudo, String columnName, byte[] newEntry)
    {
        ContentValues values = new ContentValues();
        values.put(columnName, newEntry);

        writeDB = helper.getWritableDatabase();
        writeDB.update(UserEntries.TABLE_NAME, values, UserEntries.COL_PSEUDO +"=?", new String[] {pseudo});
        writeDB.close();
    }

    public static void updateFamilyName(User user, String newValue)
    {
        updateUser(user.getPseudo(), UserEntries.COL_LAST_NAME, newValue);
    }

    public static void updateFirstName(User user, String newValue)
    {
        updateUser(user.getPseudo(), UserEntries.COL_FIRST_NAME, newValue);
    }

    public static void updateBirthDate(User user, String newValue)
    {
        updateUser(user.getPseudo(), UserEntries.COL_BIRTH_DATE, newValue);
    }

    public static void updateGender(User user, String newValue)
    {
        updateUser(user.getPseudo(), UserEntries.COL_GENDER, newValue);
    }

    public static void updateLoveStatus(User user, String newValue)
    {
        updateUser(user.getPseudo(), UserEntries.COL_LOVE_STATUS, newValue);
    }

    public static void updateHeight(User user, double newValue)
    {
        updateUser(user.getPseudo(), UserEntries.COL_HEIGHT, newValue);
    }

    public static void updateDescription(User user, String newValue)
    {
        updateUser(user.getPseudo(), UserEntries.COL_DESCRIPTION, newValue);
    }

    public static void updateSmoker(User user, boolean newValue)
    {
        updateUser(user.getPseudo(), UserEntries.COL_SMOKER, newValue);
    }

    public static void updateInterestedIn(User user, String newValue)
    {
        updateUser(user.getPseudo(), UserEntries.COL_INTERESTED_IN, newValue);
    }

    public static void updateProfilePicture(User user, int newValue)
    {
        Log.d("DEBUG", "Updating profile picture to " +Integer.toString(newValue));
        updateUser(user.getPseudo(), UserEntries.COL_PROFILE_PICTURE, newValue);
    }

    public static void updateChildrenNb(User user, int newValue)
    {
        updateUser(user.getPseudo(), UserEntries.COL_CHILDREN_NB, newValue);
    }

    public static void updateCountry(User user, String newValue)
    {
        updateUser(user.getPseudo(), UserEntries.COL_COUNTRY, newValue);
    }

    public static void updateCity(User user, String newValue)
    {
        updateUser(user.getPseudo(), UserEntries.COL_CITY, newValue);
    }

    public static void updatePassword(User user, String newValue)
    {
        updateUser(user.getPseudo(), UserEntries.COL_PASSWORD, newValue);
    }

    public static void updateFamilyNameVisibility(User user, int newValue)
    {
        updateUser(user.getPseudo(), UserEntries.COL_LAST_NAME_VISIBILITY, newValue);
    }

    public static void updateFirstNameVisibility(User user, int newValue)
    {
        updateUser(user.getPseudo(), UserEntries.COL_FIRST_NAME_VISIBILITY, newValue);
    }

    public static void updateBirthDateVisibility(User user, int newValue)
    {
        updateUser(user.getPseudo(), UserEntries.COL_BIRTH_DATE_VISIBILITY, newValue);
    }

    public static void updateGenderVisibility(User user, int newValue)
    {
        updateUser(user.getPseudo(), UserEntries.COL_GENDER_VISIBILITY, newValue);
    }

    public static void updateLoveStatusVisibility(User user, int newValue)
    {
        updateUser(user.getPseudo(), UserEntries.COL_LOVE_STATUS_VISIBILITY, newValue);
    }

    public static void updateHeightVisibility(User user, int newValue)
    {
        updateUser(user.getPseudo(), UserEntries.COL_HEIGHT_VISIBILITY, newValue);
    }

    public static void updateSmokerVisibility(User user, int newValue)
    {
        updateUser(user.getPseudo(), UserEntries.COL_SMOKER_VISIBILITY, newValue);
    }

    public static void updateChildrenNbVisibility(User user, int newValue)
    {
        updateUser(user.getPseudo(), UserEntries.COL_CHILDREN_NB_VISIBILITY, newValue);
    }

    public static void updateCityVisibility(User user, int newValue)
    {
        updateUser(user.getPseudo(), UserEntries.COL_CITY_VISIBILITY, newValue);
    }

    public static void updateFamilyName(String pseudo, String newValue)
    {
        updateUser(pseudo, UserEntries.COL_LAST_NAME, newValue);
    }

    public static void updateFirstName(String pseudo, String newValue)
    {
        updateUser(pseudo, UserEntries.COL_FIRST_NAME, newValue);
    }

    public static void updateBirthDate(String pseudo, String newValue)
    {
        updateUser(pseudo, UserEntries.COL_BIRTH_DATE, newValue);
    }

    public static void updateGender(String pseudo, String newValue)
    {
        updateUser(pseudo, UserEntries.COL_GENDER, newValue);
    }

    public static void updateLoveStatus(String pseudo, String newValue)
    {
        updateUser(pseudo, UserEntries.COL_LOVE_STATUS, newValue);
    }

    public static void updateHeight(String pseudo, double newValue)
    {
        updateUser(pseudo, UserEntries.COL_HEIGHT, newValue);
    }

    public static void updateDescription(String pseudo, String newValue)
    {
        updateUser(pseudo, UserEntries.COL_DESCRIPTION, newValue);
    }

    public static void updateSmoker(String pseudo, boolean newValue)
    {
        updateUser(pseudo, UserEntries.COL_SMOKER, newValue);
    }

    public static void updateInterestedIn(String pseudo, String newValue)
    {
        updateUser(pseudo, UserEntries.COL_INTERESTED_IN, newValue);
    }

    public static void updateProfilePicture(String pseudo, int newValue)
    {
        updateUser(pseudo, UserEntries.COL_FIRST_NAME, newValue);
    }

    public static void updateChildrenNb(String pseudo, int newValue)
    {
        updateUser(pseudo, UserEntries.COL_CHILDREN_NB, newValue);
    }

    public static void updateCountry(String pseudo, String newValue)
    {
        updateUser(pseudo, UserEntries.COL_COUNTRY, newValue);
    }

    public static void updateCity(String pseudo, String newValue)
    {
        updateUser(pseudo, UserEntries.COL_CITY, newValue);
    }

    public static void updatePassword(String pseudo, String newValue)
    {
        updateUser(pseudo, UserEntries.COL_PASSWORD, newValue);
    }

    public static void updateFamilyNameVisibility(String pseudo, int newValue)
    {
        updateUser(pseudo, UserEntries.COL_LAST_NAME_VISIBILITY, newValue);
    }

    public static void updateFirstNameVisibility(String pseudo, int newValue)
    {
        updateUser(pseudo, UserEntries.COL_FIRST_NAME_VISIBILITY, newValue);
    }

    public static void updateBirthDateVisibility(String pseudo, int newValue)
    {
        updateUser(pseudo, UserEntries.COL_BIRTH_DATE_VISIBILITY, newValue);
    }

    public static void updateGenderVisibility(String pseudo, int newValue)
    {
        updateUser(pseudo, UserEntries.COL_GENDER_VISIBILITY, newValue);
    }

    public static void updateLoveStatusVisibility(String pseudo, int newValue)
    {
        updateUser(pseudo, UserEntries.COL_LOVE_STATUS_VISIBILITY, newValue);
    }

    public static void updateHeightVisibility(String pseudo, int newValue)
    {
        updateUser(pseudo, UserEntries.COL_HEIGHT_VISIBILITY, newValue);
    }

    public static void updateSmokerVisibility(String pseudo, int newValue)
    {
        updateUser(pseudo, UserEntries.COL_SMOKER_VISIBILITY, newValue);
    }

    public static void updateChildrenNbVisibility(String pseudo, int newValue)
    {
        updateUser(pseudo, UserEntries.COL_CHILDREN_NB_VISIBILITY, newValue);
    }

    public static void updateCityVisibility(String pseudo, int newValue)
    {
        updateUser(pseudo, UserEntries.COL_CITY_VISIBILITY, newValue);
    }

    public static boolean isRightPassword(String pseudo, String inputPassword)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_PASSWORD},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            boolean answer = false;
            if (inputPassword.equals(cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_PASSWORD))))
            {
                answer = true;
                Log.d("DB", "Password entered ok");
            }

            cursor.close();
            return answer;
        }
        Log.e("DB", "Cursor empty");
        return false;
    }

    public static List<User> findSimpleMatches(User user)
    {
        String interestedIn, interestedIn2;
        if (user.getInterestedIn().equals(MainActivity.getContext().getResources().getString(R.string.women)))
        {
            interestedIn = "F";
            interestedIn2 = "empty";
        }
        else if (user.getInterestedIn().equals(MainActivity.getContext().getResources().getString(R.string.men)))
        {
            interestedIn = "M";
            interestedIn2 = "empty";
        }
        else if (user.getInterestedIn().equals(MainActivity.getContext().getResources().getString(R.string.both)))
        {
            interestedIn = "F";
            interestedIn2 = "M";
        }
        else
            return null;

        String column;
        String[] columnArgs;
        if (interestedIn2.equals("empty"))
        {
            column = UserEntries.COL_GENDER+ "=?";
            columnArgs = new String[] {interestedIn};
        }
        else
        {
            column = UserEntries.COL_GENDER+ "=? OR " +UserEntries.COL_GENDER +"=?";
            columnArgs = new String[] {interestedIn, interestedIn2};
        }

        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_PSEUDO},
                column,
                columnArgs,
                null, null, null, null);

        List<User> matchesList = new ArrayList<User>();

        if (cursor.moveToFirst())
        {
            do
            {
                Log.d("DB", "Found new match : " +cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_PSEUDO)));
                User current;
                if ( getRelationshipType(user.getPseudo(), cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_PSEUDO))) != User.RelationshipType.FRIENDS
                        && getRelationshipType(user.getPseudo(), cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_PSEUDO))) != User.RelationshipType.ONESELF
                        && getRelationshipType(user.getPseudo(), cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_PSEUDO))) != User.RelationshipType.REJECTION )
                {
                    current = new User(cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_PSEUDO)));
                    matchesList.add(current);
                }
            } while(cursor.moveToNext());
        }
        return matchesList;
    }

    public static List<User> findMatchesWithFilters(User user, Filter filter)
    {
        String interestedIn, interestedIn2;
        if (user.getInterestedIn().equals(MainActivity.getContext().getResources().getString(R.string.women)))
        {
            interestedIn = "F";
            interestedIn2 = "empty";
        }
        else if (user.getInterestedIn().equals(MainActivity.getContext().getResources().getString(R.string.men)))
        {
            interestedIn = "M";
            interestedIn2 = "empty";
        }
        else if (user.getInterestedIn().equals(MainActivity.getContext().getResources().getString(R.string.both)))
        {
            interestedIn = "F";
            interestedIn2 = "M";
        }
        else
            return null;

        String column;
        String[] columnArgs;
        List<String> columnArgsTmp = new ArrayList<String>();
        if (interestedIn2.equals("empty"))
        {
            column = UserEntries.COL_GENDER+ "=?";
            columnArgsTmp.add(interestedIn);
        }
        else
        {
            column = UserEntries.COL_GENDER+ "=? OR " +UserEntries.COL_GENDER +"=?";
            columnArgsTmp.add(interestedIn);
            columnArgsTmp.add(interestedIn2);
        }

        if (filter.getPseudo() != null && !(filter.getPseudo().equals("")))
        {
            column += " AND "+ UserEntries.COL_PSEUDO +"=?";
            columnArgsTmp.add(filter.getPseudo());
        }
        if (filter.getFirstName() != null && !(filter.getFirstName().equals("")))
        {
            column += " AND "+ UserEntries.COL_FIRST_NAME +"=?";
            columnArgsTmp.add(filter.getFirstName());
        }
        if (filter.getFamilyName() != null && !(filter.getFamilyName().equals("")))
        {
            column += " AND "+ UserEntries.COL_LAST_NAME +"=?";
            columnArgsTmp.add(filter.getFamilyName());
        }
        if (filter.getBirthDate() != null && !(filter.getBirthDate().equals("")))
        {
            column += " AND "+ UserEntries.COL_BIRTH_DATE +"=?";
            columnArgsTmp.add(filter.getBirthDate());
        }
        if (filter.getGender() != null && !(filter.getGender().equals(MainActivity.getContext().getResources().getString(R.string.no_filter))))
        {
            column += " AND "+ UserEntries.COL_GENDER +"=?";
            columnArgsTmp.add(filter.getGender());
        }
        if (filter.getLoveStatus() != null && !(filter.getLoveStatus().equals(MainActivity.getContext().getResources().getString(R.string.no_filter))))
        {
            Log.d("DEBUG", filter.getLoveStatus() +" "+ MainActivity.getContext().getResources().getString(R.string.no_filter));
            if (filter.getLoveStatus().equals(MainActivity.getContext().getResources().getString(R.string.no_filter)))
                Log.d("DEBUG", "Equality");
            column += " AND "+ UserEntries.COL_LOVE_STATUS +"=?";
            columnArgsTmp.add(filter.getLoveStatus());
        }
        if (filter.getHeight() != 0.0)
        {
            column += " AND "+ UserEntries.COL_HEIGHT +"=?";
            columnArgsTmp.add(Double.toString(filter.getHeight()));
        }
        if (filter.getSmoker() != null && !(filter.getSmoker().equals(MainActivity.getContext().getResources().getString(R.string.no_filter))))
        {
            column += " AND "+ UserEntries.COL_SMOKER +"=?";
            columnArgsTmp.add(filter.getSmoker());
        }
        if (filter.getChildrenNb() != -1)
        {
            column += " AND "+ UserEntries.COL_CHILDREN_NB +"=?";
            columnArgsTmp.add(Integer.toString(filter.getChildrenNb()));
        }
        if (filter.getCountry() != null && !(filter.getCountry().equals(MainActivity.getContext().getResources().getString(R.string.no_filter))))
        {
            column += " AND "+ UserEntries.COL_COUNTRY +"=?";
            columnArgsTmp.add(filter.getCountry());
        }
        if (filter.getCity() != null  && !(filter.getCity().equals("")))
        {
            column += " AND "+ UserEntries.COL_CITY +"=?";
            columnArgsTmp.add(filter.getCity());
        }

        columnArgs = new String[columnArgsTmp.size()];
        columnArgsTmp.toArray(columnArgs);

        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_PSEUDO},
                column,
                columnArgs,
                null, null, null, null);

        for (int i=0; i<columnArgs.length; i++)
            Log.d("DB", "Query : " +column+ " -> " +columnArgs[i]);

        List<User> matchesList = new ArrayList<User>();

        if (cursor.moveToFirst())
        {
            do
            {
                Log.d("DB", "Found new match : " +cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_PSEUDO)));
                User current;
                if ( getRelationshipType(user.getPseudo(), cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_PSEUDO))) != User.RelationshipType.FRIENDS
                        && getRelationshipType(user.getPseudo(), cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_PSEUDO))) != User.RelationshipType.ONESELF
                        && getRelationshipType(user.getPseudo(), cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_PSEUDO))) != User.RelationshipType.REJECTION )
                {
                    current = new User(cursor.getString(cursor.getColumnIndexOrThrow(UserEntries.COL_PSEUDO)));
                    matchesList.add(current);
                }
            } while(cursor.moveToNext());
        }
        return matchesList;
    }


    //Relationship
    public static User.RelationshipType getRelationshipType(String pseudo1, String pseudo2)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(RelationshipEntries.TABLE_NAME,
                new String[] {RelationshipEntries.COL_RELATIONSHIP_TYPE},
                "(" +RelationshipEntries.COL_USER1+ "=? AND " +RelationshipEntries.COL_USER2+ "=?) OR (" +RelationshipEntries.COL_USER1+ "=? AND " +RelationshipEntries.COL_USER2+ "=?)",
                new String[] {pseudo1, pseudo2, pseudo2, pseudo1},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            String answer = cursor.getString( cursor.getColumnIndexOrThrow(RelationshipEntries.COL_RELATIONSHIP_TYPE) );
            cursor.close();
            if (answer.equals("Friends"))
                return User.RelationshipType.FRIENDS;
            else if (answer.equals("Request"))
                return User.RelationshipType.REQUEST;
            else if (answer.equals("Rejection"))
                return User.RelationshipType.REJECTION;
            else
                return User.RelationshipType.NONE;
        }
        Log.e("DB", "Cursor empty");
        return User.RelationshipType.NONE;
    }

    protected static String getVisibilityColumnName(UserInformation information)
    {
        switch (information)
        {
            case LAST_NAME:
                return UserEntries.COL_LAST_NAME_VISIBILITY;
            case FIRST_NAME:
                return UserEntries.COL_FIRST_NAME_VISIBILITY;
            case BIRTH_DATE:
                return UserEntries.COL_BIRTH_DATE_VISIBILITY;
            case AGE:
                return UserEntries.COL_BIRTH_DATE_VISIBILITY;
            case GENDER:
                return UserEntries.COL_GENDER_VISIBILITY;
            case SMOKER:
                return UserEntries.COL_SMOKER_VISIBILITY;
            case LOVE_STATUS:
                return UserEntries.COL_LOVE_STATUS_VISIBILITY;
            case HEIGHT:
                return UserEntries.COL_HEIGHT_VISIBILITY;
            case CHILDREN_NB:
                return UserEntries.COL_CHILDREN_NB_VISIBILITY;
            case CITY:
                return UserEntries.COL_CITY_VISIBILITY;
            default:
                return null;
        }
    }

    public static boolean isVisible(String pseudoUserToDisplay, UserInformation infoToDisplay, String currentUserPseudo)
    {
        if (currentUserPseudo.equals(pseudoUserToDisplay))
            return true;

        readDB = helper.getReadableDatabase();

        String columnName = getVisibilityColumnName(infoToDisplay);
        if (columnName == null)
            return false;

        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {columnName},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudoUserToDisplay},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            String visibility = cursor.getString(cursor.getColumnIndexOrThrow(columnName));
            if (visibility.equals("Public"))
                return true;
            else if (visibility.equals("Private"))
                return false;
            else if (visibility.equals("Friends"))
            {
                User.RelationshipType relationship = getRelationshipType(currentUserPseudo, pseudoUserToDisplay);
                if (relationship == User.RelationshipType.FRIENDS)
                    return true;
                else
                    return false;
            }
        }
        Log.e("DB", "Cursor empty");
        return false;
    }

    public static boolean hasSentRequest(String pseudoSrc, String pseudoDestination)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(RelationshipEntries.TABLE_NAME,
                new String[] {RelationshipEntries.COL_RELATIONSHIP_TYPE},
                "(" +RelationshipEntries.COL_USER1+ "=? AND " +RelationshipEntries.COL_USER2+ "=?) OR (" +RelationshipEntries.COL_USER1+ "=? AND " +RelationshipEntries.COL_USER2+ "=?)",
                new String[] {pseudoSrc, pseudoDestination},
                null, null, null, null);

        if (cursor.moveToFirst())
            return true;
        else
            return false;
    }

    public static void sendRequest(String pseudoSrc, String pseudoDestination)
    {
        Log.d("DB", pseudoSrc+ " sends " +pseudoDestination+ "a request.");

        ContentValues values = new ContentValues();
        values.put(RelationshipEntries.COL_USER1, pseudoSrc);
        values.put(RelationshipEntries.COL_USER2, pseudoDestination);
        values.put(RelationshipEntries.COL_RELATIONSHIP_TYPE, REQUEST);
        values.put(RelationshipEntries.COL_REQUEST_DATE, String.format("%1$td/%1$tm/%1$tY", Calendar.getInstance()));

        writeDB = helper.getWritableDatabase();
        writeDB.insert(RelationshipEntries.TABLE_NAME, null, values);
        writeDB.close();
    }

    public static void acceptRequest(String pseudoAsked, String pseudoSrc)
    {
        Log.d("DB", pseudoAsked+ " accepts " +pseudoSrc+ "'s request.");

        ContentValues values = new ContentValues();
        values.put(RelationshipEntries.COL_RELATIONSHIP_TYPE, FRIENDS);

        writeDB = helper.getWritableDatabase();
        writeDB.update(RelationshipEntries.TABLE_NAME,
                values,
                RelationshipEntries.COL_USER1 +"=? AND "+ RelationshipEntries.COL_USER2 +"=?",
                new String[] {pseudoSrc, pseudoAsked});
        writeDB.close();
    }

    public static void unfriend(String pseudo1, String pseudo2)
    {
        Log.d("DB", pseudo1+ " unfriends " +pseudo2+ "'s request.");

        writeDB = helper.getWritableDatabase();
        writeDB.delete(RelationshipEntries.TABLE_NAME,
                "("+ RelationshipEntries.COL_USER1 +"=? AND "+ RelationshipEntries.COL_USER2 +"=?) OR ("+ RelationshipEntries.COL_USER1 +"=? AND "+ RelationshipEntries.COL_USER2 +"=?)",
                new String[] {pseudo1, pseudo2, pseudo2, pseudo1});
        writeDB.close();
    }

    public static List<User> getFriendsAndRequests(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(RelationshipEntries.TABLE_NAME,
                new String[] {RelationshipEntries.COL_USER1, RelationshipEntries.COL_USER2},
                RelationshipEntries.COL_USER1+ "=? Or " +RelationshipEntries.COL_USER2+ "=?",
                new String[] {pseudo, pseudo},
                null, null, null, null);

        List<User> userList = new ArrayList<User>();

        if (cursor.moveToFirst())
        {
            do
            {
                User current;
                Log.d("DEBUG", "User1 : " +cursor.getString(cursor.getColumnIndexOrThrow(RelationshipEntries.COL_USER1))+ " User2 : " +cursor.getString(cursor.getColumnIndexOrThrow(RelationshipEntries.COL_USER2)));
                if ( !(cursor.getString(cursor.getColumnIndexOrThrow(RelationshipEntries.COL_USER1)).equals(pseudo)) ) {
                    Log.d("DEBUG", "Current : " +pseudo+ " Returning user 1 " +cursor.getString(cursor.getColumnIndexOrThrow(RelationshipEntries.COL_USER1)));
                    current = new User(cursor.getString(cursor.getColumnIndexOrThrow(RelationshipEntries.COL_USER1)));
                }
                else {
                    Log.d("DEBUG", "Returning user 2");
                    current = new User(cursor.getString(cursor.getColumnIndexOrThrow(RelationshipEntries.COL_USER2)));
                }
                userList.add(current);
            } while(cursor.moveToNext());
        }
        return userList;
    }


    //Meeting
    public static void addMeeting(Meeting meeting)
    {
        String[] pseudos = new String[2];
        pseudos = meeting.getUserPseudos();
        ContentValues values = new ContentValues();
        values.put(AppointmentEntries.COL_USER1, pseudos[0]);
        values.put(AppointmentEntries.COL_USER2, pseudos[1]);
        values.put(AppointmentEntries.COL_DATE, meeting.getDate());
        values.put(AppointmentEntries.COL_LOCATION, meeting.getLocation());

        Log.d("DB", "Adding new meeting : " +pseudos[0]+ " - " +pseudos[1]);

        writeDB = helper.getWritableDatabase();
        writeDB.insert(AppointmentEntries.TABLE_NAME, null, values);
        writeDB.close();
    }

    public static String getLocationMeeting(String pseudo1, String pseudo2)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(AppointmentEntries.TABLE_NAME,
                new String[] {AppointmentEntries.COL_LOCATION},
                "("+ AppointmentEntries.COL_USER1 +"=? AND "+ AppointmentEntries.COL_USER2 +"=?) OR ("+ AppointmentEntries.COL_USER1 +"=? AND "+ AppointmentEntries.COL_USER2 +"=?)",
                new String[] {pseudo1, pseudo2, pseudo2, pseudo1},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            return cursor.getString(cursor.getColumnIndexOrThrow(AppointmentEntries.COL_LOCATION));
        }
        return null;
    }

    public static void updateLocation(Meeting meeting, String location)
    {
        ContentValues values = new ContentValues();
        values.put(AppointmentEntries.COL_LOCATION, location);

        String[] pseudos = new String[2];
        pseudos = meeting.getUserPseudos();

        writeDB = helper.getWritableDatabase();
        writeDB.update(AppointmentEntries.TABLE_NAME,
                values,
                "("+ AppointmentEntries.COL_USER1 +"=? AND "+ AppointmentEntries.COL_USER2 +"=?) OR ("+ AppointmentEntries.COL_USER1 +"=? AND "+ AppointmentEntries.COL_USER2 +"=?)",
                new String[] {pseudos[0], pseudos[1], pseudos[1], pseudos[0]});
        writeDB.close();
    }

    public static void updateMeetingDay(Meeting meeting, String meetingDay)
    {
        ContentValues values = new ContentValues();
        values.put(AppointmentEntries.COL_DATE, meetingDay);

        String[] pseudos = new String[2];
        pseudos = meeting.getUserPseudos();

        writeDB = helper.getWritableDatabase();
        writeDB.update(AppointmentEntries.TABLE_NAME,
                values,
                "("+ AppointmentEntries.COL_USER1 +"=? AND "+ AppointmentEntries.COL_USER2 +"=?) OR ("+ AppointmentEntries.COL_USER1 +"=? AND "+ AppointmentEntries.COL_USER2 +"=?)",
                new String[] {pseudos[0], pseudos[1], pseudos[1], pseudos[0]});
        writeDB.close();
    }

    public static List<Meeting> getAllMeetings(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(AppointmentEntries.TABLE_NAME,
                new String[] {AppointmentEntries.COL_USER1, AppointmentEntries.COL_USER2, AppointmentEntries.COL_DATE, AppointmentEntries.COL_LOCATION},
                AppointmentEntries.COL_USER1+ "=? OR " +AppointmentEntries.COL_USER2+ "=?",
                new String[] {pseudo, pseudo},
                null, null, null, null);

        List<Meeting> meetingList = new ArrayList<Meeting>();

        if (cursor.moveToFirst())
        {
            Log.d("DB", "Found meetings");
            do
            {
                Log.d("DB", "Found new meeting");
                Meeting current = new Meeting(cursor.getString(cursor.getColumnIndexOrThrow(AppointmentEntries.COL_USER1)),
                        cursor.getString(cursor.getColumnIndexOrThrow(AppointmentEntries.COL_USER2)),
                        cursor.getString(cursor.getColumnIndexOrThrow(AppointmentEntries.COL_LOCATION)));
                current.setMeetingDay(cursor.getString(cursor.getColumnIndexOrThrow(AppointmentEntries.COL_DATE)));
                meetingList.add(current);
            } while(cursor.moveToNext());
        }
        return meetingList;
    }

    //Disponibilities
    public static void addDisponibilityDate(String pseudo, String date)
    {
        Log.d("DB", "Adding disponibility for " +pseudo);

        ContentValues values = new ContentValues();
        values.put(DisponibilityEntries.COL_PSEUDO, pseudo);
        values.put(DisponibilityEntries.COL_DISPONIBILITY_DATE, date);

        writeDB = helper.getWritableDatabase();
        writeDB.insert(DisponibilityEntries.TABLE_NAME, null, values);
        writeDB.close();
    }

    public static void updateDisponibility(String pseudo, String newDate)
    {
        ContentValues values = new ContentValues();
        values.put(DisponibilityEntries.COL_DISPONIBILITY_DATE, newDate);

        writeDB = helper.getWritableDatabase();
        writeDB.update(DisponibilityEntries.TABLE_NAME, values, DisponibilityEntries.COL_PSEUDO +"=?", new String[] {pseudo});
        writeDB.close();
    }

    public static boolean[] getDisponibilityDates(String pseudo, Context context)
    {
        Log.d("DB", "Getting all the disponibility for " +pseudo);

        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(DisponibilityEntries.TABLE_NAME,
                new String[] {DisponibilityEntries.COL_DISPONIBILITY_DATE},
                DisponibilityEntries.COL_PSEUDO+ "=?",
                new String[] {pseudo},
                null, null, null, null);

        boolean[] disponibilities = new boolean[7];

        if (cursor.moveToFirst())
        {
            int count = 0;
            do
            {
                if (cursor.getString(cursor.getColumnIndexOrThrow(DisponibilityEntries.COL_DISPONIBILITY_DATE)).equals(context.getResources().getString(R.string.monday)))
                    disponibilities[0] = true;
                else if (cursor.getString(cursor.getColumnIndexOrThrow(DisponibilityEntries.COL_DISPONIBILITY_DATE)).equals(context.getResources().getString(R.string.tuesday)))
                    disponibilities[1] = true;
                else if (cursor.getString(cursor.getColumnIndexOrThrow(DisponibilityEntries.COL_DISPONIBILITY_DATE)).equals(context.getResources().getString(R.string.wednesday)))
                    disponibilities[2] = true;
                else if (cursor.getString(cursor.getColumnIndexOrThrow(DisponibilityEntries.COL_DISPONIBILITY_DATE)).equals(context.getResources().getString(R.string.thursday)))
                    disponibilities[3] = true;
                else if (cursor.getString(cursor.getColumnIndexOrThrow(DisponibilityEntries.COL_DISPONIBILITY_DATE)).equals(context.getResources().getString(R.string.friday)))
                    disponibilities[4] = true;
                else if (cursor.getString(cursor.getColumnIndexOrThrow(DisponibilityEntries.COL_DISPONIBILITY_DATE)).equals(context.getResources().getString(R.string.saturday)))
                    disponibilities[5] = true;
                else if (cursor.getString(cursor.getColumnIndexOrThrow(DisponibilityEntries.COL_DISPONIBILITY_DATE)).equals(context.getResources().getString(R.string.sunday)))
                    disponibilities[6] = true;
                count++;
            } while(cursor.moveToNext());
        }
        if (disponibilities == null)
            Log.d("DB", "Not found any");
        return disponibilities;
    }

    public static void removeDisponibilities(String pseudo)
    {
        writeDB = helper.getWritableDatabase();
        writeDB.delete(DisponibilityEntries.TABLE_NAME,
                DisponibilityEntries.COL_PSEUDO +"=?",
                new String[] {pseudo});
        writeDB.close();
    }


    //Pictures
    public static void addPicture(String pseudo, int pictureRes)
    {
        ContentValues values = new ContentValues();
        values.put(PicturesEntries.COL_PSEUDO, pseudo);
        values.put(PicturesEntries.COL_PICTURE, Integer.toString(pictureRes));

        Log.d("DB", "Adding picture : " +pseudo);

        writeDB = helper.getWritableDatabase();
        writeDB.insert(PicturesEntries.TABLE_NAME, null, values);
        writeDB.close();
    }

    public static boolean isPictureBoundToUser(String pseudo, int pictureRes)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(PicturesEntries.TABLE_NAME,
                new String[] {PicturesEntries.COL_PICTURE},
                PicturesEntries.COL_PSEUDO +"=? AND "+ PicturesEntries.COL_PICTURE +"=?",
                new String[] {pseudo, Integer.toString(pictureRes)},
                null, null, null, null);

        Log.d("DB", "Searched for " +pictureRes);

        if (cursor.moveToFirst())
        {
            Log.d("DB", "Picture found : " +cursor.getString(cursor.getColumnIndexOrThrow(PicturesEntries.COL_PICTURE))+ "Picture searched : " +Integer.toString(pictureRes));
            return (cursor.getInt(cursor.getColumnIndexOrThrow(PicturesEntries.COL_PICTURE)) == pictureRes);
        }
        return false;
    }

    public static void removePictureBond(String pseudo, int pictureRes)
    {
        Log.d("DB", "Removing picture : " +pseudo);

        writeDB = helper.getWritableDatabase();
        writeDB.delete(PicturesEntries.TABLE_NAME,
                PicturesEntries.COL_PSEUDO +"=? AND "+ PicturesEntries.COL_PICTURE +"=?",
                new String[] {pseudo, Integer.toString(pictureRes)});
        writeDB.close();
    }

    public static List<Integer> getPicturesRes(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(PicturesEntries.TABLE_NAME,
                new String[] {PicturesEntries.COL_PICTURE},
                PicturesEntries.COL_PSEUDO+ "=?",
                new String[] {pseudo},
                null, null, null, null);

        List<Integer> pictures = new ArrayList<Integer>();

        if (cursor.moveToFirst())
        {
            do
            {
                int current = cursor.getInt(cursor.getColumnIndexOrThrow(PicturesEntries.COL_PICTURE));
                pictures.add(current);
            } while(cursor.moveToNext());
        }
        return pictures;
    }


    //Messages
    public static List<String> getAllInterlocutors(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(MessageEntries.TABLE_NAME,
                new String[] {MessageEntries.COL_SENDER, MessageEntries.COL_RECEIVER},
                MessageEntries.COL_SENDER+ "=? Or " +MessageEntries.COL_RECEIVER+ "=?",
                new String[] {pseudo, pseudo},
                null, null, null, null);

        List<String> pseudoList = new ArrayList<String>();

        if (cursor.moveToFirst())
        {
            Log.d("DB", "Found interlocutors");
            do
            {
                String current;
                if ( !(cursor.getString(cursor.getColumnIndexOrThrow(MessageEntries.COL_SENDER)).equals(pseudo)) ) {
                    Log.d("DEBUG", "Current : " +pseudo+ " Returning user 1 " +cursor.getString(cursor.getColumnIndexOrThrow(MessageEntries.COL_SENDER)));
                    current = cursor.getString(cursor.getColumnIndexOrThrow(MessageEntries.COL_SENDER));
                }
                else {
                    Log.d("DEBUG", "Returning user 2");
                    current = cursor.getString(cursor.getColumnIndexOrThrow(MessageEntries.COL_RECEIVER));
                }
                if (!pseudoList.contains(current))
                pseudoList.add(current);
            } while(cursor.moveToNext());
        }
        return pseudoList;
    }

    public static ConversationOverviewData getConversationOverviewInformation(String currentUserPseudo, String interlocutorPseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(MessageEntries.TABLE_NAME,
                new String[] {MessageEntries.COL_MSG, MessageEntries.COL_RECEPTION_DATE},
                "("+ MessageEntries.COL_SENDER+ "=? AND " +MessageEntries.COL_RECEIVER+ "=?) OR ("+ MessageEntries.COL_SENDER +"=? AND "+ MessageEntries.COL_RECEIVER +"=?)",
                new String[] {currentUserPseudo, interlocutorPseudo, interlocutorPseudo, currentUserPseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            cursor.moveToLast();
            int profilePictureRes = Database.getProfilePicture(interlocutorPseudo);
            boolean isLastMessageRead = (cursor.getString(cursor.getColumnIndexOrThrow(MessageEntries.COL_RECEPTION_DATE)) != null);
            return new ConversationOverviewData(profilePictureRes, interlocutorPseudo, cursor.getString(cursor.getColumnIndexOrThrow(MessageEntries.COL_RECEPTION_DATE)), isLastMessageRead);
        }
        return null;
    }

    public static boolean haveAConversation(String pseudo1, String pseudo2)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(MessageEntries.TABLE_NAME,
                new String[] {MessageEntries.COL_SENDER},
                "("+ MessageEntries.COL_SENDER+ "=? AND " +MessageEntries.COL_RECEIVER+ "=?) OR ("+ MessageEntries.COL_SENDER +"=? AND "+ MessageEntries.COL_RECEIVER +"=?)",
                new String[] {pseudo1, pseudo2, pseudo2, pseudo1},
                null, null, null, null);

        if (cursor.moveToFirst())
            return true;
        return  false;
    }

    public static void sendMessage(String senderPseudo, String receiverPseudo, String message)
    {
        ContentValues values = new ContentValues();
        values.put(MessageEntries.COL_SENDER, senderPseudo);
        values.put(MessageEntries.COL_RECEIVER, receiverPseudo);
        values.put(MessageEntries.COL_MSG, message);
        values.put(MessageEntries.COL_SENDING_DATE, String.format("%1$tY/%1$tm/%1$td %1$tI:%1$tM:%1$tS", Calendar.getInstance()));

        Log.d("DB", "Adding message from " +senderPseudo+ " to " +receiverPseudo+ " : " +message);

        writeDB = helper.getWritableDatabase();
        writeDB.insert(MessageEntries.TABLE_NAME, null, values);
        writeDB.close();
    }

    public static List<MessageData> getAllMessages(String currentPseudo, String interlocutorPseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(MessageEntries.TABLE_NAME,
                new String[] {MessageEntries.COL_SENDER, MessageEntries.COL_RECEIVER, MessageEntries.COL_SENDING_DATE, MessageEntries.COL_RECEPTION_DATE, MessageEntries.COL_MSG},
                "("+ MessageEntries.COL_SENDER+ "=? AND " +MessageEntries.COL_RECEIVER+ "=?) OR ("+ MessageEntries.COL_SENDER +"=? AND "+ MessageEntries.COL_RECEIVER +"=?)",
                new String[] {currentPseudo, interlocutorPseudo, interlocutorPseudo, currentPseudo},
                null, null, MessageEntries.COL_SENDING_DATE +" DESC", null);

        List<MessageData> msgList = new ArrayList<MessageData>();

        if (cursor.moveToFirst())
        {
            do
            {
                String receptionDate;
                if (cursor.getString(cursor.getColumnIndexOrThrow(MessageEntries.COL_RECEPTION_DATE)) == null
                        && cursor.getString(cursor.getColumnIndexOrThrow(MessageEntries.COL_RECEIVER)).equals(currentPseudo))
                    receptionDate = String.format("%1$tY/%1$tm/%1$td %1$tI:%1$tM:%1$tS", Calendar.getInstance());
                else
                    receptionDate = cursor.getString(cursor.getColumnIndexOrThrow(MessageEntries.COL_RECEPTION_DATE));
                MessageData current = new MessageData(cursor.getString(cursor.getColumnIndexOrThrow(MessageEntries.COL_SENDER)),
                        cursor.getString(cursor.getColumnIndexOrThrow(MessageEntries.COL_MSG)),
                        cursor.getString(cursor.getColumnIndexOrThrow(MessageEntries.COL_SENDING_DATE)),
                        receptionDate);
                msgList.add(current);
            } while(cursor.moveToNext());
        }
        return msgList;
    }

}
