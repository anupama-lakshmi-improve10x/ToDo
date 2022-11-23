package com.sunshine.todo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ToDoServices {
    @GET("anupamaTodo")
    Call<List<ToDo>> fetchTasks();

    @POST("anupamaTodo")
    Call<ToDo> createTask(@Body ToDo task);
}
