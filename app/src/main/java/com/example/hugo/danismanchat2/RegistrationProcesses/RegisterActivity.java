package com.example.hugo.danismanchat2.RegistrationProcesses;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hugo.danismanchat2.MainActivity;
import com.example.hugo.danismanchat2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class RegisterActivity extends AppCompatActivity {


    private Button CreateAccountButton;
    private EditText UserEmail, UserPassword, UserPassword2;

    private TextView AlreadyHaveAccountLink;

    private FirebaseAuth mAuth;
    private DatabaseReference RootRef;

    private ProgressDialog loadingBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        RootRef = FirebaseDatabase.getInstance().getReference();

        InitializeField();

        AlreadyHaveAccountLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SendUserToLoginActivity();
            }
        });


        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                CreateNewAccount();
            }
        });
    }

    private void CreateNewAccount()
    {
        String email = UserEmail.getText().toString();
        String password = UserPassword.getText().toString();
        String password2 = UserPassword2.getText().toString();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Lütfen Email adresi giriniz...", Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Lütfen Şifre giriniz...", Toast.LENGTH_SHORT).show();
        }

        else if(!password.equals(password2)){
            Toast.makeText(this, "Şifreniz örtüşmüyor...", Toast.LENGTH_SHORT).show();
        }

        else {

            loadingBar.setTitle("Creating New Account");
            loadingBar.setMessage("Please wait, while we are creating new account for you ");
            loadingBar.setCanceledOnTouchOutside(true);
            loadingBar.show();

           mAuth.createUserWithEmailAndPassword(email, password)
                   .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {

                                FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( new OnSuccessListener<InstanceIdResult>() {
                                    @Override
                                    public void onSuccess(InstanceIdResult instanceIdResult) {
                                        String mToken = instanceIdResult.getToken();
                                        Log.e("Token",mToken);

                                        String currentUserID = mAuth.getCurrentUser().getUid();
                                        RootRef.child("Users").child(currentUserID).setValue("");

                                        //Save to the database
                                        RootRef.child("Users").child(currentUserID).child("device_token")
                                                .setValue(mToken);

                                    }
                                });

                             /*   FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( new OnSuccessListener<InstanceIdResult>() {

                                    @Override
                                    public void onSuccess(InstanceIdResult instanceIdResult) {
                                        deviceToken = instanceIdResult.getToken();
                                        Toast.makeText(RegisterActivity.this, "Device token elde edildi " + deviceToken, Toast.LENGTH_SHORT).show();
                                    }
                                });*/

                                SendUserToMainActivity();
                                Toast.makeText(RegisterActivity.this, "Account Created Succesfully", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                            else
                                {
                                String message = task.getException().toString();
                                Toast.makeText(RegisterActivity.this, "Error :" + message, Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                       }
                   });

        }


    }

    private void InitializeField()
    {
        CreateAccountButton = (Button) findViewById(R.id.register_button);
        UserEmail = (EditText) findViewById(R.id.register_email);
        UserPassword = (EditText) findViewById(R.id.register_password);
        UserPassword2 = (EditText) findViewById(R.id.register_password2);
        AlreadyHaveAccountLink = (TextView) findViewById(R.id.already_have_account_link);

        loadingBar = new ProgressDialog(this);

    }


    private void SendUserToLoginActivity()
    {
        Intent loginIntent= new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }

    private void SendUserToMainActivity()
    {
        Intent mainIntent= new Intent(RegisterActivity.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }



}
