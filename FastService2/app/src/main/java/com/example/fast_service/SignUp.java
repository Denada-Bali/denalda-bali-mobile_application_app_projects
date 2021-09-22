package com.example.fast_service;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {

    private EditText Signup_Email_, Signup_fullName_ , Signup_phone_ , Signup_Password_ ;
    private Button _signup ;
    private ProgressBar progressBar;
    private CheckBox checkBox__;
    RadioGroup WorkPosition_;
    String _selectedWorkPosition;
    GoogleApiClient googleApiClient;
    RadioButton radioButton_;
    private FirebaseAuth mAuth;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();
        initlistners();
        Validation();
    }

    private void init()
    {
        Signup_Email_ = (EditText) findViewById(R.id.signup_Email);
        Signup_fullName_ = (EditText) findViewById(R.id.signup_fullName);
        Signup_phone_ = (EditText) findViewById(R.id.signup_phone);
        Signup_Password_ = (EditText) findViewById(R.id.signup_Password);

        WorkPosition_ = (RadioGroup) findViewById(R.id.WorkPosition);
        _selectedWorkPosition="Waiter";

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);

        _signup = (Button)findViewById(R.id.signup_);

        // Assign variable
        checkBox__ = (CheckBox) findViewById(R.id.checkBox);

        mAuth = FirebaseAuth.getInstance();  //getting the current instance of the database from the firebase
    }

    private void initlistners()
    {
        _signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp_();
            }
        });

        WorkPosition_.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                radioButton_ = (RadioButton) findViewById(checkedId);
                _selectedWorkPosition   = radioButton_.getText().toString();
            }
        });
    }
    public void SignUp_() {
        String Email = Signup_Email_.getText().toString().trim();
        String FullName = Signup_fullName_.getText().toString().trim();
        String PhoneNumber = Signup_phone_.getText().toString().trim();
        String Password = Signup_Password_.getText().toString().trim();


        if (Email.isEmpty()) {
            Signup_Email_.setError("Email is required!");
            Signup_Email_.requestFocus();
            return;
        }

        if (FullName.isEmpty()) {
            Signup_fullName_.setError("Full name is required!");
            Signup_fullName_.requestFocus();
            return;
        }

        if (PhoneNumber.isEmpty()) {
            Signup_phone_.setError("Phone Number is required!");
            Signup_phone_.requestFocus();
            return;
        }
        if (Password.isEmpty()) {
            Signup_Password_.setError("Password is required!");
            Signup_Password_.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            Signup_Email_.setError("Please provide valid Email!");
            Signup_Email_.requestFocus();
            return;
        }

        if (Password.length() < 8) {
            Signup_Password_.setError("Minimum Password length should be 8 characters!");
            Signup_Password_.requestFocus();
            return;
        }

        if (!checkBox__.isChecked()) {
            checkBox__.setError("Invalid Verification! ");
            checkBox__.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        //register the user in the firebase
        mAuth.createUserWithEmailAndPassword(Signup_Email_.getText().toString(), Signup_Password_.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    String uid=task.getResult().getUser().getUid();

                    User user = new User(uid, Signup_Email_.getText().toString(), Signup_fullName_.getText().toString(), Signup_phone_.getText().toString(), Signup_Password_.getText().toString(),_selectedWorkPosition);

                    firebaseDatabase.getReference().child("Users").child(uid).setValue(user);

                    //Toast.makeText(SignUp.this, "User has been registered successfully", Toast.LENGTH_LONG).show();
                    //startActivity(new Intent(getApplicationContext(), FirstPage.class));
                    //progressBar.setVisibility(View.GONE);

                    if(_selectedWorkPosition.equals("Waiter"))
                    {
                      Toast.makeText(SignUp.this, "User has been registered successfully", Toast.LENGTH_LONG).show();
                      startActivity(new Intent(getApplicationContext(), FirstPage.class));
                      progressBar.setVisibility(View.GONE);
                    }else
                    {
                        Toast.makeText(SignUp.this, "Oops! Wrong category.", Toast.LENGTH_LONG).show();
                       // startActivity(new Intent(getApplicationContext(), UserDashboard.class));
                        progressBar.setVisibility(View.GONE);
                        return;
                    }

                    //redirect to login layaut
                }else{
                    Toast.makeText(SignUp.this, "Failed to register! Try again", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    public void Validation(){
        // Put siteKey as a string
        String siteKey = "6LfeN8vUaAAAAAEqPk3EMivE6L3EMjHD4AgfhJ3NJ";

        // Create a google Api client
                googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(SafetyNet.API)
                .addConnectionCallbacks( SignUp.this)
                .build();
                googleApiClient.connect();

        checkBox__.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set if condition when checkBox checked
                if (checkBox__.isChecked()) {
                    SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient, siteKey)
                            .setResultCallback(new ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {
                                @Override
                                public void onResult(@NonNull SafetyNetApi.RecaptchaTokenResult recaptchaTokenResult) {
                                    Status status = recaptchaTokenResult.getStatus();
                                    if (status != null && status.isSuccess()) {
                                        //Display Successful Message
                                        Toast.makeText(getApplicationContext(), "Successfully Varified...", Toast.LENGTH_LONG).show();
                                        //Change checkbox text color
                                        checkBox__.setTextColor(Color.GREEN);
                                    }
                                }
                            });
                }else{
                    //Default checkbox text color
                    checkBox__.setTextColor(Color.BLACK);
                }
            }
        });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    }

