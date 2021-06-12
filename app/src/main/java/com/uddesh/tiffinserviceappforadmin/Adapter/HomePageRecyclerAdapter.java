package com.uddesh.tiffinserviceappforadmin.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.uddesh.tiffinserviceappforadmin.R;

public class HomePageRecyclerAdapter extends RecyclerView.Adapter<HomePageRecyclerViewHolder> {
    @NonNull
    @Override
    public HomePageRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.individual_service_list_card , parent , false);
        return new HomePageRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomePageRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
class HomePageRecyclerViewHolder extends RecyclerView.ViewHolder
{
    private TextView service_name_textview , service_active_textview , service_not_active_textview , subscribed_user_textview;
    public HomePageRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        service_active_textview = itemView.findViewById(R.id.service_active_textview);
        service_not_active_textview = itemView.findViewById(R.id.service_not_active_textview);
        service_name_textview = itemView.findViewById(R.id.service_name_textview);
        subscribed_user_textview = itemView.findViewById(R.id.subscribed_user_textview);
    }

    public TextView getService_name_textview() {
        return service_name_textview;
    }

    public TextView getService_active_textview() {
        return service_active_textview;
    }

    public TextView getService_not_active_textview() {
        return service_not_active_textview;
    }

    public TextView getSubscribed_user_textview() {
        return subscribed_user_textview;
    }
}
