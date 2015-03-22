package com.example.bryanty.projectx_smym5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.FileInputStream;


//public class MainActivity extends ActionBarActivity implements NavigationDrawerCallbacks, ExpenseFragment.SendMessage{
public class MainActivity extends ActionBarActivity implements NavigationDrawerCallbacks{
    private Toolbar mToolbar;
    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //change navigation drawer pop up effect (activity_main,activity_main_blacktoolbar,activity_main_topdrawer)
        setContentView(R.layout.activity_main);

        //load settings
        String colorString="#3F51B5";
        String FILENAME ="settings_file";
        try{
            FileInputStream fis=openFileInput(FILENAME);
            byte[] reader = new byte[fis.available()];

            while(fis.read(reader)!= -1){

            }
            colorString= findColor(Integer.parseInt(new String(reader)));
            fis.close();
        }catch (Exception e) {
            Toast.makeText(this,"Unable read settings",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        //initial toolbar/ action bar
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        //load color
        mToolbar.setBackgroundColor(Color.parseColor(colorString));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //initial navigation components
        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.action_settings:
//                Intent intent=new Intent(this,SettingsActivity.class);
//                startActivity(intent);

                Intent intent = new Intent();
                intent.setClass(this,SettingsActivity.class);
//                intent.setClass(this,SetPreferenceActivity.class);
                startActivity(intent);
            break;

            case R.id.action_aboutus:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,new AboutUsFragment()).commit();
            break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Toast.makeText(this, "Menu item selected -> " + position, Toast.LENGTH_SHORT).show();
        //add custom navigation drawer
        Fragment objFragment=null;
        switch(position){
            case 0:
                objFragment=new AccountFragment();
                break;
            case 1:
                objFragment=new ExpenseFragment();
                break;
            case 2:
                objFragment=new AboutUsFragment();
                break;
            case 3:
                objFragment=new ReportFragment();
                break;
            case 4:
                //objFragment=new ExpenseTopFragment();
                break;
        }
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container,objFragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }

    //load settings file
    public String findColor(int selectedColor){
        String colorString="#3F51B5";

        switch (selectedColor){
            case 1:
                colorString="#3F51B5";
                break;
            case 2:
                colorString="#E91E63";
                break;
            case 3:
                colorString="#9C27B0";
                break;
            case 4:
                colorString="#2196F3";
                break;
            case 5:
                colorString="#009688";
                break;
            case 6:
                colorString="#FF9800";
                break;
            case 7:
                colorString="#607D8B";
                break;
            default:
                colorString="#3F51B5";
                break;
        }
        return colorString;
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        loadPref();
//    }
//
//    private void loadPref() {
//        SharedPreferences mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//
//        int my_color_preference= mySharedPreferences.getInt("color_prefs",0);
//
//        Log.v("MyActivity","you color selected >>> "+my_color_preference);
//    }
}
