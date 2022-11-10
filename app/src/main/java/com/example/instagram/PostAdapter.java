package com.example.instagram;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {


    Context context;
    List<PostModel> models;


    public PostAdapter(Context context, List<PostModel> models) {

        this.context = context;
        this.models = models;
    }


    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, viewGroup, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PostViewHolder postViewHolder, int i) {
        final PostModel postModel = models.get(i);
        postViewHolder.name.setText(postModel.getUsername());
        Picasso.with(context).load(postModel.getImage()).into(postViewHolder.ImagePost);
        Picasso.with(context).load(postModel.getImage()).into(postViewHolder.imgProfile);
        postViewHolder.ImagePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              postViewHolder.image_like.setImageResource(R.drawable.btnlike1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imgProfile;
        TextView name;
        ImageView ImagePost,image_like;


        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            ImagePost = itemView.findViewById(R.id.img_post);
            image_like=itemView.findViewById(R.id.icon_like);
            name = itemView.findViewById(R.id.profile_name);
            imgProfile = itemView.findViewById(R.id.profile_img);
        }

    }
}
