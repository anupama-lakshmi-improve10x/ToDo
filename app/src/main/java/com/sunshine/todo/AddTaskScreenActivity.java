package com.sunshine.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import kotlinx.coroutines.scheduling.Task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTaskScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task_screen);
        getSupportActionBar().setTitle("Add Task");
        setupAddButton();
    }

    public void addTask(String taskName, String description){
       ToDo todo = new ToDo();
       todo.task = taskName;
       todo.taskDescription = description;

       ToDoApi toDoApi = new ToDoApi();
       ToDoServices toDoServices = toDoApi.createTodoServices();
        Call<ToDo> call = toDoServices.createTask(todo);
        call.enqueue(new Callback<ToDo>() {
            @Override
            public void onResponse(Call<ToDo> call, Response<ToDo> response) {
                Toast.makeText(AddTaskScreenActivity.this, "Sucessfully added new task", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ToDo> call, Throwable t) {
                Toast.makeText(AddTaskScreenActivity.this, "Failed to add new task", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setupAddButton() {
        Button addTaskBtn = findViewById(R.id.add_task_btn);
        addTaskBtn.setOnClickListener(view -> {
            EditText taskItemTxt = findViewById(R.id.task_item_txt);
            String taskName = taskItemTxt.getText().toString();
            EditText taskDescriptionTxt = findViewById(R.id.task_description_txt);
            String taskDescription = taskDescriptionTxt.getText().toString();
            addTask(taskName, taskDescription);
        });
    }
}