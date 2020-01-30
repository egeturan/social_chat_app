package com.example.hugo.danismanchat2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hugo.danismanchat2.SearchConsultantActivities.FincDoctorActivity;
import com.example.hugo.danismanchat2.SearchConsultantActivities.FindDietitian;

import static com.example.hugo.danismanchat2.R.id.buttonOfHealthSpecialist;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {

    private Button doctor, dietitian, trainer, psycholog;
    private View contactFragment;

    public ContactsFragment() {
        // Required empty public constructor
    }

    public void insertIntoDb(View v) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view  =  inflater.inflate(R.layout.fragment_contacts, container, false);

        InitializeFields(view);


        doctor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent doctorIntent = new Intent(getContext(), FincDoctorActivity.class);
                startActivity(doctorIntent);
            }
        });

        dietitian.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent uzmanDocI = new Intent(getContext(), FindDietitian.class);
                startActivity(uzmanDocI);
            }
        });


        trainer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent uzmanDocI = new Intent(getContext(), FindDietitian.class);
                startActivity(uzmanDocI);
            }
        });


        psycholog.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent uzmanDocI = new Intent(getContext(), FindDietitian.class);
                startActivity(uzmanDocI);
            }
        });

        return view ;
    }

    private void InitializeFields(View view) {
        doctor = (Button) view.findViewById(buttonOfHealthSpecialist);
        dietitian = (Button) view.findViewById(R.id.buttonForDietitian);
        trainer = (Button) view.findViewById(R.id.buttonForSportTeacher);
        psycholog = (Button) view.findViewById(R.id.buttonForPsychlolog);
    }





}
