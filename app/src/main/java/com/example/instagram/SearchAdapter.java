package com.example.instagram;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchView> {

    List<NameSearchName> namesList;
    Context context;


    public SearchAdapter(List<NameSearchName> namesList, Context context) {

        this.namesList = namesList;
        this.context=context;


    }

    @NonNull
    @Override
    public SearchView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_show_list_search, viewGroup, false);

        return new SearchView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchView searchView, final int i) {
        final NameSearchName name = namesList.get(i);
        searchView.textView.setText(name.getName());
        searchView.url=name.getImg();
        searchView.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(context,ShowPostUsername.class);
                intent.putExtra("img",searchView.url+"");
                Log.i("ali", "onClick: "+searchView.url);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);



            }
        });


    }

    @Override
    public int getItemCount() {
        return namesList.size();
    }


    public class SearchView extends RecyclerView.ViewHolder {


        RelativeLayout relativeLayout;
        TextView textView;
        String url;
        int id;

        public SearchView(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_search);
            relativeLayout=itemView.findViewById(R.id.item);


        }
    }


}
