package com.sunshine.todo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ToDo implements Serializable {
    @SerializedName("_id")
    public String id;
    public String task;
    @SerializedName("description")
    public String taskDescription;
}
