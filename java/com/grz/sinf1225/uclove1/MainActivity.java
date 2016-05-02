package com.grz.sinf1225.uclove1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    //public final String EXTRA_CURRENT_USER = "UCLove.CurrentUser";write this in CurrentUser

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view)
    {
        Log.d("BUTTON", "Pressed login button");

        EditText pseudoEditText = (EditText) findViewById(R.id.edit_text_pseudo);
        String inputPseudo = pseudoEditText.getText().toString();

        EditText passwordEditText = (EditText) findViewById(R.id.edit_text_password);
        String inputPassword = passwordEditText.getText().toString();

        if(/*Database.isRightPassword(inputPseudo, inputPassword)*/true)
        {

            Intent intent = new Intent(this, MenuActivity.class);
            /*
            CurrentUser currentUser = new CurrentUser(inputPseudo);
            intent.putExtra(CurrentUser.EXTRA_CURRENT_USER, currentUser);
            */
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
    }

}
