package com.grz.sinf1225.uclove1.Chat;

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
public class ConversationOverviewAdapter extends RecyclerView.Adapter<ConversationOverviewAdapter.ViewHolder>
{
    public interface onConversationOverviewClickedListener
    {
        void onOverviewClicked(String pseudo);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        RelativeLayout overviewBox;
        ImageView profilePicture;
        TextView pseudo;
        TextView lastMessage;

        public ViewHolder(View boxView)
        {
            super(boxView);
            overviewBox = (RelativeLayout) boxView.findViewById(R.id.conversation_overview_relative_layout);
            profilePicture = (ImageView) boxView.findViewById(R.id.profile_picture);
            pseudo = (TextView) boxView.findViewById(R.id.pseudo);
            lastMessage = (TextView) boxView.findViewById(R.id.last_message);
        }
    }

    List<ConversationOverviewData> m_data;
    onConversationOverviewClickedListener m_listener;
    Context m_context;

    public ConversationOverviewAdapter(List<ConversationOverviewData> data, onConversationOverviewClickedListener listener, Context context)
    {
        m_data = data;
        m_listener = listener;
        m_context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View boxView = LayoutInflater.from(parent.getContext()).inflate(R.layout.conversation_overview_layout, parent, false);
        return new ViewHolder(boxView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
        holder.overviewBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m_listener.onOverviewClicked(m_data.get(position).m_pseudo);
            }
        });

        holder.profilePicture.setImageResource(m_data.get(position).m_profilePictureRes);
        holder.pseudo.setText(m_data.get(position).m_pseudo);
        holder.lastMessage.setText(m_data.get(position).m_lastMessage);

        if (!m_data.get(position).m_lastMessageRead)
            holder.overviewBox.setBackgroundResource(R.color.colorPrimaryLight);
    }

    @Override
    public int getItemCount()
    {
        return m_data.size();
    }
}
