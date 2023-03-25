package com.example.team_management_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    ActionBarDrawerToggle drawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.item_home:
                    {
                        Toast.makeText(MainActivity.this,"Home selected", Toast.LENGTH_LONG);
                        break;
                    }
                    case R.id.item_notification:
                    {
                        Toast.makeText(MainActivity.this,"Notification selected", Toast.LENGTH_LONG);
                        break;
                    }
                    case R.id.item_star:
                    {
                        Toast.makeText(MainActivity.this,"Star selected", Toast.LENGTH_LONG);
                        break;
                    }
                    case R.id.item_addPerson:
                    {
                        Toast.makeText(MainActivity.this,"Add person selected", Toast.LENGTH_LONG);
                        break;
                    }
                    case R.id.item_search:
                    {
                        Toast.makeText(MainActivity.this,"Search selected", Toast.LENGTH_LONG);
                        break;
                    }
                    case R.id.item_question:
                    {
                        Toast.makeText(MainActivity.this,"Question selected", Toast.LENGTH_LONG);
                        break;
                    }
                }
                return false;
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }
}