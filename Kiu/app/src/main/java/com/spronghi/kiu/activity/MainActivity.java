package com.spronghi.kiu.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.spronghi.kiu.R;
import com.spronghi.kiu.fragment.FragmentFactory;
import com.spronghi.kiu.fragment.ListPostHelperFragment;
import com.spronghi.kiu.fragment.ListPostKiuerFragment;
import com.spronghi.kiu.fragment.ModelFragment;
import com.spronghi.kiu.model.Kiuer;
import com.spronghi.kiu.model.PostKiuer;
import com.spronghi.kiu.runtime.CurrentUser;

/**
 * Created by MatteoSemolaArena on 08/09/2016.
 */
public class MainActivity extends AppCompatActivity {


    private DrawerLayout mDrawer;

    private NavigationView nvDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main );


        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        // ...From section above...
        // Find our drawer view
        nvDrawer = (NavigationView) findViewById(R.id.activity_main_navigation_view);

        if(CurrentUser.isKiuer()==true){
            nvDrawer.inflateMenu(R.menu.kiuer_drawer_menu);
            if(savedInstanceState==null){

                // Begin the transaction
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                // Replace the contents of the container with the new fragment
                ft.replace(R.id.activity_main_frame_layout, FragmentFactory.getInstance("ListPostKiuerFragment"));

                // Complete the changes added above
                ft.commit();
            }

        } else {
            nvDrawer.inflateMenu(R.menu.helper_drawer_menu);
            if(savedInstanceState==null){

                // Begin the transaction
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                // Replace the contents of the container with the new fragment
                ft.replace(R.id.activity_main_frame_layout, FragmentFactory.getInstance("ListPostHelperFragment"));

                // Complete the changes added above
                ft.commit();
            }
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        // Replace the contents of the container with the new fragment
        ModelFragment<PostKiuer> fragment = FragmentFactory.getInstance("CreatePostKiuerFragment");
        fragment.setModel(new PostKiuer());
        ft.replace(R.id.activity_main_frame_layout, fragment);

        // Complete the changes added above
        ft.commit();

    }


    @Override
    protected void onStart(){
        super.onStart();


    }


    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE! Make sure to override the method with only a single `Bundle` argument
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }


  /*  private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {

        final View coordinatorLayout = (View) findViewById(R.id.drawer_layout);

        if(menuItem.getItemId() == R.id.nav_cancel_post) {
            final Context context = getApplicationContext();
            String text;
            try {
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, context.getString(R.string.postDel), Snackbar.LENGTH_LONG)
                        .setAction(context.getText(R.string.undo), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Snackbar snackbar1 = Snackbar.make(coordinatorLayout, context.getString(R.string.postRest), Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                            }
                        });

                snackbar.show();
            } catch (Exception e) {
                text = context.getString(R.string.opFailed);
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                e.printStackTrace();
            }
        } else {
            // Create a new fragment and specify the fragment to show based on nav item clicked
            Fragment fragment = FragmentFactory.getInstance(menuItem.getItemId());

            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).addToBackStack(null).commit();
        }

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }*/

}

