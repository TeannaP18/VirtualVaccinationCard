package cosc490.morgan.virtualvaccinationcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText username = (EditText) findViewById(R.id.etUsername);
        EditText password = (EditText) findViewById(R.id.etPassword);

        MaterialButton btnLogin = (MaterialButton) findViewById(R.id.btnLogin);
        MaterialButton btnSignUp= (MaterialButton) findViewById(R.id.btnSignUp);

        //admin and admin
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){ //replace with query
                    //show toast for successful login
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    //open admin page
                    openAdminHome();
                }
                if(username.getText().toString().equals("username") && password.getText().toString().equals("password")){ //replace with query
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    //open user page
                    openUserHome();
                }
                else{
                    //toast for login failure
                    Toast.makeText(LoginActivity.this, "Login FAILED!", Toast.LENGTH_SHORT).show();
                    //open user homepage
                }

            }
        });

        //sign up button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegister();
            }
        });
    }

    //open the admin home page
    public void openAdminHome(){
        //create new intent
        Intent intent = new Intent(this, AdminHomeActivity.class);
        //start activity with that intent
        startActivity(intent);
    }

    //open the user home page
    public void openUserHome(){
        Intent intent = new Intent(this, UserHomeActivity.class);
        startActivity(intent);
    }

    //open the register user homepage
    public void openRegister(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}