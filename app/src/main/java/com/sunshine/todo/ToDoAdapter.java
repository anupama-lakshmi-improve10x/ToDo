package com.sunshine.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoViewHolder> {

    public List<ToDo> toDoArray;
    public OnItemActionListener onItemActionListener;

    public void setData(List<ToDo> toDoArrayList){
        toDoArray = toDoArrayList;
        notifyDataSetChanged();
    }

    public void setOnItemActionListener(OnItemActionListener actionListener){
        onItemActionListener = actionListener;
    }


    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_items,parent,false);
        ToDoViewHolder toDoViewHolder = new ToDoViewHolder(view);
        return toDoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
        ToDo toDo = toDoArray.get(position);
        holder.taskTxt.setText(toDo.task);
        holder.descriptionTxt.setText(toDo.taskDescription);
        holder.deleteBtn.setOnClickListener(view -> {
            onItemActionListener.onDelete(toDo.id);
        });
    }

    @Override
    public int getItemCount() {
        return toDoArray.size();
    }
}
