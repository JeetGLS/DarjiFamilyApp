package com.darji.darjifamilyapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.darji.darjifamilyapp.Model.MatrimonialData;
import com.darji.darjifamilyapp.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MatrimonialAdapter extends RecyclerView.Adapter<MatrimonialAdapter.MyHolder> {
    private Context context;
    private List<MatrimonialData> finalMatrimonial;
    private OnMatrimonialListener onMatrimonialListener;

    public MatrimonialAdapter(Context context,List<MatrimonialData> finalMatrimonial,OnMatrimonialListener onMatrimonialListener){
        this.context = context;
        this.finalMatrimonial = finalMatrimonial;
        this.onMatrimonialListener = onMatrimonialListener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup container, int viewType) {
        View root = LayoutInflater.from(container.getContext()).inflate(R.layout.matrimonial_list,container,false);
        MatrimonialAdapter.MyHolder myHolder = new MatrimonialAdapter.MyHolder(root,onMatrimonialListener);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        final  MatrimonialData mdata = finalMatrimonial.get(position);

        holder.name.setText(mdata.getFirstName()+" "+mdata.getMiddleName()+" "+mdata.getLastName());
        holder.about.setText(mdata.getAboutMe());
        holder.city.setText(mdata.getNativePlace());
        String dateStr = mdata.getBirthDte();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date d = format.parse(dateStr);

            DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
            dateStr = dateFormat.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.date.setText(dateStr);
    }

    @Override
    public int getItemCount() {
        return finalMatrimonial.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name,about;
        Button city,date;
        OnMatrimonialListener onMatrimonialListener;

        public MyHolder(@NonNull View root,OnMatrimonialListener onMatrimonialListener) {
            super(root);
            name = root.findViewById(R.id.candidate_name);
            about = root.findViewById(R.id.about_candidate);
            city = root.findViewById(R.id.btn_city);
            date = root.findViewById(R.id.btn_date);

            this.onMatrimonialListener = onMatrimonialListener;
            root.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onMatrimonialListener.onMatrimonialClick(getAdapterPosition());
        }
    }

    public interface OnMatrimonialListener{
        void onMatrimonialClick(int position);
    }
}
