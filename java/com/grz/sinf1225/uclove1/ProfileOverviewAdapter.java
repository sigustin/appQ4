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
    public interface OnOverviewClickedListener
    {
        void onOverviewClicked(String pseudo);
    }

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

    List<OverviewData> m_data;
    OnOverviewClickedListener m_listener;

    public ProfileOverviewAdapter(List<OverviewData> data, OnOverviewClickedListener listener)
    {
        m_data = data;
        m_listener = listener;
    }

    @Override
    public ProfileOverviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View boxView = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_overview_layout, parent, false);
        return new ViewHolder(boxView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
        holder.profilePicture.setImageResource(m_data.get(position).m_profilePictureRes);
        holder.pseudo.setText(m_data.get(position).m_pseudo);
        holder.age.setText(m_data.get(position).m_age);
        holder.city.setText(m_data.get(position).m_city);

        holder.overviewBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m_listener.onOverviewClicked(m_data.get(position).m_pseudo);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return m_data.size();
    }


}
