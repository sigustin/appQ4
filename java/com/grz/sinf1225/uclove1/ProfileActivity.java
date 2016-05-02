package com.grz.sinf1225.uclove1;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity
{
    final String tmpPseudo = "angelina24", tmpName = "Angelina Jolie", tmpBirthDate = "01/01/1984", tmpAge = "42 years old", tmpGender = "F", tmpLoveStatus = "Married", tmpInterestedIn = "Both", tmpHeight = "1m70", tmpDescription = "Famous actress", tmpSmoker = "No", tmpChildrenNb = "10", tmpCountry = "Etats-Unis", tmpCity = "Washington D.C.";
    final int profilePictureRes = R.drawable.angelina_jolie_profile_picture;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /*
        CurrentUser currentUser = (CurrentUser) getIntent().
         */

        addViews();
    }

    private void addViews()
    {
        /*RelativeLayout baseLayout = (RelativeLayout) findViewById(R.id.base_layout);

        ImageView profilePicture = new ImageView(this);
        profilePicture.setId(View.generateViewId());
        RelativeLayout.LayoutParams profilePictureParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, (int) getResources().getDimension(R.dimen.big_profile_picture_height));
        profilePictureParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        profilePictureParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE); profilePictureParams.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
        profilePictureParams.setMargins(
                (int) getResources().getDimension(R.dimen.profile_picture_margin),
                (int) getResources().getDimension(R.dimen.profile_picture_margin),
                (int) getResources().getDimension(R.dimen.profile_picture_margin),
                (int) getResources().getDimension(R.dimen.profile_picture_margin));
        profilePicture.setImageResource(profilePictureRes);
        baseLayout.addView(profilePicture, profilePictureParams);

        TextView pseudo = new TextView(this);
        RelativeLayout.LayoutParams pseudoParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pseudoParams.addRule(RelativeLayout.BELOW, profilePicture.getId());
        pseudoParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE); pseudoParams.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
        pseudo.setText(tmpPseudo);
        setListItemPadding(pseudo);
        baseLayout.addView(pseudo, pseudoParams);

        TextView name = new TextView(this);
        RelativeLayout.LayoutParams nameParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        nameParams.addRule(RelativeLayout.BELOW, R.id.separator1);
        nameParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE); nameParams.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
        name.setText(tmpName);
        setListItemPadding(name);
        baseLayout.addView(name, nameParams);*/

        ImageView profilePicture = (ImageView) findViewById(R.id.profile_picture);
        if (/*profilePicture can be viewed*/true)
            profilePicture.setImageResource(profilePictureRes);
        else
            profilePicture.setImageResource(R.drawable.ic_person_black_48dp);

        TextView pseudo = (TextView) findViewById(R.id.pseudo);
        if (/*can be viewed*/true)
            pseudo.setText(tmpPseudo);
        else
            pseudo.setText(R.string.invisible);

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(tmpName);

        TextView birthDate = (TextView) findViewById(R.id.birth_date);
        birthDate.setText(tmpBirthDate);

        TextView age = (TextView) findViewById(R.id.age);
        age.setText(tmpAge);

        TextView gender = (TextView) findViewById(R.id.gender);
        gender.setText(tmpGender);

        TextView loveStatus = (TextView) findViewById(R.id.love_status);
        loveStatus.setText(tmpLoveStatus);

        TextView interestedIn = (TextView) findViewById(R.id.interested_in);
        interestedIn.setText(tmpInterestedIn);

        TextView height = (TextView) findViewById(R.id.height);
        height.setText(tmpHeight);

        TextView description = (TextView) findViewById(R.id.description);
        description.setText(tmpDescription);

        TextView smoker = (TextView) findViewById(R.id.smoker);
        smoker.setText(tmpSmoker);

        TextView childrenNb = (TextView) findViewById(R.id.children_nb);
        childrenNb.setText(tmpChildrenNb);

        TextView country = (TextView) findViewById(R.id.country);
        country.setText(tmpCountry);

        TextView city = (TextView) findViewById(R.id.city);
        city.setText(tmpCity);
    }

    private void setListItemPadding(View view)
    {
        view.setPadding(
                (int) getResources().getDimension(R.dimen.list_item_padding_horizontal),
                (int) getResources().getDimension(R.dimen.list_item_padding_vertical),
                (int) getResources().getDimension(R.dimen.list_item_padding_horizontal),
                (int) getResources().getDimension(R.dimen.list_item_padding_vertical));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.top_menu_my_profile_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.top_menu_item_settings:
                Log.d("TOPMENU", "Settings selected");
                Intent intent = new Intent(this, SettingsActivity.class);
                /*
                intent.putExtra(CurrentUser.EXTRA_CURRENT_USER, currentUser);
                 */
                startActivity(intent);
                return true;
            case R.id.top_menu_item_quit:
                Log.d("TOPMENU", "Quit selected");
                return true;
        }
        return false;
    }
}
