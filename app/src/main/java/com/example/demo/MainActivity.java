package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Grid_Model> details;
    private Grid_Adapter gridAdapter;
    private String url = Api_Config.base_url;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        progressBar = findViewById(R.id.progress_bar);

        details = new ArrayList<>();
        gridAdapter = new Grid_Adapter(details,this);
        recyclerView.setAdapter(gridAdapter);

        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(manager);

        fetch_data();
    }

    private void fetch_data() {

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < response.length(); i++) {

                                JSONObject recordJson = jsonArray.getJSONObject(i);

                                Grid_Model records = new Grid_Model();

                                records.setId(recordJson.getString("id"));
                                records.setTitle(recordJson.getString("author"));
                                details.add(records);
                                gridAdapter.notifyDataSetChanged();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        progressBar.setVisibility(View.GONE);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("Error.Response", String.valueOf(error));
                    }
                }
        ) {
        };

        queue.add(getRequest);
    }
}