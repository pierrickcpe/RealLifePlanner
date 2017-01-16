package com.example.pierrickvinot.myapplication.activities;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.pierrickvinot.myapplication.R;
import com.example.pierrickvinot.myapplication.models.User;
import com.example.pierrickvinot.myapplication.tools.InternalSearcher;


public class ProfileActivity extends AppCompatActivity {
    TextView tvName, tvDisconnect, tvSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        User user = InternalSearcher.getCredential();
        tvName = (TextView)findViewById(R.id.user_profile_name);
        tvName.setText(user.username);

        tvDisconnect = (TextView)findViewById(R.id.tv_disconnect);
        tvDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InternalSearcher.deleteCredentials();
                Intent intent = new Intent(ProfileActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        tvSwitch = (TextView)findViewById(R.id.tv_switch);
        tvSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                Intent intent = new Intent(ProfileActivity.this,MainActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
