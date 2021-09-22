package com.example.fast_service;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private TextView signup, forgotpass;
    private EditText email, password;
    private Button loginbtn;
    private ProgressBar progressBar;
    RadioGroup WorkPosition;
    String selectedWorkPosition;
    RadioButton radioButton;

    private FirebaseAuth mAuth;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initlistners();
    }

    private void init()
    {
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        loginbtn = (Button) findViewById(R.id.loginbtn);
        signup = (TextView) findViewById(R.id.signup);
        forgotpass = (TextView) findViewById(R.id.forgotpass);


      WorkPosition = (RadioGroup) findViewById(R.id.WorkPosition);
        selectedWorkPosition="Waiter";

        mAuth = FirebaseAuth.getInstance();  //getting the current instance of the database from the firebase
    }

    private void initlistners()
    {
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(startIntent);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login ();

            }
        });

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgotPassword(v);
            }
        });
    }

        public void Login () {
            String email_ = email.getText().toString().trim();
            String password_ = password.getText().toString().trim();

            if (email_.isEmpty()) {
                email.setError("Email is required!");
                email.requestFocus();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email_).matches()) {
                email.setError("Please provide valid email!");
                email.requestFocus();
                return;
            }

            if (password_.isEmpty()) {
                password.setError("Password is required!");
                password.requestFocus();
                return;
            }

            if (password_.length() < 8) {
                password.setError("Minimum password length should be 8 characters!");
                password.requestFocus();
                return;
            }


            progressBar.setVisibility(View.VISIBLE);

            //Authenticate the user

            mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){

                        if(selectedWorkPosition.equals("Waiter")) {

                            Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), FirstPage.class));
                            progressBar.setVisibility(View.GONE);
                            finish();
                    }else {
                        Toast.makeText(MainActivity.this, "Sorry, previously, you registered as a user! ", Toast.LENGTH_LONG).show();
                            return;
                    }
                    }else
                    {
                        Toast.makeText(MainActivity.this, "Sorry, " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            });
    }

        public void ForgotPassword(View v){
            EditText resetMail = new EditText (v.getContext());
            AlertDialog.Builder passwordResetDialog =  new AlertDialog.Builder(v.getContext());
            passwordResetDialog.setTitle("Reset Password ?");
            passwordResetDialog.setMessage("Enter Your Email To Received Reset Link.");
            passwordResetDialog.setView(resetMail);

            passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // extra the email and sent reset link

                    String mail = resetMail.getText().toString();
                    mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText( MainActivity.this, "Reset Link Sent To Your Email", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText( MainActivity.this, "Error! Reset Link is Not Sent"+ e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });

            passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // close the dialog
                }
            });
            passwordResetDialog.create().show();
      }
    }
