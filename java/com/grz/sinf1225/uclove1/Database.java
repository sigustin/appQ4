package com.grz.sinf1225.uclove1;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.security.PublicKey;

/**
 * Created by simon on 5/05/16.
 */
public final class Database
{
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

    public static abstract class RelationshipEntries implements BaseColumns
    {
        public static final String TABLE_NAME = "Relationship";
        public static final String COL_NAME_USER1 = "User1", COL_NAME_USER2 = "User2", COL_NAME_RELATIONSHIP_TYPE = "RelationshipType",
                COL_NAME_REQUEST_DATE = "RequestDate";
    }

    public static abstract class PicturesEntries implements BaseColumns
    {
        public static final String TABLE_NAME = "Pictures";
        public static final String COL_NAME_PSEUDO = "Pseudo", COL_NAME_PICTURE = "Picture";
    }

    public static abstract class MessageEntries implements BaseColumns
    {
        public static final String TABLE_NAME = "Message";
        public static final String COL_NAME_SENDER = "Sender", COL_NAME_SENDING_DATE = "SendingDate",
                COL_NAME_RECEIVER = "Receiver", COL_NAME_RECEPTION_DATE = "ReceptionDate", COL_NAME_MSG = "Msg";
    }

    public static abstract class DisponibilityEntries implements BaseColumns
    {
        public static final String TABLE_NAME = "Disponibility";
        public static final String COL_NAME_PSEUDO = "Pseudo", COL_NAME_DISPONIBILITY_DATE = "DisponibilityDate";
    }

    public static abstract class AppointmentEntries implements BaseColumns
    {
        public static final String TABLE_NAME = "Appointment";
        public static final String COL_NAME_USER1 = "User1", COL_NAME_USER2 = "User2",
                COL_NAME_DATE = "Date", COL_NAME_LOCATION = "Location";
    }

    public static final String SQL_CREATE_USER = "CREATE TABLE User(Pseudo TEXT NOT NULL PRIMARY KEY, LastName TEXT NOT NULL, FirstName TEXT NOT NULL, BirthDate DATE NOT NULL, Gender TEXT NOT NULL, LoveStatus TEXT NOT NULL DEFAULT 'Single', RegistrationDate DATE NOT NULL, Height INTEGER, Description TEXT, Smoker BOOLEAN, InterestedIn TEXT, ProfilePicture BLOB, ChildrenNb INTEGER, Country TEXT NOT NULL, City TEXT NOT NULL, Password TEXT NOT NULL, LastNameVisibility TEXT NOT NULL DEFAULT 'Public', FirstNameVisibility TEXT NOT NULL DEFAULT 'Public', BirthDateVisibility TEXT NOT NULL DEFAULT 'Public', GenderVisibility TEXT NOT NULL DEFAULT 'Public', LoveStatusVisibility TEXT NOT NULL DEFAULT 'Public', HeightVisibility TEXT NOT NULL DEFAULT 'Public', SmokerVisibility TEXT NOT NULL DEFAULT 'Public',  ChildrenNbVisibility TEXT NOT NULL DEFAULT 'Public', CityVisibility TEXT NOT NULL DEFAULT 'Public');";
    public static final String SQL_CREATE_RELATIONSHIP = "CREATE TABLE Relationship(User1 TEXT NOT NULL, User2 TEXT NOT NULL, RelationshipType TEXT NOT NULL DEFAULT 'Request', RequestDate TEXT NOT NULL, PRIMARY KEY (User1, User2), FOREIGN KEY (User1) REFERENCES User, FOREIGN KEY (User2) REFERENCES User);";
    public static final String SQL_CREATE_PICTURES = "CREATE TABLE Pictures(Pseudo TEXT NOT NULL, Picture BLOB NOT NULL, PRIMARY KEY (Pseudo, Picture), FOREIGN KEY (Pseudo) REFERENCES User);";
    public static final String SQL_CREATE_MESSAGE = "CREATE TABLE Message(Sender TEXT NOT NULL, SendingDate DATE NOT NULL, Receiver TEXT NOT NULL, ReceptionDate DATE, Msg TEXT NOT NULL, PRIMARY KEY (Sender, SendingDate, Receiver), FOREIGN KEY (Sender) REFERENCES User, FOREIGN KEY (Receiver) REFERENCES User);";
    public static final String SQL_CREATE_DISPONIBILITY = "CREATE TABLE Disponibility(Pseudo TEXT NOT NULL, DisponibilityDate DATE NOT NULL, PRIMARY KEY (Pseudo, DisponibilityDate), FOREIGN KEY (Pseudo) REFERENCES User);";
    public static final String SQL_CREATE_APPOINTMENT = "CREATE TABLE Appointment(User1 TEXT NOT NULL, User2 TEXT NOT NULL, Date DATE NOT NULL, Location TEXT, PRIMARY KEY (User1, User2, Date), FOREIGN KEY (User1) REFERENCES User, FOREIGN KEY (User2) REFERENCES User);";

