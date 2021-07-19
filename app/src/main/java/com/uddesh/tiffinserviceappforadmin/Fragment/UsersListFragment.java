package com.uddesh.tiffinserviceappforadmin.Fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.uddesh.tiffinserviceappforadmin.Adapter.UserListRecyclerAdapter;
import com.uddesh.tiffinserviceappforadmin.R;
import com.uddesh.tiffinserviceappforadmin.Repository.RetrofitViewModel;

public class UsersListFragment extends Fragment {
    private UserListRecyclerAdapter userListRecyclerAdapterInstance;
    private RetrofitViewModel viewModel;
    public UsersListFragment() {
        // Required empty public constructor
    }


    public static UsersListFragment newInstance() {
        return new UsersListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.user_list_recyclerview);
        userListRecyclerAdapterInstance = new UserListRecyclerAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager( getContext() ,LinearLayoutManager.VERTICAL , false));
        recyclerView.setAdapter(userListRecyclerAdapterInstance);
        viewModel = new RetrofitViewModel(getActivity().getApplication());
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getUserDetails().observe(this , result->{
            if(result!=null)
            {
                userListRecyclerAdapterInstance.notifyDataSetChanged(result);
            }
        });
    }
}