package com.example.call_it;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpActivity extends AppCompatActivity {

    FirebaseAuth auth;
    EditText emailBox ,passwordBox,nameBox;
    Button already_account,CreateAccount;
    FirebaseFirestore database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_sign_up);
        emailBox = findViewById(R.id.emailBox);
        passwordBox = findViewById(R.id.passwordBox);
        nameBox = findViewById(R.id.username);
        CreateAccount = findViewById(R.id.CreateButton);

        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password,name;
                email = emailBox.getText().toString();
                password = passwordBox.getText().toString();
                name = nameBox.getText().toString();

                User user = new User();
                user.setEmail(email);
                user.setPassword(password);
                user.setName(name);

                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //success
                            database.collection("Users").document().set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Intent i = new Intent(SignUpActivity.this,LoginActivity.class);
                                    startActivity(i);
                                }
                            });
                            Toast.makeText(SignUpActivity.this, "Account is Created", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(SignUpActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                
            }
        });

        already_account = findViewById(R.id.Already_Account);
        already_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}