package com.grz.sinf1225.uclove1;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.grz.sinf1225.uclove1.Profile.EditProfileActivity;

public class MainActivity extends AppCompatActivity
{
    //public static final String EXTRA_CURRENT_USER = "UCLove.CurrentUser";write this in CurrentUser
    public static final String EXTRA_IS_REGISTRATION = "UCLove.REGISTRATION";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Database.init(this);
        Database.tmpWrite(123456);
    }

    public void login(View view)
    {
        Log.d("BUTTON", "Pressed login button");

        EditText pseudoEditText = (EditText) findViewById(R.id.edit_text_pseudo);
        String inputPseudo = pseudoEditText.getText().toString();

        EditText passwordEditText = (EditText) findViewById(R.id.edit_text_password);
        String inputPassword = passwordEditText.getText().toString();

        Log.d("ENTRIES", "Entered pseudo : " +inputPseudo+ " password : " +inputPassword);

        //----
        inputPseudo = "attempt1";
        inputPassword = "password";
        //----

        if(Database.isRightPassword(inputPseudo, inputPassword))
        {

            Intent intent = new Intent(this, MenuActivity.class);
            User currentUser = new User(inputPseudo);
            /*
            CurrentUser currentUser = new CurrentUser(inputPseudo);
            */
            intent.putExtra(User.EXTRA_TMP, currentUser);
            startActivity(intent);
        }
        else
        {
            Toast badInput = Toast.makeText(this, R.string.invalid_password, Toast.LENGTH_LONG);
            badInput.show();
        }
    }

    public void startRegistration(View view)
    {
        Log.d("BUTTON", "Pressed register button");
        Intent intent = new Intent(this, EditProfileActivity.class);
        intent.putExtra(EXTRA_IS_REGISTRATION, true);
        startActivity(intent);
    }

}
