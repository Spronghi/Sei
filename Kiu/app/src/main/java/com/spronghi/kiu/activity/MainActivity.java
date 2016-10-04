package com.spronghi.kiu.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.spronghi.kiu.R;
import com.spronghi.kiu.backgroundservice.NotificationService;
import com.spronghi.kiu.fragment.FragmentControl;
import com.spronghi.kiu.fragment.FragmentFactory;
import com.spronghi.kiu.fragment.ModelFragment;
import com.spronghi.kiu.model.Helper;
import com.spronghi.kiu.model.Kiuer;
import com.spronghi.kiu.runtime.CurrentUser;

import java.util.List;


/**
 * Created by MatteoSemolaArena on 08/09/2016.
 */
public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main );

        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.activity_main_navigation_view);

        setupMenu(savedInstanceState);
        setupDrawerContent(navigationView);

        startService(new Intent(this, NotificationService.class));
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public static void stopService(){
        stopService();
    }
    private void setupMenu(Bundle savedInstanceState){
        if(CurrentUser.isKiuer()){
            navigationView.inflateMenu(R.menu.kiuer_drawer_menu);
            if(savedInstanceState==null){
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, FragmentFactory.getInstance(FragmentControl.LIST_POST_KIUER)).commit();
            }
        } else {
            navigationView.inflateMenu(R.menu.helper_drawer_menu);
            if(savedInstanceState==null){
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, FragmentFactory.getInstance(FragmentControl.LIST_POST_KIUER)).commit();
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }
    private void selectDrawerItem(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.id_kiuer_drawer_menu_icon_posts) {
            ModelFragment<Kiuer> fragment = FragmentFactory.getInstance(MenuFragmentRegister.getKey(menuItem.getItemId()));
            fragment.setModel(CurrentUser.getKiuer());
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, fragment).addToBackStack(null).commit();
        } else if(menuItem.getItemId() == R.id.id_helper_drawer_menu_icon_posts){
            ModelFragment<Helper> fragment = FragmentFactory.getInstance(MenuFragmentRegister.getKey(menuItem.getItemId()));
            fragment.setModel(CurrentUser.getHelper());
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, fragment).addToBackStack(null).commit();
        } else if(menuItem.getItemId() == R.id.id_helper_drawer_menu_icon_your_profile){
            ModelFragment<Helper> fragment = FragmentFactory.getInstance(MenuFragmentRegister.getKey(menuItem.getItemId()));
            fragment.setModel(CurrentUser.getHelper());
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, fragment).addToBackStack(null).commit();
        } else if(menuItem.getItemId() == R.id.id_kiuer_drawer_menu_icon_your_profile){
            ModelFragment<Kiuer> fragment = FragmentFactory.getInstance(MenuFragmentRegister.getKey(menuItem.getItemId()));
            fragment.setModel(CurrentUser.getKiuer());
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, fragment).addToBackStack(null).commit();
        } else {
            ModelFragment fragment = FragmentFactory.getInstance(MenuFragmentRegister.getKey(menuItem.getItemId()));
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, fragment).addToBackStack(null).commit();
        }


        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }
    private void setupIconColor(NavigationView navigationView){}

}

