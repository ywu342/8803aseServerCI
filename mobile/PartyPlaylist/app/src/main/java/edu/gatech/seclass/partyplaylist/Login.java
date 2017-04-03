package edu.gatech.seclass.partyplaylist;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Login extends AppCompatActivity {
    private final String LOG_TAG = "PartyPlaylist.Login";
    private final String LOGIN_ENDPOINT = Api.LOGIN_ENDPOINT;
    private EditText usernameField;
    private EditText passwordField;
    private enum RequestStatus {
        SUCCESS, ERROR_MISSING_FIELD, ERROR_SERVER_CONNECTION
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        setTitle("Login");
        usernameField = (EditText) findViewById(R.id.username_field);
        passwordField = (EditText) findViewById(R.id.password_field);

    }

    public void callRegisterView(View view)
    {
        Intent i = new Intent(getApplicationContext(),Register.class);
        startActivity(i);
    }

    public void loginUser(View view){
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        // TODO Implement server connection
        LoginRequest serverRequest = new LoginRequest();
        serverRequest.execute(username, password);
    }

    private class LoginRequest extends AsyncTask<String, Void, Login.RequestStatus> {
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
                // set up request
                String data = URLEncoder.encode("username", "UTF-8")
                        + "=" + URLEncoder.encode(params[0], "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "="
                        + URLEncoder.encode(params[1], "UTF-8");

                URL registerEndpointUrl = new URL(LOGIN_ENDPOINT);
                URLConnection connection = registerEndpointUrl.openConnection();

                // TODO uncomment this when the server endpoint is finalized
                // make request
                 connection.setDoOutput(true);
                 OutputStreamWriter writer  = new OutputStreamWriter(connection.getOutputStream());
                 writer.write(data);
                 writer.flush();

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
                return Login.RequestStatus.ERROR_SERVER_CONNECTION;
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
            }
        }
    }
}
