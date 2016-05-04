package com.grz.sinf1225.uclove1;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by simon on 4/05/16.
 */
public class MessageViewAdapter extends RecyclerView.Adapter<MessageViewAdapter.ViewHolder>
{
    public interface onMessageClickedListener
    {
        void onMessageClicked(MessageData data);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout messageBox;
        TextView message;
        TextView readEmblem;

        public ViewHolder(View boxView)
        {
            super(boxView);
            messageBox = (LinearLayout) boxView.findViewById(R.id.message_box);
            message = (TextView) boxView.findViewById(R.id.message_text);
            readEmblem = (TextView) boxView.findViewById(R.id.read_emblem);
        }
    }

    private List<MessageData> m_data;
    private onMessageClickedListener m_listener;
    private Context m_context;
    private String m_interlocutorPseudo;

    public MessageViewAdapter(List<MessageData> data, onMessageClickedListener listener, Context context, String interlocutorPseudo)
    {
        m_data = data;
        m_listener = listener;
        m_context = context;
        m_interlocutorPseudo = interlocutorPseudo;
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
        holder.messageBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m_listener.onMessageClicked(m_data.get(position));
            }
        });

        holder.message.setText(m_data.get(position).m_message);

        Log.d("DEBUG", m_data.get(position).m_senderPseudo +" "+ m_interlocutorPseudo);
        if (m_data.get(position).m_senderPseudo.equals(m_interlocutorPseudo))
        {
            holder.messageBox.setBackgroundResource(R.color.colorPrimaryLight);
            holder.messageBox.setGravity(Gravity.START);
            if (m_data.get(position).m_readingDate != null)
                holder.readEmblem.setText(m_context.getResources().getString(R.string.message_read));
        }
        else
        {
            holder.messageBox.setBackgroundResource(R.color.white);
        }
    }

    @Override
    public int getItemCount()
    {
        return m_data.size();
    }
}
