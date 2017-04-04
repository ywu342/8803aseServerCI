package edu.gatech.seclass.partyplaylist;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonWriter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import static edu.gatech.seclass.partyplaylist.Api.LOGIN_ENDPOINT;

/**
 * Created by andy on 3/18/17.
 */

public class Register extends AppCompatActivity {
    private final String LOG_TAG = "PartyPlaylist.Register";
    private final String REGISTER_ENDPOINT = Api.REGISTER_ENDPOINT;
    private EditText usernameField;
    private EditText passwordField;
    private EditText emailField;
    private Button submitButton;
    private TextView loginPageLink;
    private enum RequestStatus {
        SUCCESS, ERROR_MISSING_FIELD, ERROR_SERVER_CONNECTION
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        setTitle("Register");

        usernameField = (EditText) findViewById(R.id.username_field);
        passwordField = (EditText) findViewById(R.id.password_field);
        emailField = (EditText) findViewById(R.id.email_field);
        submitButton = (Button) findViewById(R.id.login_button);
        loginPageLink = (TextView) findViewById(R.id.to_loginpage);

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

    public void callLoginView(View view)
    {
        Intent i = new Intent(getApplicationContext(),Login.class);
        startActivity(i);
    }
    private class RegisterRequest extends AsyncTask<String, Void, RequestStatus> {
        @Override
        protected void onPreExecute() {
            // Runs on UI thread
        }

        @Override
        protected RequestStatus doInBackground(String... params) {
            // Runs on the background thread
            for (int i=0;i<params.length;i++)
            {
                if(params[i] == null || params[i].length() == 0)
                {
                    return RequestStatus.ERROR_MISSING_FIELD;
                }
            }

            Log.d(LOG_TAG, "Sending a server request to create an account with username "
                    + params[0] + ", password " + params[1] + ", email " + params[2]);

            try {
                String data = new JSONObject()
                        .put("username", params[0])
                        .put("password", params[1])
                        .put("email", params[2]).toString();

                URL registerEndpointUrl = new URL(LOGIN_ENDPOINT);
                HttpURLConnection connection = (HttpURLConnection) registerEndpointUrl.openConnection();

                // TODO uncomment this when the server endpoint is finalized
                //make request
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 4.01; Windows NT)");
                connection.setChunkedStreamingMode(0);

                OutputStreamWriter writer  = new OutputStreamWriter(connection.getOutputStream());
                writer.write(data);
                writer.flush();

                if (!(connection.getResponseCode()  == 200)) { // 2xx code means success
                    InputStream _is = connection.getErrorStream();
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(_is));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null) {
                        response.append(line + "\n");
                    }
                    Log.i("Error != 2xx", response.toString());
                    return RequestStatus.ERROR_SERVER_CONNECTION;
                }


                // parse response
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while((line = reader.readLine()) != null) {
                    response.append(line);
                }


                JSONObject jObject  = new JSONObject(response.toString()); // json
                JSONObject jsonData = jObject.getJSONObject("data"); // get data object
                Log.d(LOG_TAG, "Server responded with " + jsonData.toString());
                //handle response


            } catch (IOException e) {
                e.printStackTrace();
                return RequestStatus.ERROR_SERVER_CONNECTION;
            }
            catch (JSONException e) {
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
                    Toast.makeText(getApplicationContext(), SystemMessages.REGISTRATION_SUCCESS,
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