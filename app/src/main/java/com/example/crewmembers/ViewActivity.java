package com.example.crewmembers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        TextView textView = findViewById(R.id.textView);

        List<CrewMember> users = MainActivity.crewMemberDatabase.myDao().getUsers();

        String info ="";
        for(CrewMember usr : users)
        {
            String status = usr.getStatus();
            String name = usr.getName();

            info = info+"\n\n"+"status :"+status+"\n Name :"+name+"\n";
        }
        textView.setText(info);

    }
}