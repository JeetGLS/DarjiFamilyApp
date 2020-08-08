package com.darji.darjifamilyapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.darji.darjifamilyapp.Model.JobsData;
import com.darji.darjifamilyapp.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.JobHolder> {

    Context context;
    List<JobsData> joblist,filteredJoblist;
    Boolean isFiltered;

    public JobsAdapter(Context context, List<JobsData> joblist)
    {
        this.context = context;
        this.joblist = joblist;
        isFiltered = false;
    }
    @NonNull
    @Override
    public JobHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.joblist,parent,false);
        JobHolder jobHolder = new JobHolder(root);
        return jobHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JobHolder holder, int position) {

        final JobsData data = (isFiltered)?filteredJoblist.get(position):joblist.get(position);

        holder.sector.setText(data.getSector());
        holder.salary.setText("Salary: " + data.getSalaryFrom() + " - "+data.getSalaryTo());
        holder.title.setText(data.getJobTitle());
        holder.profile.setText(data.getJobProfile());
        holder.company.setText(data.getOrganizationName());
        holder.skills.setText(data.getSkills());
        holder.location.setText(data.getJobLocation());

        String dateStr = data.getExpireDate();
        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date d = format.parse(dateStr);
            @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
            dateStr = dateFormat.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.enddate.setText(dateStr);

        if(data.getContactNumber().equals(""))
            holder.contact.setText("N/A");
        else
            holder.contact.setText(data.getContactNumber());

        holder.mainContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(data.getReferencelink().trim()));
                context.startActivity(i);
            }
        });
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
        return (isFiltered)?filteredJoblist.size():joblist.size();
    }

    class JobHolder extends RecyclerView.ViewHolder
    {
        TextView sector,enddate,salary,title,profile,company,skills,location,contact;
        View mainContainer;
        public JobHolder(@NonNull View root) {
            super(root);
            sector = root.findViewById(R.id.job_sector);
            enddate = root.findViewById(R.id.job_enddate);
            salary = root.findViewById(R.id.job_salary);
            title = root.findViewById(R.id.job_title);
            profile = root.findViewById(R.id.job_profile);
            company = root.findViewById(R.id.job_company);
            skills = root.findViewById(R.id.job_skill);
            location = root.findViewById(R.id.job_location);
            contact = root.findViewById(R.id.job_contact);
            mainContainer = root;
        }
    }


    /////Filtering////////////////////////////////////////////////////////////////////////
    private String title="",location="",skill="",sector="",salaryFrom="",salaryTo="";
    public void setFilters(String title, String location, String skill, String sector, String salaryFrom, String salaryTo)
    {
        isFiltered = true;
        this.title = title;
        this.location = location;
        this.skill = skill;
        this.sector = sector;
        this.salaryFrom = salaryFrom;
        this.salaryTo = salaryTo;
        updateList();
    }
    private void updateList()
    {
        filteredJoblist = new ArrayList<>();
        for(int i=0;i<joblist.size();i++)
        {
            final JobsData data = joblist.get(i);
            if( (title.equals("") || data.getJobTitle().toLowerCase().contains(title.toLowerCase())) &&
                    (location.equals("") || data.getJobLocation().toLowerCase().contains(location.toLowerCase())) &&
                    (skill.equals("") || data.getSkills().toLowerCase().contains(skill.toLowerCase())) &&
                    (sector.equals("All Sectors") || data.getSector().toLowerCase().contains(sector.toLowerCase())) &&
                    (salaryFrom.equals("") || Integer.parseInt(salaryFrom)<=data.getSalaryFrom()) &&
                    (salaryTo.equals("") || Integer.parseInt(salaryTo)>=data.getSalaryTo()) )
            {
                       filteredJoblist.add(data);
            }

        }
    }
}
