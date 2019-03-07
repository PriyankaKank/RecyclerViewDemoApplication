package com.example.user.recyclerviewdemoapplication.UI;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.user.recyclerviewdemoapplication.Model.OutputParameter;
import com.example.user.recyclerviewdemoapplication.Model.Row;
import com.example.user.recyclerviewdemoapplication.Network.ApiClient;
import com.example.user.recyclerviewdemoapplication.Network.AuthenticationApi;
import com.example.user.recyclerviewdemoapplication.Network.NetworkManager;
import com.example.user.recyclerviewdemoapplication.R;
import com.example.user.recyclerviewdemoapplication.Adapter.DisplayListAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created on 07-03-2019.
 * @author Priyanka Gole
 * Activity to check the availability of network
 */

public class DisplayListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DisplayListAdapter displayListAdapter;
    private SwipeRefreshLayout refreshLayout;
    private List<Row> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);

        init();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(displayListAdapter);

        //Check if network is available. If network available then call web service else display error message.
        if (NetworkManager.isNetworkAvailable(this)) {
            DisplayList();
        } else {
            Toast.makeText(this, R.string.internetErrorMessage, Toast.LENGTH_LONG).show();
        }

        //It is called after refreshing layout
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (NetworkManager.isNetworkAvailable(DisplayListActivity.this)) {
                    DisplayList();
                } else {
                    Toast.makeText(DisplayListActivity.this, R.string.internetErrorMessage, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * This is to call web service for fetching data and set adapter for displaying data on list.
     */
    private void DisplayList() {

        ShowProgress(true);

        AuthenticationApi authenticationApi = ApiClient.getClient().create(AuthenticationApi.class);

        Call<OutputParameter> call=authenticationApi.getAllPost();
        call.enqueue(new Callback<OutputParameter>() {
            @Override
            public void onResponse(Call<OutputParameter> call, Response<OutputParameter> response) {

                modelList = response.body().getRows();
                displayListAdapter = new DisplayListAdapter(modelList, DisplayListActivity.this);
                recyclerView.setAdapter(displayListAdapter);
                displayListAdapter.notifyDataSetChanged();

                ShowProgress(false);
            }

            @Override
            public void onFailure(Call<OutputParameter> call, Throwable t) {
                ShowProgress(false);
                Log.d("myResponse:",  "MSG"+t.toString());
            }
        });

    }

    /**
     *  To initialize components
     */
    private void init() {
        recyclerView = findViewById(R.id.recycler_list_view);
        refreshLayout= findViewById(R.id.refresh_recycler_list);
    }


    /**
     *  To show or hide progress
     */
    public void ShowProgress(final boolean visibility){

        if (visibility==true){
            refreshLayout.setRefreshing(true);
        }else {
            refreshLayout.setRefreshing(false);
        }

    }
}
