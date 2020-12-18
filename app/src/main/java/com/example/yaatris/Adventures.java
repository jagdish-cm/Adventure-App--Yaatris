package com.example.yaatris;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Adventures#newInstance} factory method to
 * create an instance of this fragment.
 */

public class Adventures extends Fragment implements AdventureHolder.OnCardListener  {
    private CardView card;
    List<AdventureData> adventureDataList = new ArrayList<>();
    List<Company> companiesDataList = new ArrayList<>();
    RecyclerView recyclerView;
    AdventureAdapter adventureAdapter;
    private ArrayList<AdvnetureModel> models = new ArrayList<>();
    private StorageReference mStorageRef;
    public static ArrayList<Company> companies = new ArrayList<>();
    FirebaseAuth mAuth;


    public Adventures() {
    }

    public static Adventures newInstance() {
        Adventures fragment = new Adventures();
        return fragment;
    }


    public  ArrayList<AdvnetureModel> getModels() {
        return this.models;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_adventures, container, false);
        TextView t = (TextView) v.findViewById(R.id.logintocont);
        TextView t2 = v.findViewById(R.id.textView25);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            t.setVisibility(View.GONE);
            t2.setVisibility(View.GONE);
        }

        EditText search = (EditText) v.findViewById(R.id.search_advnt);
        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_DEL){
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                }
                return false;
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Companies");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                companiesDataList.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Company c = postSnapshot.getValue(Company.class);
                    companiesDataList.add(c);
                    final Company m = new Company();
                    m.setAddress(c.address);
                    m.setEmail(c.email);
                    m.setPhone(c.phone);
                    m.setName(c.name);
                    companies.add(m);
                }
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("AdventureCompanyImages");

                recyclerView = v.findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        adventureDataList.clear();
                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                            AdventureData advnt = postSnapshot.getValue(AdventureData.class);
                            adventureDataList.add(advnt);
                            final AdvnetureModel m = new AdvnetureModel();
                            m.setAdventureName(advnt.adventureName);
                            m.setLocation(advnt.location);
                            m.setCheckpoints(advnt.checkpoints);
                            m.setDesc(advnt.desc);
                            m.setSponsor(advnt.cmail);
//                            m.setSponsor(setName(advnt.cmail));
                            m.setFrom(advnt.from);
                            m.setTo(advnt.to);
                            m.setPrice(advnt.price);
                            m.setImage(R.drawable.udpr);
                            models.add(m);
                        }
                        abc();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        return v;
    }

    private String setName(String email){
        String name = new String();
        for(int i =0 ; i< companies.size(); i++){
            System.out.println("@@@@@@@@@@@@@@@@@@@@  " + companies.get(i).email +" *** " + email);
            if(email == companies.get(i).email){
                name = companies.get(i).name;
                return name;
            }
        }
        return name;
    }

    private String findCompName(final String email) {
        final String[] compname = new String[1];
        return compname[0];
    }

    private void abc(){
        adventureAdapter = new AdventureAdapter(getActivity(), models, this);
        recyclerView.setAdapter(adventureAdapter);
        MainActivity a = new MainActivity();
        a.setModels(this.models);
    }


    private  void openAdventure(){
        Intent in = new Intent(getActivity(), AdventureDetails.class);
        startActivity(in);
    }

    private void filter(String text){
        System.out.println("SLLLLLLLLLLLLLLLLLLLLLLLLLL" + text);
        ArrayList<AdvnetureModel> filteredList = new ArrayList<>();

        for(AdvnetureModel item : models){
            if( item.getAdventureName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);

            }
        }
        adventureAdapter = new AdventureAdapter(getActivity(), filteredList, this);
        recyclerView.setAdapter(adventureAdapter);
        MainActivity a = new MainActivity();
        a.setModels(filteredList);
    }

    @Override
    public void onNoteClick(int position) {
        models.get(position);
        Intent i = new Intent(getActivity(), AdventureDetails.class);
        i.putExtra( "index", position);
        startActivity(i);
    }
}