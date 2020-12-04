package com.example.lacochalitafinal.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lacochalitafinal.Common.Common;
import com.example.lacochalitafinal.Model.UserModel;
import com.example.lacochalitafinal.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView register;
    private EditText edtEmail, edtPassword;
    private Button login;

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private ProgressBar progressBar;

    LayoutAnimationController layoutAnimationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        register = (TextView) findViewById(R.id.Register);
        register.setOnClickListener(this);

        login = (Button)findViewById(R.id.LoginBtn);
        login.setOnClickListener(this);

        edtEmail = (EditText)findViewById(R.id.email);
        edtPassword = (EditText)findViewById(R.id.password);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        user = mAuth.getCurrentUser();

        /**if (user != null) {
         startActivity(new Intent(getApplicationContext(), Home.class));
         Toast.makeText(MainActivity.this, "Error!" + user.getEmail(), Toast.LENGTH_SHORT).show();
         finish();
         }**/
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.Register:
                startActivity(new Intent(this, Register.class));
                break;
            case R.id.LoginBtn:
                userLogin();
                break;
        }
    }

    private void userLogin()
    {
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

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
            edtPassword.setError("Password is requied");
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

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {
                    //redirect to next acitivity menu
                    //layoutAnimationController = AnimationUtils.loadLayoutAnimation(getApplicationContext(), R.anim.layout_iterm_from_left);
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Failed to login! Check if your email and password are correct and match!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
