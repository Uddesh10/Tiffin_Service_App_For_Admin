package com.uddesh.tiffinserviceappforadmin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.uddesh.tiffinserviceappforadmin.DataModels.UserDetailsModel;
import com.uddesh.tiffinserviceappforadmin.R;

import java.util.List;
import java.util.Locale;

public class UserListRecyclerAdapter extends RecyclerView.Adapter<UserListRecyclerViewHolder> {
    private List<UserDetailsModel> model;
    private final Context mContext;

    public UserListRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public UserListRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.individual_user_list_card , parent , false);
        return new UserListRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListRecyclerViewHolder holder, int position) {
    if(model!=null)
        {
            holder.getSubscribed_service_textview().setText(model.get(position).getServicename());
            holder.getUser_address_textview().setText(model.get(position).getLocation());
            holder.getUser_contact_no_textview().setText(model.get(position).getContactno());
            holder.getUser_name_textview().setText(model.get(position).getFullname());
            holder.getUser_address_textview().setOnClickListener(v->{
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?q=loc:%f,%f", model.get(position).getLatitude() , model.get(position).getLongitude());
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                mContext.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        if(model!=null)
            return model.size();
        else
            return 0;
    }

    public void notifyDataSetChanged(List<UserDetailsModel> model)
    {
        this.model = model;
        notifyDataSetChanged();
    }
}
class UserListRecyclerViewHolder extends RecyclerView.ViewHolder
{
    private final TextView user_name_textview , subscribed_service_textview , user_contact_no_textview , user_address_textview;
    public UserListRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        user_address_textview = itemView.findViewById(R.id.user_address_textview);
        subscribed_service_textview = itemView.findViewById(R.id.subscribed_service_textview);
        user_name_textview = itemView.findViewById(R.id.user_name_textview);
        user_contact_no_textview = itemView.findViewById(R.id.user_contact_no_textview);
    }

    public TextView getUser_name_textview() {
        return user_name_textview;
    }

    public TextView getSubscribed_service_textview() {
        return subscribed_service_textview;
    }

    public TextView getUser_contact_no_textview() {
        return user_contact_no_textview;
    }

    public TextView getUser_address_textview() {
        return user_address_textview;
    }
}
