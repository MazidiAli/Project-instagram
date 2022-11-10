package com.example.instagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ShowPostUsername extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_post_username);
        imageView=findViewById(R.id.imageView);
        String img=getIntent().getExtras().getString("img");

        Picasso.with(this).load(img).into(imageView);
    }
}
