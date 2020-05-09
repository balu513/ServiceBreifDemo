package com.balu.servicebreifdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.balu.servicebreifdemo.ui.AIDLActivity;
import com.balu.servicebreifdemo.ui.BindServiceActivity;
import com.balu.servicebreifdemo.ui.IntentServiceActivity;
import com.balu.servicebreifdemo.ui.JobServiceFragment;
import com.balu.servicebreifdemo.ui.MessangerActivity;
import com.balu.servicebreifdemo.ui.StartServiceActivity;
import com.balu.servicebreifdemo.ui.WorkManagerFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private String selectedCountry;
    private View welcomeAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcomeAnim = findViewById(R.id.welocome_anim);
        welcomeAnim.setVisibility(View.VISIBLE);
        dl = findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl, R.string.open, R.string.close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.bound_service:
                        startActivity(new Intent(MainActivity.this, BindServiceActivity.class));
                        break;
                    case R.id.un_bind_service:
                        startActivity(new Intent(MainActivity.this, StartServiceActivity.class));
                        break;
                    case R.id.intent_service:
                         startActivity(new Intent(MainActivity.this, IntentServiceActivity.class));
                        break;
                    case R.id.messanger:
                        startActivity(new Intent(MainActivity.this, MessangerActivity.class));
                        break;
                    case R.id.aidl:
                        startActivity(new Intent(MainActivity.this, AIDLActivity.class));
                        break;
                    case R.id.job_service:
                       launchFragment(null,new JobServiceFragment());
                        break;
                    case R.id.work_manager:
                        launchFragment(null, new WorkManagerFragment());
                        break;
                }
                dl.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    public void launchFragment(Bundle bundle, Fragment fragment){
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (t.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
}

