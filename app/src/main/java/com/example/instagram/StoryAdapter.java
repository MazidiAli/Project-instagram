package com.example.instagram;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;
public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {


    Context context;
    List<StoryModel> models;


    public StoryAdapter(Context context, List<StoryModel> models) {

        this.context = context;
        this.models = models;


    }


    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_story, viewGroup, false);
        return new StoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder storyViewHolder, final int i) {

        final StoryModel StoryModel = models.get(i);
        storyViewHolder.name.setText(StoryModel.getName());
        Picasso.with(context).load(StoryModel.getImgStory()).into(storyViewHolder.imgStory);
        storyViewHolder.imgStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,StoryActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putParcelableArrayListExtra("model",StoryModel);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class StoryViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imgStory;
        TextView name;


        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imgStory = itemView.findViewById(R.id.story_image);
            name = itemView.findViewById(R.id.name_story);
        }
    }


}
