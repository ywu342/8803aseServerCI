package edu.gatech.seclass.partyplaylist;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by andy on 3/18/17.
 */

public class Register extends AppCompatActivity {
    private final String LOG_TAG = "PartyPlaylist.Register";
    private final String REGISTER_ENDPOINT = "https://www.google.com";

    private EditText usernameField;
    private EditText passwordField;
    private EditText emailField;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        setTitle("Register");

        usernameField = (EditText) findViewById(R.id.username_field);
        passwordField = (EditText) findViewById(R.id.password_field);
        emailField = (EditText) findViewById(R.id.email_field);
        submitButton = (Button) findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();
                String email = emailField.getText().toString();

                // TODO Implement server connection
                RegisterRequest serverRequest = new RegisterRequest();
                serverRequest.execute(username, password, email);
            }
        });
    }

    private class RegisterRequest extends AsyncTask<String, Void, RequestStatus> {
        @Override
        protected void onPreExecute() {
            // Runs on UI thread
        }

        @Override
        protected RequestStatus doInBackground(String... params) {
            // Runs on the background thread
            if (params.length != 3) {
                // 3 fields to fill in
                return RequestStatus.ERROR_MISSING_FIELD;
            }
            Log.d(LOG_TAG, "Sending a server request to create an account with username "
                    + params[0] + ", password " + params[1] + ", email " + params[2]);

            try {
                // set up request
                String data = URLEncoder.encode("username", "UTF-8")
                        + "=" + URLEncoder.encode(params[0], "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "="
                        + URLEncoder.encode(params[1], "UTF-8");
                data += "&" + URLEncoder.encode("email", "UTF-8")
                        + "=" + URLEncoder.encode(params[2], "UTF-8");

                URL registerEndpointUrl = new URL(REGISTER_ENDPOINT);
                URLConnection connection = registerEndpointUrl.openConnection();

                // TODO uncomment this when the server endpoint is finalized
                // make request
                // connection.setDoOutput(true);
                // OutputStreamWriter writer  = new OutputStreamWriter(connection.getOutputStream());
                // writer.write(data);
                // writer.flush();

                // parse response
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while((line = reader.readLine()) != null) {
                    response.append(line + "\n");
                }

                // TODO make use of response
                Log.d(LOG_TAG, "Server responded with " + response.toString());

            } catch (IOException e) {
                e.printStackTrace();
                return RequestStatus.ERROR_SERVER_CONNECTION;
            }
            return RequestStatus.SUCCESS;
        }

        @Override
        protected void onPostExecute(RequestStatus result) {
            // Runs on the UI thread
            switch (result) {
                case SUCCESS:
                    Toast.makeText(getApplicationContext(), "Account was registered successfully",
                            Toast.LENGTH_SHORT).show();
                    break;
                case ERROR_MISSING_FIELD:
                    Toast.makeText(getApplicationContext(), "Please make sure all fields are " +
                            "filled in",
                            Toast.LENGTH_SHORT).show();
                    break;
                case ERROR_SERVER_CONNECTION:
                    Toast.makeText(getApplicationContext(), "Server failed, try again",
                            Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    private enum RequestStatus {
        SUCCESS, ERROR_MISSING_FIELD, ERROR_SERVER_CONNECTION
    }
}