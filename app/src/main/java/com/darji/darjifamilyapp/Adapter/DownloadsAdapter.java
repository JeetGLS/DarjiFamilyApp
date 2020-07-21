package com.darji.darjifamilyapp.Adapter;

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

import com.darji.darjifamilyapp.R;

public class DownloadsAdapter extends RecyclerView.Adapter<DownloadsAdapter.MyHolder> {
    private Context context;
    private String names[]={"ટેલિફોન ડાયરી","ધોરણ 10 ના ચમકતા તારલા-2019","શ્રદ્ધાંજલિ યાદી","MEDICAL HELP","સ્વાસ્થ્ય સમાચાર","રાહતકીટ માટે દાન આપનાર દાતાઓની યાદી"};
    private String links[]={"https://www.shri76goldarjikelavanimandal.com/Uploads/Downloads/TelephoneDiary.pdf","https://www.shri76goldarjikelavanimandal.com/Uploads/Downloads/Std.10%20Chamakta%20Tarla-2019.pdf","https://www.shri76goldarjikelavanimandal.com/Uploads/Downloads/Swargvas%20List.pdf","https://www.shri76goldarjikelavanimandal.com/Uploads/Downloads/MEDICAL%20HELP.pdf","https://www.shri76goldarjikelavanimandal.com/Uploads/Downloads/Vinanti.pdf","https://www.shri76goldarjikelavanimandal.com/Uploads/Downloads/Rahatkit%20Bulletin%20Ank%203.pdf"};

    public DownloadsAdapter(Context context)
    {
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup container, int viewType) {
        View root= LayoutInflater.from(container.getContext()).inflate(R.layout.download_list,container,false);
        DownloadsAdapter.MyHolder myHolder=new DownloadsAdapter.MyHolder(root);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        holder.name.setText(names[position]);
        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Put condition where if filename == "" then show toast "coming soon"
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(links[position]));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    class MyHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        Button download;
        public MyHolder(@NonNull View root) {
            super(root);
            name = root.findViewById(R.id.download_name);
            download = root.findViewById(R.id.button_download);
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
