package com.sunshine.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class TakeListScreenActivity extends AppCompatActivity {
    public ArrayList<ToDo> toDoLists;
    public RecyclerView listsRv;
    public ToDoAdapter toDoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_list_screen);
        getSupportActionBar().setTitle("Task List");
        setupData();
        setupToDoListRecyclerView();
        setupAddButton();
    }

    private void setupAddButton() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddTaskScreenActivity.class);
            startActivity(intent);
        });
    }

    private void setupToDoListRecyclerView() {
        listsRv = findViewById(R.id.list_rv);
        listsRv.setLayoutManager(new LinearLayoutManager(this));
        toDoAdapter = new ToDoAdapter();
        toDoAdapter.setData(toDoLists);
        listsRv.setAdapter(toDoAdapter);
    }

    private void setupData() {
       toDoLists = new ArrayList<>();
       ToDo getVegetables = new ToDo();
       getVegetables.task = "Get Vegetables";
       getVegetables.taskDescription = "for 1 week";
       toDoLists.add(getVegetables);

       ToDo readingNews = new ToDo();
       readingNews.task = "Reading News";
       readingNews.taskDescription ="Explore politics, flimy and sport news";
       toDoLists.add(readingNews);

       ToDo prepareLunch = new ToDo();
       prepareLunch.task = "Prepare Lunch";
       prepareLunch.taskDescription = "Briyani and Raitha.Yummyyy";
       toDoLists.add(prepareLunch);

       ToDo haveBreakfast = new ToDo();
       haveBreakfast.task = "Have BreakFast";
       haveBreakfast.taskDescription = "Healthy Breakfast for Better Morning";
       toDoLists.add(haveBreakfast);
    }
}