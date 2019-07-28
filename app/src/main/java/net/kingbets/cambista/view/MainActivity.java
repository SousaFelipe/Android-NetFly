package net.kingbets.cambista.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import android.support.v7.widget.Toolbar;
import android.view.Menu;

import net.kingbets.cambista.R;
import net.kingbets.cambista.utils.URL;
import net.kingbets.cambista.view.fragments.CampeonatosFragmnet;
import net.kingbets.cambista.view.fragments.MinhaContaFragment;
import net.kingbets.cambista.view.fragments.PartidasFragment;


public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {



    private Fragment lastFragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setLoader(R.id.content_loader);

        BottomNavigationView navigation = findViewById(R.id.bottom_nav);
        navigation.setOnNavigationItemSelectedListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    @Override
    protected void onStart() {
        super.onStart();
        URL.build(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadFragment(PartidasFragment.newInstance(this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.action_refresh:

                break;

            case R.id.action_https:
                switchHttps(this);
                break;

            case R.id.action_autologin:
                switchAutoLogin(this);
                break;

            case R.id.action_fechar:
                realyCloseApp(this);
                break;

            case R.id.action_exit:
                realyExitApp(this);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch (item.getItemId()) {

            case R.id.bottom_nav_partidas:
                fragment = PartidasFragment.newInstance(this);
                break;

            case R.id.bottom_nav_campeonatos:
                fragment = CampeonatosFragmnet.newInstance(this);
                break;

            case R.id.bottom_nav_conta:
                fragment = MinhaContaFragment.newInstance(this);
                break;
        }

        return loadFragment(fragment);
    }



    private boolean loadFragment(Fragment fragment) {

        if (fragment != null && lastFragment != fragment) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_fragments, fragment).commit();
            lastFragment = fragment;
            return true;
        }

        return false;
    }
}