    public static class DBHelper extends SQLiteOpenHelper
    {
        public static final int DATABASE_VERSION = 1;
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

    public static void init(Context context)
    {
        if (helper == null)
            helper = new DBHelper(context);
    }

    public static void tmpWrite(int date)
    {
        ContentValues values = new ContentValues();
        values.put(DisponibilityEntries.COL_NAME_PSEUDO, "Testpseudo");
        values.put(DisponibilityEntries.COL_NAME_DISPONIBILITY_DATE, Integer.toString(date));

        writeDB = helper.getWritableDatabase();
        long newRowId = writeDB.insert(DisponibilityEntries.TABLE_NAME, null, values);
        writeDB.close();
    }

    public static void tmpRead()
    {
        String[] projection = {DisponibilityEntries.COL_NAME_PSEUDO, DisponibilityEntries.COL_NAME_DISPONIBILITY_DATE};
        String[] selectionArgs = {"Testpseudo"};

        readDB = helper.getReadableDatabase();
        Cursor c = readDB.query(DisponibilityEntries.TABLE_NAME, projection, DisponibilityEntries.COL_NAME_PSEUDO +"=?", selectionArgs, null, null, null, null);

        if (c.moveToFirst())
        {
            String pseudo = c.getString(c.getColumnIndexOrThrow(DisponibilityEntries.COL_NAME_PSEUDO));
            int date = c.getInt(c.getColumnIndexOrThrow(DisponibilityEntries.COL_NAME_DISPONIBILITY_DATE));

            Log.d("DB", pseudo + " " + date);

            c.close();
        }

        readDB.close();
    }

    public static void tmpUpdate(int newDate)
    {
        ContentValues values = new ContentValues();
        values.put(DisponibilityEntries.COL_NAME_DISPONIBILITY_DATE, newDate);

        /*writeDB = helper.getWritableDatabase();
        writeDB.update(DisponibilityEntries.TABLE_NAME, values, DisponibilityEntries.COL_NAME_PSEUDO +"=?", new String[] {"Testpseudo"});
        //writeDB.execSQL("UPDATE " +DisponibilityEntries.TABLE_NAME+ " SET " +DisponibilityEntries.COL_NAME_DISPONIBILITY_DATE+ "=" +newDate+ " WHERE " +DisponibilityEntries.COL_NAME_PSEUDO+ "='Testpseudo';");
        writeDB.close();*/

        tmpDelete("Testpseudo");
        tmpWrite(newDate);
    }

    public static void tmpDelete(String pseudo)
    {
        writeDB = helper.getWritableDatabase();
        writeDB.delete(DisponibilityEntries.TABLE_NAME, DisponibilityEntries.COL_NAME_PSEUDO +"=?", new String[] {pseudo});
        writeDB.close();
    }
}
