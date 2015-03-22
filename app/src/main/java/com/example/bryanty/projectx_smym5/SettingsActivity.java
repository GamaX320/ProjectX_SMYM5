package com.example.bryanty.projectx_smym5;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.bryanty.projectx_smym5.domain.History;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SettingsActivity extends ActionBarActivity implements View.OnClickListener,NavigationDrawerCallbacks {
    private Toolbar mToolbar;

    Button btnSave;
    ImageButton btnColor1;
    ImageButton btnColor2;
    ImageButton btnColor3;
    ImageButton btnColor4;
    ImageButton btnColor5;
    ImageButton btnColor6;
    ImageButton btnColor7;

    Integer selectedColor=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnSave=(Button)findViewById(R.id.button_Save);
        btnColor1=(ImageButton)findViewById(R.id.button_color_1);
        btnColor2=(ImageButton)findViewById(R.id.button_color_2);
        btnColor3=(ImageButton)findViewById(R.id.button_color_3);
        btnColor4=(ImageButton)findViewById(R.id.button_color_4);
        btnColor5=(ImageButton)findViewById(R.id.button_color_5);
        btnColor6=(ImageButton)findViewById(R.id.button_color_6);
        btnColor7=(ImageButton)findViewById(R.id.button_color_7);

        btnColor1.setOnClickListener(this);
        btnColor2.setOnClickListener(this);
        btnColor3.setOnClickListener(this);
        btnColor4.setOnClickListener(this);
        btnColor5.setOnClickListener(this);
        btnColor6.setOnClickListener(this);
        btnColor7.setOnClickListener(this);

        //setTitle("Settings");
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        // default method for handling onClick Events..

        btnColor1.setBackgroundColor(Color.parseColor("#DEDEDE"));
        btnColor2.setBackgroundColor(Color.parseColor("#DEDEDE"));
        btnColor3.setBackgroundColor(Color.parseColor("#DEDEDE"));
        btnColor4.setBackgroundColor(Color.parseColor("#DEDEDE"));
        btnColor5.setBackgroundColor(Color.parseColor("#DEDEDE"));
        btnColor6.setBackgroundColor(Color.parseColor("#DEDEDE"));
        btnColor7.setBackgroundColor(Color.parseColor("#DEDEDE"));

        switch(v.getId()){
            case R.id.button_color_1:
                btnColor1.setBackgroundColor(Color.GRAY);
                selectedColor=1;
                break;
            case R.id.button_color_2:
                btnColor2.setBackgroundColor(Color.GRAY);
                selectedColor=2;
                break;
            case R.id.button_color_3:
                btnColor3.setBackgroundColor(Color.GRAY);
                selectedColor=3;
                break;
            case R.id.button_color_4:
                btnColor4.setBackgroundColor(Color.GRAY);
                selectedColor=4;
                break;
            case R.id.button_color_5:
                btnColor5.setBackgroundColor(Color.GRAY);
                selectedColor=5;
                break;
            case R.id.button_color_6:
                btnColor6.setBackgroundColor(Color.GRAY);
                selectedColor=6;
                break;
            case R.id.button_color_7:
                btnColor7.setBackgroundColor(Color.GRAY);
                selectedColor=7;
                break;
            default:
                selectedColor=1;
                break;
        }

//        Log.v("MyActivity", "selected=" + selectedAvatar); //print message to console
    }

    //save button clicked
    public void SaveClicked(View v){
        String FILENAME ="settings_file";

        try {
            FileOutputStream fos=openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(selectedColor.toString().getBytes());
            fos.close();
            Toast.makeText(this, "Settings Saved, required restart application to take effect", Toast.LENGTH_LONG).show();

            //add new history
            //default get today date
            DBHandler dbHandler;
            dbHandler= new DBHandler(this,null,null,1);
            Calendar c = Calendar.getInstance();
            String todayDate="";
            SimpleDateFormat df = new SimpleDateFormat("yyyy-M-dd");
            todayDate = df.format(c.getTime());
            History history=new History("Change theme color ",todayDate);
            dbHandler.addHistory(history);

        } catch (Exception e) {
            Toast.makeText(this, "Unable save settings", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

    }
}
