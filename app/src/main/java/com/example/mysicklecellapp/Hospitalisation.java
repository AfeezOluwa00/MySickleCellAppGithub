package com.example.mysicklecellapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.sickle.healthcareapp.adapter.HospitalisationAdapter;
import com.sickle.healthcareapp.model.Fiche;

public class Hospitalisation extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference FicheRef;
    private HospitalisationAdapter adapter;
    View result;


    public Hospitalisation() {
        // Required empty public constructor
    }


    public static Hospitalisation newInstance() {
        Hospitalisation fragment = new Hospitalisation();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        result = inflater.inflate(R.layout.fragment_hospitalisation, container, false);
        setUpRecyclerView();
        return result;
    }
    private void setUpRecyclerView() {
        String email_id = getActivity().getIntent().getExtras().getString("patient_email");
        FicheRef = db.collection("Patient").document(email_id).collection("MyMedicalFolder");
        Query query = FicheRef.whereEqualTo("type", "Hospitalisation");

        FirestoreRecyclerOptions<Fiche> options = new FirestoreRecyclerOptions.Builder<Fiche>()
                .setQuery(query, Fiche.class)
                .build();

        adapter = new HospitalisationAdapter(options);

        RecyclerView recyclerView = result.findViewById(R.id.hospitalisationRecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}