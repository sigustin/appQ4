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
import java.sql.Blob;
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
                COL_SMOKER = "Smoker", COL_INTERESTED_IN = "IneterstedIn", COL_PROFILE_PICTURE = "ProfilePicture",
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
        else
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
        else
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
        else
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
        else
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
        else
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
        else
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
        else
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
        else
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
        else
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
        else
            return null;
    }

    public static byte[] getProfilePicture(String pseudo)
    {
        readDB = helper.getReadableDatabase();
        Cursor cursor = readDB.query(UserEntries.TABLE_NAME,
                new String[] {UserEntries.COL_PROFILE_PICTURE},
                UserEntries.COL_PSEUDO + "=?",
                new String[] {pseudo},
                null, null, null, null);

        if (cursor.moveToFirst())
        {
            byte[] answer = cursor.getBlob(cursor.getColumnIndexOrThrow(UserEntries.COL_PROFILE_PICTURE));
            cursor.close();
            return answer;
        }
        else
            return null;
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
        else
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
        else
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
        else
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
        else
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
        else
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
        else
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
        else
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
        else
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
        else
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
        else
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
        else
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
        else
            return -1;
    }


    public static void updateUser(String pseudo, String columnName, String newEntry)
    {
        ContentValues values = new ContentValues();
        values.put(columnName, newEntry);

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

    public static void updateProfilePicture(User user, byte[] newValue)
    {
        updateUser(user.getPseudo(), UserEntries.COL_FIRST_NAME, newValue);
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


    public static void updateDisponibility(User user, boolean[] tmp) {}
    public static boolean[] getDisponibility(String pseudo) {return null;}
    public static List<byte[]> getPictures(String pseudo) {return null;}
}
