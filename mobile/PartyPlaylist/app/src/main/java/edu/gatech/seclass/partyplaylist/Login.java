package edu.gatech.seclass.partyplaylist;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Login extends AppCompatActivity {
    private final String LOG_TAG = "PartyPlaylist.Login";
    private final String LOGIN_ENDPOINT = Api.LOGIN_ENDPOINT;
    private EditText usernameField;
    private EditText passwordField;
    private EditText emailField;
    private enum RequestStatus {
        SUCCESS, ERROR_MISSING_FIELD, ERROR_SERVER_CONNECTION, ERROR_MISC
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        setTitle("Login");
        usernameField = (EditText) findViewById(R.id.username_field);
        passwordField = (EditText) findViewById(R.id.password_field);
        emailField = (EditText) findViewById(R.id.email_field);
    }

    public void callRegisterView(View view)
    {
        Intent i = new Intent(getApplicationContext(),Register.class);
        startActivity(i);
    }

    public void loginUser(View view){
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        String email = emailField.getText().toString();

        // TODO Implement server connection
        LoginRequest serverRequest = new LoginRequest();
        serverRequest.execute(username, password, email);
    }

    private class LoginRequest extends AsyncTask<String, Void, Login.RequestStatus> {
        private String errorDetail = null;

        @Override
        protected void onPreExecute() {
            // Runs on UI thread
        }

        @Override
        protected Login.RequestStatus doInBackground(String... params) {
            // Runs on the background thread
            for (int i=0;i<params.length;i++)
            {
                if(params[i] == null || params[i].length() == 0)
                {
                    return Login.RequestStatus.ERROR_MISSING_FIELD;
                }
            }

            Log.d(LOG_TAG, "Sending a server request to create an account with username "
                    + params[0] + ", password " + params[1]);

            try {
                String urlParameters = new JSONObject()
                        .put("username", params[0])
                        .put("password", params[1])//.toString();
                        .put("email", params[2]).toString();

                byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
                int postDataLength = postData.length;
                String request = LOGIN_ENDPOINT;
                URL url;
                url = new URL(request);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setInstanceFollowRedirects(false);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("charset", "utf-8");
                conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
                conn.setUseCaches(false);
                try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                    wr.write(postData);
                }

                if (!(conn.getResponseCode()/100  == 2)) { // 2xx code means success
                    InputStream _is = conn.getErrorStream();
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(_is));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null) {
                        response.append(line + "\n");
                    }
                    Log.d(LOG_TAG, "Non 2XX response\n" + response);
                    return RequestStatus.ERROR_SERVER_CONNECTION;
                }


                // parse response
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while((line = reader.readLine()) != null) {
                    response.append(line);
                }
                Log.d(LOG_TAG, "Server responded with " + response);

                if(response.toString().isEmpty()) {
                    errorDetail = "User not found";
                    return RequestStatus.ERROR_MISC;
                }

                JSONObject jsonObj = new JSONObject(response.toString());
                //Handle response

            }catch(Exception e){
                e.printStackTrace();
                errorDetail = "Internal Error";
                return RequestStatus.ERROR_MISC;
            }

            return Login.RequestStatus.SUCCESS;
        }

        @Override
        protected void onPostExecute(Login.RequestStatus result) {
            // Runs on the UI thread
            switch (result) {
                case SUCCESS:
                    Toast.makeText(getApplicationContext(), SystemMessages.LOGIN_SUCCESS,
                            Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),UserHome.class);
                    startActivity(i);
                    break;
                case ERROR_MISSING_FIELD:
                    Toast.makeText(getApplicationContext(), SystemMessages.FORM_ERROR ,
                            Toast.LENGTH_SHORT).show();
                    break;
                case ERROR_SERVER_CONNECTION:
                    Toast.makeText(getApplicationContext(), SystemMessages.SERVER_ERROR,
                            Toast.LENGTH_SHORT).show();
                    break;
                case ERROR_MISC:
                    Toast.makeText(getApplicationContext(), errorDetail, Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }
}
