package com.spronghi.kiu.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.spronghi.kiu.R;
import com.spronghi.kiu.fragment.FragmentControl;
import com.spronghi.kiu.fragment.FragmentFactory;
import com.spronghi.kiu.fragment.ModelFragment;
import com.spronghi.kiu.model.Helper;
import com.spronghi.kiu.model.Kiuer;
import com.spronghi.kiu.runtime.CurrentUser;

/**
 * Created by spronghi on 13/09/16.
 */
public class RequestActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView usernameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.activity_main_navigation_view);
        usernameText = (TextView) findViewById(R.id.activity_main_menu_username_text);

        setupMenu(savedInstanceState);
        setupDrawerContent(navigationView);
        //setupUsernameText();
    }

    private void setupMenu(Bundle savedInstanceState){
        if(CurrentUser.isKiuer()){
            navigationView.inflateMenu(R.menu.kiuer_drawer_menu);
            if(savedInstanceState==null){
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, FragmentFactory.getInstance(FragmentControl.LIST_REQUEST_KIUER)).commit();
            }
        } else {
            navigationView.inflateMenu(R.menu.helper_drawer_menu);

            if(savedInstanceState==null){
                //getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, FragmentFactory.getInstance(FragmentControl.LIST_POST_HELPER)).commit();
            }
        }
    }
    private void setupUsernameText(){
        if(CurrentUser.isKiuer()){
            usernameText.setText(CurrentUser.getKiuer().getUsername());
            usernameText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ModelFragment<Kiuer> fragment = FragmentFactory.getInstance(FragmentControl.VIEW_KIUER);
                    fragment.setModel(CurrentUser.getKiuer());
                    getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, fragment).commit();
                }
            });
        } else {
            usernameText.setText(CurrentUser.getHelper().getUsername());
            usernameText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ModelFragment<Helper> fragment = FragmentFactory.getInstance(FragmentControl.VIEW_KIUER);
                    fragment.setModel(CurrentUser.getHelper());
                    getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, fragment).commit();
                }
            });
        }
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
        ModelFragment fragment = FragmentFactory.getInstance(MenuFragmentRegister.getKey(menuItem.getItemId()));
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, fragment).addToBackStack(null).commit();

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }
    private void setupIconColor(NavigationView navigationView){
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
}
