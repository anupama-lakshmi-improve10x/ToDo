package com.sunshine.todo;

public interface OnItemActionListener {

    void onEdit(ToDo toDo);

    void onDelete(String id);
}
