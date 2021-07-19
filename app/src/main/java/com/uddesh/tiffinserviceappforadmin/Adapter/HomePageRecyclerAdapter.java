package com.uddesh.tiffinserviceappforadmin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.uddesh.tiffinserviceappforadmin.Activity.AddServiceActivity;
import com.uddesh.tiffinserviceappforadmin.DataModels.GetServiceModel;
import com.uddesh.tiffinserviceappforadmin.R;
import java.util.List;

public class HomePageRecyclerAdapter extends RecyclerView.Adapter<HomePageRecyclerViewHolder> {
    private List<GetServiceModel> model;
    private final Context mContext;

    public HomePageRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public HomePageRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.individual_service_list_card , parent , false);
        return new HomePageRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomePageRecyclerViewHolder holder, int position) {
        if(model!=null) {
            String subscribed = model.get(position).getSubscribed() + "";
            holder.getService_name_textview().setText(model.get(position).getServicename());
            holder.getSubscribed_user_textview().setText(subscribed);
            if (model.get(position).isActive()) {
                holder.getService_active_textview().setVisibility(View.VISIBLE);
                holder.getService_not_active_textview().setVisibility(View.GONE);
            } else {
                holder.getService_active_textview().setVisibility(View.GONE);
                holder.getService_not_active_textview().setVisibility(View.VISIBLE);
            }
            holder.itemView.setOnClickListener(v->{
                Intent intent = new Intent(mContext , AddServiceActivity.class);
                intent.putExtra("id" , model.get(position).getId());
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

    public void notifyDataSetChanged(List<GetServiceModel> model)
    {
        this.model = model;
        notifyDataSetChanged();
    }
}
class HomePageRecyclerViewHolder extends RecyclerView.ViewHolder
{
    private final TextView service_name_textview , service_active_textview , service_not_active_textview , subscribed_user_textview;
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
