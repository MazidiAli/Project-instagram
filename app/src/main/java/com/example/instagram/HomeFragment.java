package com.example.instagram;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView rc_story, rc_post;
    View view;
    ProgressBar progressBar;
    List<PostModel> postModels;
    List<StoryModel> storyModels;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.layout_home_fragment, container, false);
        SetupView();
        List_Post();
        return view;
    }

    private void List_Post() {
        progressBar.setVisibility(View.GONE);
        //String uri = "http://192.168.1.101/instagram/index.php";
        String uri = "https://store-iran.ir/Api_instagram/getpost.php";
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, uri, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        PostModel postModel = new PostModel();
                        StoryModel storyModel = new StoryModel();
                        JSONObject object = response.getJSONObject(i);
                        postModel.setUsername(object.getString("username"));
                        postModel.setTitle(object.getString("title"));
                        postModel.setImage(object.getString("image"));
                        storyModel.setName(object.getString("username"));
                        storyModel.setImgStory(object.getString("image"));

                        postModels.add(postModel);
                        storyModels.add(storyModel);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    rc_post.setAdapter(new PostAdapter(getContext(), postModels));
                    rc_story.setAdapter(new StoryAdapter(getContext(),storyModels));

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

    private void SetupView() {
        progressBar=view.findViewById(R.id.progress);
        storyModels = new ArrayList<>();
        postModels = new ArrayList<>();
        rc_post = view.findViewById(R.id.rc_post);
        rc_post.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        rc_story = view.findViewById(R.id.rc_story);
        rc_story.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
    }


}
