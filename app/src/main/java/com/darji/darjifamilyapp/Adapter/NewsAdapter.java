package com.darji.darjifamilyapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.darji.darjifamilyapp.Model.NewsEventsData;
import com.darji.darjifamilyapp.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
    private Context context;
    private List<NewsEventsData> newslist;

    public NewsAdapter(Context context, List<NewsEventsData> newslist) {
        this.context = context;
        this.newslist = newslist;
    }
    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.newslist,parent,false);
        NewsAdapter.NewsHolder newsHolder = new NewsAdapter.NewsHolder(root);
        return newsHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        NewsEventsData data = newslist.get(position);

        holder.news.setText(data.getTitle());
        holder.year.setText(data.getENDate().substring(0,4));
        holder.date.setText(data.getENDate().substring(8,10)+ " - " + data.getENDate().substring(5,7));
    }

    @Override
    public int getItemCount() {
        return newslist.size();
    }

    class NewsHolder extends RecyclerView.ViewHolder{
        TextView news,year,date;
        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            news = itemView.findViewById(R.id.news_text);
            year = itemView.findViewById(R.id.news_year);
            date = itemView.findViewById(R.id.news_date);
        }
    }
}
