package com.aelleon.theroommateapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText message;
    private ListView items;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;

    //Database
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        message = (EditText) findViewById(R.id.message);
        items = (ListView) findViewById(R.id.items);

        arrayList = new ArrayList<String>();

        // Adapter: You need three parameters 'the context, id of the layout (it will be where the data is shown),
        // and the array that contains the data
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);

        items.setAdapter(adapter);

    }

    public void onButton(View view) {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message").child("User").push();
        String mess = message.getText().toString();


        /*Context context = getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, myRef.toString(), duration);
        toast.show();*/

        // this line adds the data of your EditText and puts in your array
        arrayList.add(message.getText().toString());
        // next thing you have to do is check if your adapter has changed
        adapter.notifyDataSetChanged();

        myRef.setValue(mess);
    }
}
