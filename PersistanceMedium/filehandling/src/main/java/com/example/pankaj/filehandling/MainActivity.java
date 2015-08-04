package com.example.pankaj.filehandling;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.bean.EmployeeBean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    EditText edtid, edtaddress, edtage;
    Button btnwrite, btnread;
    File dir, fname;
    FileWriter fw;
    FileReader fr;
    BufferedReader br;
    ListView mListView;
    EmployeeBean bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        btnwrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bean.setId(edtid.getText().toString().trim());
                bean.setAddress(edtaddress.getText().toString().trim());
                bean.setAge(Integer.parseInt(edtage.getText().toString().trim()));
                try {
                    fw = new FileWriter(fname, true);
                    fw.write(bean.getId() + "\t");
                    fw.write(bean.getAddress() + "\t");
                    fw.write(bean.getAge() + "\n");
                    Toast.makeText(MainActivity.this, "Data Save", Toast.LENGTH_SHORT).show();
                    refresh();
                } catch (Exception ex) {
                    Log.e("Write Error", ex.toString());
                } finally {
                    try {
                        fw.flush();
                        fw.close();
                    } catch (Exception ex) {
                        Log.e("Close Error", ex.toString());
                    }
                }
            }
        });

        btnread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<EmployeeBean> list = new ArrayList<EmployeeBean>();
                EmployeeBean employeeBean= new EmployeeBean();
                try {
                    fr = new FileReader(fname);
                    br = new BufferedReader(fr);
                    String data = br.readLine();
                    String[] alldata = data.split(",");
                    employeeBean.setId(alldata[0]);
                    employeeBean.setId(alldata[1]);
                    employeeBean.setId(alldata[2]);

                    while (data != null) {
                        list.add(employeeBean);
                        data = br.readLine();
                         alldata = data.split(",");
                        employeeBean.setId(alldata[0]);
                        employeeBean.setId(alldata[1]);
                        employeeBean.setId(alldata[2]);
                    }
                    ArrayAdapter<EmployeeBean> ad = new ArrayAdapter<EmployeeBean>(MainActivity.this, android.R.layout.simple_list_item_1, list);
                    mListView.setAdapter(ad);
                } catch (Exception ex) {
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

    public void initialize() {
        bean = new EmployeeBean();
        edtid = (EditText) findViewById(R.id.edtid);
        edtaddress = (EditText) findViewById(R.id.edtaddress);
        edtage = (EditText) findViewById(R.id.edtage);
        btnwrite = (Button) findViewById(R.id.btnsubmit);
        btnread = (Button) findViewById(R.id.btnread);
        mListView = (ListView) findViewById(R.id.displaydata);

        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        Log.e("Path", path);
        String path1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
        Log.e("Path1", path1);
        dir = new File(path, "/myDir");
        boolean flag = dir.mkdir();
        fname = new File(dir, "employee.txt");
        try {
            boolean fflag = fname.createNewFile();
            if (fflag) {
                fw = new FileWriter(fname);
            } else {
                fw = new FileWriter(fname, true);
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }
    }

    public void refresh() {
        edtid.setText("");
        edtaddress.setText("");
        edtage.setText("");
    }
}
