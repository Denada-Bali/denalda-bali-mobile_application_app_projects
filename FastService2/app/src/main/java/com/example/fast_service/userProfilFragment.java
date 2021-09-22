package com.example.fast_service;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class userProfilFragment extends Fragment {

    View view;
    private ImageView LogOut;

    private FirebaseUser Users;
    private DatabaseReference reference;
    private String userID;
    private static final String USERS = "Users";
    private TextView profilFullName, profileUserType, ProfileAddressEmail;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_profil, container, false);

        init();
        initlistners();
       ProfileAccount();
        return view;
    }
    public void  init(){
        LogOut = (ImageView) view.findViewById(R.id.LogOut1);


        profilFullName = (TextView) view.findViewById(R.id.profilFullName_);
        profileUserType = (TextView) view.findViewById(R.id.profileUserType_);
        ProfileAddressEmail = (TextView) view.findViewById(R.id.ProfileAddressEmail_);
    }

    private void initlistners() {
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getActivity(), user_LogOut.class);
                startActivity(startIntent);
            }
        });
    }

    public void ProfileAccount(){
        Users = FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference(USERS);
        userID = Users.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                // setContentView( R.layout.profil_fragment);
                if(userProfile != null){
                    String fullName = userProfile.getFullName();
                    String UsetTper = userProfile.getUserType();
                    String email =userProfile.getEmail();

                    profilFullName.setText(fullName);
                    profileUserType.setText(UsetTper);
                    ProfileAddressEmail.setText(email);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });
    }
}