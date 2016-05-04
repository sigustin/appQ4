package com.grz.sinf1225.uclove1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by simon on 4/05/16.
 */
public class MessageViewAdapter extends RecyclerView.Adapter<MessageViewAdapter.ViewHolder>
{
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView message;
        TextView readEmblem;

        public ViewHolder(View boxView)
        {
            super(boxView);
            message = (TextView) boxView.findViewById(R.id.message_text);
            readEmblem = (TextView) boxView.findViewById(R.id.read_emblem);
        }
    }

    List<MessageData> m_data;
    Context m_context;

    public MessageViewAdapter(List<MessageData> data, Context context)
    {
        m_data = data;
        m_context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View boxView = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_layout, parent, false);
        return new ViewHolder(boxView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
        holder.message.setText(m_data.get(position).m_message);

        if (m_data.get(position).m_messageRead)
            holder.readEmblem.setText(m_context.getResources().getString(R.string.message_read));
    }

    @Override
    public int getItemCount()
    {
        return m_data.size();
    }
}
