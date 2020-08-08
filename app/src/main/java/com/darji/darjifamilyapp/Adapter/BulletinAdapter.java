package com.darji.darjifamilyapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.darji.darjifamilyapp.Model.BulletinData;
import com.darji.darjifamilyapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BulletinAdapter extends RecyclerView.Adapter<BulletinAdapter.MyHolder> {
    private Context context;
    private List<BulletinData> finalBulletins;

    public BulletinAdapter(Context context,List<BulletinData> finalBulletins) {
        this.context = context;
        this.finalBulletins = finalBulletins;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup container, int viewType) {
        View root= LayoutInflater.from(container.getContext()).inflate(R.layout.bulletin_list,container,false);
        BulletinAdapter.MyHolder myHolder=new BulletinAdapter.MyHolder(root);
        return myHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {

        final BulletinData ddata = finalBulletins.get(position);

        SimpleDateFormat inputMonthFormat = new SimpleDateFormat("M");
        SimpleDateFormat outputMonthFormat = new SimpleDateFormat("MMMM");
        Date month;
        String m;
        try {
            month = inputMonthFormat.parse(Integer.toString(ddata.getMonth()));
            m = outputMonthFormat.format(month);
        } catch (ParseException e) {
            m="Month";
            e.printStackTrace();
        }
        holder.name.setText(ddata.getTitle()+" ("+m+" - "+ddata.getYear()+")");
        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.shri76goldarjikelavanimandal.com/Uploads/Bulletins/"+ddata.getBulletinPath()));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return finalBulletins.size();
    }

    class MyHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        Button download;
        public MyHolder(@NonNull View root) {
            super(root);
            name = root.findViewById(R.id.bulletin_name);
            download = root.findViewById(R.id.bulletin_download);
            download.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction())
                    {
                        case MotionEvent.ACTION_DOWN:
                            view.setBackground(context.getResources().getDrawable(R.drawable.button_download1_pressed));
                            break;
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL:
                            view.setBackground(context.getResources().getDrawable(R.drawable.button_download1));
                            break;
                    }
                    return false;
                }
            });
        }
    }
}
