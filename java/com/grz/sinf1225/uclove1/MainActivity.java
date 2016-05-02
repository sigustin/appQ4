package com.grz.sinf1225.uclove1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view)
    {
        Log.d("BUTTON", "Pressed login button");
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void startRegistration(View view)
    {
        Log.d("BUTTON", "Pressed register button");
    }

}
