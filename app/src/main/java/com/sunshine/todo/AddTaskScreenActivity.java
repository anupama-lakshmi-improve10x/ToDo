package com.sunshine.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddTaskScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task_screen);
        getSupportActionBar().setTitle("Add Task");
        setupAddButton();
    }

    private void setupAddButton() {
        Button addTaskBtn = findViewById(R.id.add_task_btn);
        addTaskBtn.setOnClickListener(view -> {
            Intent addTaskIntent = new Intent(this,TakeListScreenActivity.class);
            startActivity(addTaskIntent);
        });
    }
}