package com.android.firebaseapp.backpacking;

import android.content.DialogInterface;
import android.content.Intent;
import android.preference.DialogPreference;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {

    private EditText mEditTextEmail , mEditTextPassword;
    private Button mBtnLogIn;
    private TextView mSignUpTextView , mForgotTextView;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        auth = FirebaseAuth.getInstance();


      if (auth.getCurrentUser() != null) {

            Intent intent = new Intent(LogInActivity.this, MainActivity.class);
            startActivity(intent);

        }

        mEditTextEmail = findViewById(R.id.EditTextLogMail);
        mEditTextPassword = findViewById(R.id.EditTextLogPw);
        mBtnLogIn = findViewById(R.id.btnLogIn);
        mSignUpTextView = findViewById(R.id.signUpTextView);

        mSignUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this , SignUpActivity.class);
                startActivity(intent);
            }
        });

        mForgotTextView = findViewById(R.id.ForgotTextView);
        mForgotTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(LogInActivity.this , ForgotActivity.class);
              startActivity(intent);
            }
        });

        mBtnLogIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               String email = mEditTextEmail.getText().toString();
                String password = mEditTextPassword.getText().toString();
                auth = FirebaseAuth.getInstance();
                auth.signInWithEmailAndPassword(email ,password).addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(getApplicationContext() , "no user" , Toast.LENGTH_LONG).show();

                        }else {
                            Intent intent = new Intent(LogInActivity.this , MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });
    }
}
