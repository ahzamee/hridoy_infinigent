package com.example.infinigentconsulting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private static final int REQUEST_SIGNUP = 0;
    private static final String TAG = "LoginActivity";
    Button _loginButton;
    EditText _passwordText;
    EditText _useridText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this._useridText = (EditText) findViewById(R.id.input_userid);
        this._passwordText = (EditText) findViewById(R.id.input_password);
        this._loginButton = (Button) findViewById(R.id.btn_login);
        this._loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LoginActivity.this.login();
            }
        });
    }
    public void login() {
        if (validate()) {

            onLoginSuccess();
            /*String user_id = this._useridText.getText().toString();
            String password = this._passwordText.getText().toString();
            ArrayList<String> passing = new ArrayList();
            passing.add(user_id);
            passing.add(password);
            new UserAuthCheck().execute(passing);*/
            return;
        }
       // onLoginFailed();
    }
    public boolean validate() {
        boolean valid = true;
        String user_id = this._useridText.getText().toString();
        String password = this._passwordText.getText().toString();
        if (user_id.isEmpty()) {
            this._useridText.setError("enter a user id");
            valid = false;
        } else {
            this._useridText.setError(null);
        }
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            this._passwordText.setError("between 4 and 10 alphanumeric characters");
            return false;
        }
        this._passwordText.setError(null);
        return valid;
    }

    public void onLoginSuccess() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("userid", this._useridText.getText().toString());
        Toast.makeText( this, "Successfully Logged In", Toast.LENGTH_SHORT ).show();
        intent.putExtras(bundle);
        startActivity(intent);
    }
}