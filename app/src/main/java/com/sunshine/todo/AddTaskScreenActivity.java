package com.sunshine.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTaskScreenActivity extends AppCompatActivity {
    public ToDo todo;
    public Button updateBtn;
    public Button addTaskBtn;
    public EditText taskItemTxt;
    public EditText taskDescriptionTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task_screen);
        initViews();
        if(getIntent().hasExtra("toDo")){
            getSupportActionBar().setTitle("Edit Task");
            todo = (ToDo) getIntent().getSerializableExtra("toDo");
            updateBtn.setVisibility(View.VISIBLE);
            addTaskBtn.setVisibility(View.GONE);
            showData();
            handleUpdate();
        } else{
            getSupportActionBar().setTitle("Add Task");
            addTaskBtn.setVisibility(View.VISIBLE);
            updateBtn.setVisibility(View.GONE);
            setupAddButton();
        }
    }

    public void initViews(){
        taskItemTxt = findViewById(R.id.task_item_txt);
        taskDescriptionTxt = findViewById(R.id.task_description_txt);
        updateBtn = findViewById(R.id.update_btn);
        addTaskBtn = findViewById(R.id.add_task_btn);
    }

    public void showData(){
        taskItemTxt.setText(todo.task);
        taskDescriptionTxt.setText(todo.taskDescription);
    }

    public void handleUpdate(){
               updateBtn.setOnClickListener(view -> {
            String taskName =taskItemTxt.getText().toString();
            String taskDescription = taskDescriptionTxt.getText().toString();
            updateTask(todo.id,taskName, taskDescription);
        });
    }

    public void updateTask(String id, String taskName, String description){
        ToDo todo = new ToDo();
        todo.task = taskName;
        todo.taskDescription = description;

        ToDoApi toDoApi = new ToDoApi();
        ToDoServices toDoServices = toDoApi.createTodoServices();
        Call<Void> call = toDoServices.updateTask(id, todo);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(AddTaskScreenActivity.this, "Sucessfully Completed", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddTaskScreenActivity.this, "Failed to update task", Toast.LENGTH_SHORT).show();
            }
        });
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
        addTaskBtn.setOnClickListener(view -> {
            String taskName = taskItemTxt.getText().toString();
            String taskDescription = taskDescriptionTxt.getText().toString();
            addTask(taskName, taskDescription);
        });
    }
}