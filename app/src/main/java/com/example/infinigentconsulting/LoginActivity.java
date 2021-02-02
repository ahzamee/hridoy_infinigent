package com.example.infinigentconsulting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
            String user_id = this._useridText.getText().toString();
            String password = this._passwordText.getText().toString();
            ArrayList<String> passing = new ArrayList();
            passing.add(user_id);
            passing.add(password);
           // new UserAuthCheck().execute(passing);
            onLoginSuccess();
            return;
        }
        onLoginFailed();
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
        Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onLoginFailed() {
        this._loginButton.setEnabled(true);
        Toast.makeText(getApplicationContext(), "Invalid User", Toast.LENGTH_LONG).show();
    }

    public class UserAuthCheck extends AsyncTask<ArrayList<String>, Void, Integer> {
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected void onPostExecute(Integer i) {
            super.onPostExecute(i);
            if (i == 1) {
                onLoginSuccess();

            } else {
                 onLoginFailed();
            }
        }

        protected Integer doInBackground(ArrayList<String>... params) {
            String response = "";
            URL url = null;
            try {
                url = new URL("http://192.168.99.15:8080/AuthService.svc/GetAuth");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                ArrayList<String> passed = params[0];
                HttpURLConnection conn = null;
                try {
                    conn = (HttpURLConnection) url.openConnection();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                assert conn != null;
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/json");
                JSONObject jsonObject = new JSONObject();
                JSONStringer userJson = new JSONStringer().object().key("user").object().key("userid").value(passed.get(0).toString()).key("password").value(((String) passed.get(1)).toString()).endObject().endObject();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(conn.getOutputStream());
                outputStreamWriter.write(userJson.toString());
                outputStreamWriter.close();
                if (conn.getResponseCode() == 200) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while (true) {
                        String line = br.readLine();
                        if (line == null) {
                            break;
                        }
                        response = response + line;
                    }
                } else {
                    response = "";
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            Integer result = 0;
            JSONObject jObject = null;
            if (!response.isEmpty()) {
                try {
                    jObject = new JSONObject(response);
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
                try {
                    result = Integer.parseInt(jObject.getString("GetAuthResult"));
                } catch (JSONException e32) {
                    e32.printStackTrace();
                }
            }
            return result;
        }
    }
}