
package com.example.instagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class StoryActivity extends AppCompatActivity {

    CircleImageView profile;
    TextView username;
    ImageView Story;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        StoryModel storyModel=getIntent().getParcelableExtra("model");
        profile=findViewById(R.id.item_show_profile);
        username=findViewById(R.id.username);
        Story=findViewById(R.id.story);
        Picasso.with(getApplicationContext()).load(storyModel.getImgStory()).into(profile);
        Picasso.with(getApplicationContext()).load(storyModel.getImgStory()).into(Story);
        username.setText(storyModel.getName());



    }
}
