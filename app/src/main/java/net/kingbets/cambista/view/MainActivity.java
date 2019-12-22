package net.kingbets.cambista.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import android.support.v7.widget.Toolbar;
import android.view.Menu;

import net.kingbets.cambista.R;
import net.kingbets.cambista.alerts.PermissionAlert;
import net.kingbets.cambista.utils.Config;
import net.kingbets.cambista.view.fragments.BaseFragment;
import net.kingbets.cambista.view.fragments.CampeonatosFragmnet;
import net.kingbets.cambista.view.fragments.ClientesFragment;
import net.kingbets.cambista.view.fragments.MinhaContaFragment;
import net.kingbets.cambista.view.fragments.PartidasFragment;


public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {



    private Fragment currentFragment = null;
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
    protected void onResume() {
        super.onResume();

        if (Config.somePermissionDenied(this)) {
            PermissionAlert.show(this);
        }

        currentFragment = PartidasFragment.newInstance(this);
        loadFragment();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.action_refresh:
                if (currentFragment != null) {
                    ((BaseFragment) currentFragment).request();
                }
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

        switch (item.getItemId()) {

            case R.id.bottom_nav_partidas:
                currentFragment = PartidasFragment.newInstance(this);
                break;

            case R.id.bottom_nav_campeonatos:
                currentFragment = CampeonatosFragmnet.newInstance(this);
                break;

            case R.id.bottom_nav_clientes:
                currentFragment = ClientesFragment.newInstance(this);
                break;

            case R.id.bottom_nav_conta:
                currentFragment = MinhaContaFragment.newInstance(this);
                break;
        }

        return loadFragment();
    }



    private boolean loadFragment() {

        if (currentFragment != null && lastFragment != currentFragment) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_fragments, currentFragment).commit();
            lastFragment = currentFragment;
            return true;
        }

        return false;
    }
}
