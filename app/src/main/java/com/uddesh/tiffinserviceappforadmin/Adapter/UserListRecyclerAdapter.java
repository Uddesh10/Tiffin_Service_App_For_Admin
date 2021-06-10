package com.uddesh.tiffinserviceappforadmin.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uddesh.tiffinserviceappforadmin.R;

public class UserListRecyclerAdapter extends RecyclerView.Adapter<UserListRecyclerViewHolder> {
    @NonNull
    @Override
    public UserListRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.individual_user_list_card , parent , false);
        return new UserListRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
class UserListRecyclerViewHolder extends RecyclerView.ViewHolder
{
    TextView user_name_textview , subscribed_service_textview , user_contact_no_textview , user_address_textview;
    public UserListRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        user_address_textview = itemView.findViewById(R.id.user_address_textview);
        subscribed_service_textview = itemView.findViewById(R.id.subscribed_service_textview);
        user_name_textview = itemView.findViewById(R.id.user_name_textview);
        user_contact_no_textview = itemView.findViewById(R.id.user_contact_no_textview);
    }
}
