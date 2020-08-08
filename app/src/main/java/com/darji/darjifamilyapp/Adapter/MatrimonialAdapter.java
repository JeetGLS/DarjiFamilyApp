package com.darji.darjifamilyapp.Adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.darji.darjifamilyapp.Model.ApiClient;
import com.darji.darjifamilyapp.Model.MatrimonialData;
import com.darji.darjifamilyapp.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MatrimonialAdapter extends RecyclerView.Adapter<MatrimonialAdapter.MyHolder> {
    private Context context;
    private List<MatrimonialData> finalMatrimonial,filteredList;
    private Boolean isFiltered;
    private OnMatrimonialListener onMatrimonialListener;

    public MatrimonialAdapter(Context context,List<MatrimonialData> finalMatrimonial,OnMatrimonialListener onMatrimonialListener){
        this.context = context;
        this.finalMatrimonial = finalMatrimonial;
        this.onMatrimonialListener = onMatrimonialListener;
        isFiltered = false;
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

        final  MatrimonialData mdata = (isFiltered)?filteredList.get(position):finalMatrimonial.get(position);

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
        holder.bdate.setText(dateStr);

        Glide.with(context)
                .load(Uri.parse(ApiClient.BASE_URL+"Uploads/MatrimonialCandidates/"+mdata.getPhoto()))
                .into(holder.photo);

        holder.mainContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        view.setBackground(context.getResources().getDrawable(R.drawable.jobs_list_borders_pressed,context.getTheme()));
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        view.setBackground(context.getResources().getDrawable(R.drawable.jobs_list_borders,context.getTheme()));
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (isFiltered)?filteredList.size():finalMatrimonial.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name,about,city,bdate;
        ImageView photo;
        OnMatrimonialListener onMatrimonialListener;
        View mainContainer;

        public MyHolder(@NonNull View root,OnMatrimonialListener onMatrimonialListener) {
            super(root);
            name = root.findViewById(R.id.candidate_name);
            about = root.findViewById(R.id.about_candidate);
            city = root.findViewById(R.id.candidate_native);
            bdate = root.findViewById(R.id.candidate_bday);
            photo = root.findViewById(R.id.candidate_photo);
            mainContainer = root;

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


    //////Filtering///////////////////
    private String name,nativep,gender,nri,ageFrom,ageTo;
    public void setFilters(String name,String nativep,String gender,String nri,String ageFrom,String ageTo)
    {
        isFiltered = true;
        this.name=name;
        this.nativep=nativep;
        this.gender=gender;
        this.nri=nri;
        this.ageFrom=ageFrom;
        this.ageTo=ageTo;
        updateList();
    }
    private void updateList()
    {
        filteredList = new ArrayList<>();
        for(int i=0;i<finalMatrimonial.size();i++)
        {
            final MatrimonialData data = finalMatrimonial.get(i);
            final int age = getAge(data.getBirthDte());
            Log.d("MatFil","Age = "+age);
            if( (name.equals("") || data.getFirstName().toLowerCase().contains(name.toLowerCase())||data.getMiddleName().toLowerCase().contains(name.toLowerCase())||data.getLastName().toLowerCase().contains(name.toLowerCase())) &&
                    (nativep.equals("") || data.getNativePlace().toLowerCase().contains(nativep.toLowerCase())) &&
                    (gender.equals("Select Gender") || data.getGender().equals(getGender(gender)) ) &&
                    (nri.equals("Is NRI?") || data.getIsNRI().equals(getNRI(nri))) &&
                    (ageFrom.equals("") || Integer.parseInt(ageFrom)<=age) &&
                    (ageTo.equals("") || Integer.parseInt(ageTo)>=age) )
            {
                filteredList.add(data);
            }

        }
    }
    private String getGender(String g)
    {
        if(g.equals("Female"))
            return "2";
        else
            return "1";

    }
    private String getNRI(String g)
    {
        if(g.equals("Yes"))
            return "1";
        else
            return "0";

    }
    private int getAge(String dobString){

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = sdf.parse(dobString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(date == null) return 0;

        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.setTime(date);

        int year = dob.get(Calendar.YEAR);
        int month = dob.get(Calendar.MONTH);
        int day = dob.get(Calendar.DAY_OF_MONTH);

        dob.set(year, month+1, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }
        return age;
    }
}
