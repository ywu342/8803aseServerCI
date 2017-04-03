package edu.gatech.seclass.partyplaylist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UserHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
    }

    public void callLoginView(View view)
    {
        Intent i = new Intent(getApplicationContext(),Login.class);
        startActivity(i);
    }
}
