package com.uddesh.tiffinserviceappforadmin.Fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uddesh.tiffinserviceappforadmin.Activity.AddServiceActivity;
import com.uddesh.tiffinserviceappforadmin.Adapter.HomePageRecyclerAdapter;
import com.uddesh.tiffinserviceappforadmin.R;
import com.uddesh.tiffinserviceappforadmin.Repository.RetrofitViewModel;

public class HomeFragment extends Fragment {

    private HomePageRecyclerAdapter homePageRecyclerAdapterInstance;
    private RetrofitViewModel viewModel;
    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.homepage_recyclerview);
        homePageRecyclerAdapterInstance = new HomePageRecyclerAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager( getContext() ,LinearLayoutManager.VERTICAL , false));
        recyclerView.setAdapter(homePageRecyclerAdapterInstance);
        viewModel = new RetrofitViewModel(getActivity().getApplication());
        FloatingActionButton add_service_fab = view.findViewById(R.id.add_service_fab);
        add_service_fab.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext() , AddServiceActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getAllServiceDetails().observe(this , result->{
            if(result!=null)
            {
                homePageRecyclerAdapterInstance.notifyDataSetChanged(result);
            }
        });
    }
}