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

public class UserMainActivity extends AppCompatActivity {


    private TextView signup2, forgotpass2;
    private EditText email2, password2;
    private Button loginbtn2;
    private ProgressBar progressBar;
    RadioGroup WorkPosition2;
    String selectedWorkPosition;
    RadioButton radioButton;

    private FirebaseAuth mAuth;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        init();
        initlistners();
    }
    private void init()
    {
        email2 = (EditText) findViewById(R.id.email2);
        password2 = (EditText) findViewById(R.id.password2);
        progressBar = (ProgressBar) findViewById(R.id.progressBar3);

        loginbtn2 = (Button) findViewById(R.id.loginbtn2);
        signup2 = (TextView) findViewById(R.id.signup2);
        forgotpass2 = (TextView) findViewById(R.id.forgotpass2);


        WorkPosition2 = (RadioGroup) findViewById(R.id.WorkPosition1);
        selectedWorkPosition="User";

        mAuth = FirebaseAuth.getInstance();  //getting the current instance of the database from the firebase
    }

    private void initlistners()
    {

        signup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), UserSignUp.class);
                startActivity(startIntent);
            }
        });

        loginbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login ();

            }
        });

        forgotpass2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgotPassword(v);
            }
        });
    }

    public void Login () {
        String email_ = email2.getText().toString().trim();
        String password_ = password2.getText().toString().trim();

        if (email_.isEmpty()) {
            email2.setError("Email is required!");
            email2.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email_).matches()) {
            email2.setError("Please provide valid email!");
            email2.requestFocus();
            return;
        }

        if (password_.isEmpty()) {
            password2.setError("Password is required!");
            password2.requestFocus();
            return;
        }

        if (password_.length() < 8) {
            password2.setError("Minimum password length should be 8 characters!");
            password2.requestFocus();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);

        //Authenticate the user

        mAuth.signInWithEmailAndPassword(email2.getText().toString(), password2.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    if(selectedWorkPosition.equals("User")) {

                        Toast.makeText(UserMainActivity.this, "Logged in successfully", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), SecondPage.class));
                        progressBar.setVisibility(View.GONE);
                        finish();
                    }else {
                        Toast.makeText(UserMainActivity.this, "Sorry, previously, you registered as a waiter! ", Toast.LENGTH_LONG).show();
                        return;
                    }
                }else
                {
                    Toast.makeText(UserMainActivity.this, "Sorry, " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
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
                        Toast.makeText( UserMainActivity.this, "Reset Link Sent To Your Email", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText( UserMainActivity.this, "Error! Reset Link is Not Sent"+ e.getMessage(), Toast.LENGTH_LONG).show();
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
