package com.sunshine.todo;

import com.google.gson.annotations.SerializedName;

public class ToDo {
    @SerializedName("_id")
    public String id;
    public String task;
    @SerializedName("description")
    public String taskDescription;
}
