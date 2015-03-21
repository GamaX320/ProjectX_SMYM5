//package com.example.bryanty.projectx_smym5;
//
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarActivity;
//import android.os.Bundle;
//import android.support.v7.widget.Toolbar;
//import android.view.Menu;
//import android.view.MenuItem;
//
//
//public class SetPreferenceActivity extends ActionBarActivity implements NavigationDrawerCallbacks{
//    private Toolbar mToolbar;
//    private NavigationDrawerFragment mNavigationDrawerFragment;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_set_preference);
//
//        //replace PrefsFragment
//        getFragmentManager().beginTransaction().replace(R.id.container_settings,new PrefsFragment()).commit();
//
//        //initial toolbar/ action bar
//        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//
//        //initial navigation components
////        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
////        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_set_preference, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public void onNavigationDrawerItemSelected(int position) {
//
//    }
//}
