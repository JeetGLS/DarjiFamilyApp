package com.darji.darjifamilyapp.ui.matrimonial;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.darji.darjifamilyapp.Adapter.MatrimonialAdapter;
import com.darji.darjifamilyapp.Adapter.MatrimonialView;
import com.darji.darjifamilyapp.Model.ApiClient;
import com.darji.darjifamilyapp.Model.ApiInterface;
import com.darji.darjifamilyapp.Model.MatrimonialData;
import com.darji.darjifamilyapp.R;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatrimonialFragment extends Fragment implements MatrimonialAdapter.OnMatrimonialListener {

    TextView candidateCount;

    private RecyclerView matrimonial;
    private List<MatrimonialData> matrimonialList;
    private MatrimonialAdapter matrimonialAdapter;

    //Filtering
    Button search,register;
    TextView name,nativep,ageFrom,ageTo;
    Spinner gender,nri;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_matrimonial, container, false);

        candidateCount = root.findViewById(R.id.candidate_count);

        matrimonial = root.findViewById(R.id.matrimonial);
        matrimonial.setHasFixedSize(true);
        matrimonial.setLayoutManager(new LinearLayoutManager(getContext()));


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<MatrimonialData>> data = apiService.getMatrimonial();

        data.enqueue(new Callback<List<MatrimonialData>>() {
            @Override
            public void onResponse(Call<List<MatrimonialData>> call, Response<List<MatrimonialData>> response) {
                matrimonialList = response.body();
                if(matrimonialList!=null)
                {
                    matrimonialAdapter = new MatrimonialAdapter(getActivity(),matrimonialList, MatrimonialFragment.this);
                    matrimonial.setAdapter(matrimonialAdapter);
                    setCandidateCount();
                }
            }

            @Override
            public void onFailure(Call<List<MatrimonialData>> call, Throwable t) {
                //Toast.makeText(getContext(),"Network Failed!",Toast.LENGTH_LONG).show();
            }
        });

        final RelativeLayout expandableView = root.findViewById(R.id.candidateExpandableView);
        final View controller = root.findViewById(R.id.candidateSearch);
        final ImageView arrow = root.findViewById(R.id.candidateArrow);
        final CardView cardView = root.findViewById(R.id.matrimonialCard);

        controller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (expandableView.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.VISIBLE);
                    arrow.setImageResource(R.mipmap.collapse);
                } else {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.GONE);
                    arrow.setImageResource(R.mipmap.expand);
                }

            }
        });


        name = root.findViewById(R.id.candidatesearch_name);
        nativep = root.findViewById(R.id.candidatesearch_nativeplace);
        gender = root.findViewById(R.id.candidatesearch_gender);
        nri = root.findViewById(R.id.candidatesearch_nri);
        ageFrom = root.findViewById(R.id.candidatesearch_agefrom);
        ageTo = root.findViewById(R.id.candidatesearch_ageto);

        search = root.findViewById(R.id.candidatesearch_button);
        register = root.findViewById(R.id.candidatesearch_register);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matrimonialAdapter!=null) {
                    matrimonialAdapter.setFilters(
                            name.getText().toString(),
                            nativep.getText().toString(),
                            gender.getSelectedItem().toString(),
                            nri.getSelectedItem().toString(),
                            ageFrom.getText().toString(),
                            ageTo.getText().toString());
                    matrimonialAdapter.notifyDataSetChanged();
                    setCandidateCount();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(ApiClient.BASE_URL+"MatrimonialForm.php"));
                getContext().startActivity(i);
            }
        });

        return root;
    }

    private void setCandidateCount()
    {
        candidateCount.setText("" + matrimonialAdapter.getItemCount());
    }

    @Override
    public void onMatrimonialClick(int position) {

        Gson gson = new Gson();
        String jsonObj = gson.toJson(matrimonialList.get(position));

        Intent intent = new Intent(getContext(),MatrimonialView.class);
        intent.putExtra("data",jsonObj);
        startActivity(intent);

    }
}