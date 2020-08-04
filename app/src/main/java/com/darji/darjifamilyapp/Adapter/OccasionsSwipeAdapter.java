package com.darji.darjifamilyapp.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.darji.darjifamilyapp.Model.ApiClient;
import com.darji.darjifamilyapp.Model.OccassionsData;
import com.darji.darjifamilyapp.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OccasionsSwipeAdapter extends PagerAdapter {

    private Context context;
    private List<OccassionsData> list;

    public OccasionsSwipeAdapter(Context context, List<OccassionsData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==object);
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root = layoutInflater.inflate(R.layout.occasions_swipe_layout,container,false);

        View background;
        ImageView photo,osp1,osp2;
        TextView t_date,t_age_place; ////B'Date/સ્વ.તા.:   &&   ////Age/સ્થળ:
        TextView title,name,date,contact,age_place,givenby;

        //background layout
        background = root.findViewById(R.id.occasion_background);

        //Images
        photo = root.findViewById(R.id.occasion_photo);
        osp1 = root.findViewById(R.id.osp1);
        osp2 = root.findViewById(R.id.osp2);

        //Fixed Texts
        t_date = root.findViewById(R.id.ofield_date);
        t_age_place = root.findViewById(R.id.ofield_place_age);

        //Dynamic Data
        title = root.findViewById(R.id.occasion_title);
        name = root.findViewById(R.id.occasion_name);
        date = root.findViewById(R.id.occasion_date);
        contact = root.findViewById(R.id.occasion_contact);
        age_place = root.findViewById(R.id.occasion_place_age);
        givenby = root.findViewById(R.id.occasion_givenby);

        //Getting Data
        OccassionsData data = list.get(position);

        //DateFormatting - Occasion Date
        SimpleDateFormat base_format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat base_dformat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat newformat = new SimpleDateFormat("dd MMM yyyy");
        Date d_date = null;
        try {
            d_date = base_format.parse(data.getOccassionDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String odate = newformat.format(d_date);
        //DateFormatting - Death Date
        try {
            d_date = base_dformat.parse(data.getDeathDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String ddate = newformat.format(d_date);

        if(data.getOccassionType()==2)
        {
            background.setBackgroundResource(0);
            title.setText("બેસણું - "+odate+" ("+data.getOccassionTime()+")");
            t_date.setText("સ્વ.તા.: ");
            t_age_place.setText("સ્થળ: ");
            osp1.setVisibility(View.VISIBLE);
            osp2.setVisibility(View.VISIBLE);
            date.setText(ddate);
            age_place.setText(data.getAddress());
        }
        else
        {
            background.setBackgroundResource(R.mipmap.birthday_bg);
            title.setText("Happy Birthday");
            t_date.setText("B'Date: ");
            t_age_place.setText("Age: ");
            osp1.setVisibility(View.INVISIBLE);
            osp2.setVisibility(View.INVISIBLE);
            date.setText(odate);
            //TODO Generate Age
            age_place.setText(""+getAge(odate));
        }

        Picasso.get()
                .load(Uri.parse(ApiClient.BASE_URL+"Uploads/Occassions/"+data.getPhoto()))
                .placeholder(R.mipmap.occasion_photo)
                .error(R.mipmap.occasion_photo)
                .into(photo);

        name.setText(data.getName() + " ("+data.getNativePlace()+")");
        contact.setText(data.getContactNumber());
        givenby.setText(data.getGivenBy());

        container.addView(root);
        return root;
    }
    private int getAge(String dobString){

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
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
        return age+1;
    }
}
