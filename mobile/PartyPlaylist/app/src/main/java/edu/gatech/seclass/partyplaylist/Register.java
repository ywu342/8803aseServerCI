package edu.gatech.seclass.partyplaylist;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by andy on 3/18/17.
 */

public class Register extends AppCompatActivity {
    private final String LOG_TAG = "PartyPlaylist.Register";

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

            return RequestStatus.SUCCESS;
        }

        @Override
        protected void onPostExecute(RequestStatus res) {
            // Runs on the UI thread
        }
    }

    private enum RequestStatus {
        SUCCESS, ERROR_MISSING_FIELD, ERROR_SERVER_CONNECTION
    }
}