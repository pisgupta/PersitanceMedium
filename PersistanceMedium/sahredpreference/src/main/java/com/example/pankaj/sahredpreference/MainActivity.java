package com.example.pankaj.sahredpreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class MainActivity extends ActionBarActivity {
    EditText edtuname, edtpassword;
    Button btnsubmit, btncancel;
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeContriol();
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map key = mSharedPreferences.getAll();
                Set s = key.keySet();
                Iterator it = s.iterator();
                while(it.hasNext()) {
                    String key1 = it.next().toString();
                    Log.e(key1, "Value is " + mSharedPreferences.getString(key1, "Data not exist"));
                }
                int size = key.size();
                count = size/2;
                String name = edtuname.getText().toString().trim();
                String password = edtpassword.getText().toString().trim();
                mEditor.putString("uname"+count,name);
                mEditor.putString("upasword"+count,password);
                mEditor.commit();
                Toast.makeText(MainActivity.this,"Save",Toast.LENGTH_SHORT).show();
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /**
     * methos are used to initialize activity controls
     */
    public void initializeContriol() {
        edtuname = (EditText) findViewById(R.id.edtusername);
        edtpassword = (EditText) findViewById(R.id.edtpassword);
        btnsubmit = (Button) findViewById(R.id.btnsubmit);
        btncancel = (Button) findViewById(R.id.btncancel);

        mSharedPreferences = getSharedPreferences("login",MODE_MULTI_PROCESS);
        mEditor = mSharedPreferences.edit();
    }
}
