package com.BrickNet.TaskCenter.service;

import com.BrickNet.TaskCenter.dto.TodoDTO;
import com.BrickNet.TaskCenter.exception.TodoException;

import java.util.List;

public interface TodoService {

    void addToDo(TodoDTO todoDTO) throws TodoException;

    List<TodoDTO> showCreatedToDo(String employeeCode) throws TodoException;

    TodoDTO updateCreatedToDo(Integer id, TodoDTO todoDTO) throws TodoException;

    void deleteCreatedToDo(String employeeCode,String taskName,String assignedTo) throws TodoException;

}
