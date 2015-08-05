package com.example.pankaj.database;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
DBConnection con;
    EditText edtid, edtaddress, edtage;
    Button btnwrite, btnread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        con = new DBConnection(MainActivity.this);

        edtid = (EditText)findViewById(R.id.edtid);
        edtaddress = (EditText)findViewById(R.id.edtaddress);
        edtage = (EditText)findViewById(R.id.edtage);
        btnwrite =(Button)findViewById(R.id.btnsubmit);
        btnwrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmployeeBean bean = new EmployeeBean();
                bean.setId(edtid.getText().toString());
                bean.setAddress(edtaddress.getText().toString());
                bean.setAge(Integer.parseInt(edtage.getText().toString()));

                if(con.insertData(bean)){
                    Toast.makeText(MainActivity.this,"Save",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Not Save",Toast.LENGTH_SHORT).show();
                }
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
}
