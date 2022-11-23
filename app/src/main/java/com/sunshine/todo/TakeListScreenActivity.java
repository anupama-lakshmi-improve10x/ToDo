package com.sunshine.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TakeListScreenActivity extends AppCompatActivity {
    public ArrayList<ToDo> toDoLists = new ArrayList<>();
    public RecyclerView listsRv;
    public ToDoAdapter toDoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_list_screen);
        getSupportActionBar().setTitle("Task List");
        setupToDoListRecyclerView();
        setupAddButton();
        }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }

    private void fetchData() {
       ToDoApi toDoApi = new ToDoApi();
       ToDoServices toDoServices = toDoApi.createTodoServices();
        Call<List<ToDo>> call = toDoServices.fetchTasks();
        call.enqueue(new Callback<List<ToDo>>() {
            @Override
            public void onResponse(Call<List<ToDo>> call, Response<List<ToDo>> response) {
                List<ToDo> tasks = response.body();
                toDoAdapter.setData(tasks);
            }

            @Override
            public void onFailure(Call<List<ToDo>> call, Throwable t) {
                Toast.makeText(TakeListScreenActivity.this, "failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
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
}