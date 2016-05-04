package com.grz.sinf1225.uclove1.Dating;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.grz.sinf1225.uclove1.R;

import java.util.List;

/**
 * Created by simon on 4/05/16.
 */
public class DateOverviewAdapter extends RecyclerView.Adapter<DateOverviewAdapter.ViewHolder>
{
    public interface OnDateOverviewClickedListener
    {
        void onOverviewClicked(DateData dateData);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        RelativeLayout overviewBox;
        ImageView friendProfilePicture;
        TextView pseudo;
        TextView datetimeOrAsked;

        public ViewHolder(View boxView)
        {
            super(boxView);
            overviewBox = (RelativeLayout) boxView.findViewById(R.id.date_overview_box);
            friendProfilePicture = (ImageView) boxView.findViewById(R.id.small_profile_picture);
            pseudo = (TextView) boxView.findViewById(R.id.pseudo);
            datetimeOrAsked = (TextView) boxView.findViewById(R.id.date_time_or_asked);
        }
    }

    List<DateData> m_data;
    OnDateOverviewClickedListener m_listener;
    Context m_context;

    public DateOverviewAdapter(List<DateData> data, OnDateOverviewClickedListener listener, Context context)
    {
        m_data = data;
        m_listener = listener;
        m_context = context;
    }

    @Override
    public DateOverviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View boxView = LayoutInflater.from(parent.getContext()).inflate(R.layout.date_overview_layout, parent, false);
        return new ViewHolder(boxView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
        holder.overviewBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m_listener.onOverviewClicked(m_data.get(position));
            }
        });

        holder.friendProfilePicture.setImageResource(m_data.get(position).m_friendProfilePictureRes);
        holder.pseudo.setText(m_data.get(position).m_friendPseudo);

        if (m_data.get(position).m_dateTime == null)
        {
            holder.datetimeOrAsked.setText(m_context.getResources().getString(R.string.new_date_to_set));
            holder.datetimeOrAsked.setBackgroundResource(R.color.colorPrimaryLight);
        }
        else
            holder.datetimeOrAsked.setText(m_data.get(position).m_dateTime);
    }

    @Override
    public int getItemCount()
    {
        return m_data.size();
    }
}
