package com.m.deepak.remainder;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper myDB;
    ArrayList<User> userList;
    ListView listView;
    User user;
    List_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DataBaseHelper(this);
        userList=new ArrayList<>();
        Cursor data=myDB.getListContents();
        int numRows=data.getCount();
        if(numRows==0){
            Toast.makeText(MainActivity.this,"The database is empty..",Toast.LENGTH_LONG).show();
        }else{

            while (data.moveToNext()){
                user = new User(data.getString(1),data.getString(2),data.getString(3));
                userList.add(user);
            }
            adapter=new List_Adapter(this,R.layout.list,userList);
            listView= (ListView) findViewById(R.id.list1);
            listView.setAdapter(adapter);
            Toast.makeText(this,"this is called..",Toast.LENGTH_LONG).show();
            userList.addAll(userList);
            adapter.change();
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.item1:
                showDialog();
                break;
            case R.id.item2:

                break;
        }
        return true;
    }
    public void showDialog(){
        AlertDialog.Builder dialogbuilder=new AlertDialog.Builder(this);
        LayoutInflater inflator=this.getLayoutInflater();
        final View dialogview=inflator.inflate(R.layout.dialog,null);
        dialogbuilder.setView(dialogview);

        final EditText mEnter = (EditText) dialogview.findViewById(R.id.editText);
        final EditText mEntersomething = (EditText) dialogview.findViewById(R.id.editText2);
        final DatePicker mDate = (DatePicker) dialogview.findViewById(R.id.datePicker2);


        dialogbuilder.setTitle("Custom Dialog");
        dialogbuilder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String title = mEnter.getText().toString();
                String description = mEntersomething.getText().toString();
                String day = String.valueOf(mDate.getDayOfMonth());
                String month = String.valueOf(mDate.getMonth());
                String year = String.valueOf(mDate.getYear());
                StringBuilder sb = new StringBuilder();
                sb.append(day).append("/").append(month).append("/").append(year);
                String date = sb.toString();
                if(title.length() != 0 && description.length() != 0 && date.length() != 0){
                    AddData(title,description, date);
                    mEnter.setText("");
                    mEntersomething.setText("");
                }else{
                    Toast.makeText(MainActivity.this,"You must put something in the text field!",Toast.LENGTH_LONG).show();
                }
            }

        });
        dialogbuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog b=dialogbuilder.create();
        b.show();

    }
    public void AddData(String Title,String Description, String Date ){
        boolean insertData = myDB.addData(Title,Description,Date);

        if(insertData==true){
            Toast.makeText(MainActivity.this,"Successfully Entered Data!",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this,"Something went wrong :(.",Toast.LENGTH_LONG).show();
        }
    }
}
