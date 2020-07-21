package com.darji.darjifamilyapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.darji.darjifamilyapp.R;

public class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.ActivitiesHolder> {
    private String title[] = {"Notebok Distributions","Calendar Printing & Distributions","Students Career Guidance Seminar","Sneh Sammelan And Prize Distributions"};
    private String desc[] = {"દર વર્ષે કેળવણી મંડળ દ્વારા આપણા સમાજ ના બાળકો માટે આશરે 1400 ડઝન નોટબૂક 50%(પચાસ ટકા) ના રાહત દરે વિતરણ કરવામાં આવે છે.જેના માટે કેળવણી મંડળ દ્વારા અલગ-અલગ કેન્દ્રો બનાવવામાં આવ્યા છે.દા ત. શાહપુર,કાલુપુર,પાલડી,નારણપુરા,ગાંધીનગર,કલોલ,લાંઘણજ,વિજાપુર,વડોદરા,સુરત,મુંબઈ,પુના વગેરે.... જે-તે વિસ્તાર માં રહેતા વિદ્યાર્થીઓ નજીક નાં કેન્દ્ર માં જઈ તે ખરીદી શકે છે.","","",""};
    private Context context;
    public  ActivitiesAdapter(Context context)
    {
        this.context = context;
    }
    @NonNull
    @Override
    public ActivitiesHolder onCreateViewHolder(@NonNull ViewGroup container, int viewType) {
        View root= LayoutInflater.from(container.getContext()).inflate(R.layout.activities_list,container,false);
        ActivitiesAdapter.ActivitiesHolder holder = new ActivitiesAdapter.ActivitiesHolder(root);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ActivitiesHolder holder, int position) {
        holder.title.setText(title[position]);
        holder.desc.setText(desc[position]);
    }
    @Override
    public int getItemCount() {
        return title.length;
    }

    class ActivitiesHolder extends RecyclerView.ViewHolder
    {
        TextView title,desc;
        public ActivitiesHolder(@NonNull View root) {
            super(root);
            title = root.findViewById(R.id.activity_title);
            desc = root.findViewById(R.id.activity_desc);
        }
    }
}
