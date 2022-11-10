package com.example.instagram;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    View view;
    SearchView searchView;
    SearchAdapter adapter;
    RecyclerView recyclerView;
    List<NameSearchName> names = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.item_show_search, container, false);


        getsearch();
        return view;

    }


    private void getsearch() {

        searchView = view.findViewById(R.id.search_view);
        recyclerView = view.findViewById(R.id.list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (s.equals("")){

                    recyclerView.setVisibility(View.GONE);
                }


                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                getName(s);
                recyclerView.setVisibility(View.VISIBLE);
                adapter=new SearchAdapter(names,getContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                names.removeAll(names);
                if (s.equals("")){

                    recyclerView.setVisibility(View.GONE);

                }



                return false;
            }
        });
    }

    private void getName(String text) {
        String uri = "http://store-iran.ir/Api_instagram/saerch.php?text="+text;
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, uri, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject object = response.getJSONObject(i);

                        NameSearchName nameSearchName=new NameSearchName();
                        nameSearchName.setName(object.getString("username"));
                        nameSearchName.setId(object.getInt("id"));
                        nameSearchName.setImg(object.getString("image"));
                        names.add(nameSearchName);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    adapter = new SearchAdapter(names,getContext());
                    recyclerView.setAdapter(adapter);


                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);


    }

}

