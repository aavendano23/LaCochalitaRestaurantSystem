package com.example.lacochalitafinal.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lacochalitafinal.Model.UserModel;
import com.example.lacochalitafinal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegister;
    private TextView banner;
    private EditText edtName, edtEmail, edtPassword;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        banner = (TextView)findViewById(R.id.banner);
        banner.setOnClickListener(this);

        btnRegister = (Button)findViewById(R.id.RegisterBtn);
        btnRegister.setOnClickListener(this);

        edtEmail = (EditText)findViewById(R.id.email);
        edtName = (EditText)findViewById(R.id.user_name);
        edtPassword = (EditText)findViewById(R.id.password);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.banner:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.RegisterBtn:
                registerUser();
                break;
        }

    }

    private void registerUser() {
        final String email = edtEmail.getText().toString().trim();
        final String name = edtName.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (name.isEmpty())
        {
            edtName.setError("Full name is requied");
            edtName.requestFocus();
            return;
        }
        if (email.isEmpty())
        {
            edtEmail.setError("Email is required");
            edtEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            edtEmail.setError("Please enter a valid email");
            edtEmail.requestFocus();
            return;
        }
        if (password.isEmpty())
        {
            edtPassword.setError("Password is required");
            edtPassword.requestFocus();
            return;
        }
        if (password.length() < 7)
        {
            edtPassword.setError("Min password should be 7 characters");
            edtPassword.requestFocus();
            return;
        }



        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            //UserModel user = new UserModel();

                            UserModel user = new UserModel(name, email,mAuth.getCurrentUser().getUid());



                            //UserModel userModel = new UserModel();
                            //userModel.setUid(mAuth.getCurrentUser().getUid());


                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(Register.this, "User has successfully registered", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                    else
                                    {
                                        Toast.makeText(Register.this, "Failed to register! Try agiain!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }
                        else
                        {
                            Toast.makeText(Register.this, "Failed to register! ", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}