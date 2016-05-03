package com.grz.sinf1225.uclove1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by simon on 3/05/16.
 */
public class ProfileOverviewAdapter extends RecyclerView.Adapter<ProfileOverviewAdapter.ViewHolder>
{
    List<OverviewData> m_data;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        RelativeLayout overviewBox;
        ImageView profilePicture;
        TextView pseudo;
        TextView age;
        TextView city;

        public ViewHolder(View boxView)
        {
            super(boxView);
            overviewBox = (RelativeLayout) boxView.findViewById(R.id.profile_overview_relative_layout);
            profilePicture = (ImageView) boxView.findViewById(R.id.profile_picture);
            pseudo = (TextView) boxView.findViewById(R.id.pseudo);
            age = (TextView) boxView.findViewById(R.id.age);
            city = (TextView) boxView.findViewById(R.id.city);
        }
    }

    public ProfileOverviewAdapter(List<OverviewData> data)
    {
        m_data = data;
    }

    @Override
    public ProfileOverviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View boxView = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_overview_layout, parent, false);
        return new ViewHolder(boxView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.profilePicture.setImageResource(m_data.get(position).m_profilePictureRes);
        holder.pseudo.setText(m_data.get(position).m_pseudo);
        holder.age.setText(m_data.get(position).m_age);
        holder.city.setText(m_data.get(position).m_city);
    }

    @Override
    public int getItemCount()
    {
        return m_data.size();
    }


}
