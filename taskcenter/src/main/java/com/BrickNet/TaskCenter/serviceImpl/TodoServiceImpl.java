package com.BrickNet.TaskCenter.serviceImpl;

import com.BrickNet.TaskCenter.dto.TodoDTO;
import com.BrickNet.TaskCenter.exception.TodoException;
import com.BrickNet.TaskCenter.model.Todo;
import com.BrickNet.TaskCenter.repository.TodoRepository;
import com.BrickNet.TaskCenter.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void addToDo(TodoDTO todoDTO) throws TodoException{

        if (todoDTO==null) {
            throw new TodoException("Details not exist");
        }

        else {
            Todo todo1 = modelMapper.map(todoDTO, Todo.class);
            todoRepository.save(todo1);

            if (!todoDTO.getAssignedTo().equals(todoDTO.getAssignedBy())) {
                Todo t = new Todo();
                t.setTaskName(todo1.getTaskName());
                t.setDescription(todo1.getDescription());
                t.setCompletionDate(todo1.getCompletionDate());
                t.setDueDate(todo1.getDueDate());
                t.setStatus(todo1.getStatus());
                t.setPriority(todo1.getPriority());
                t.setAssignedBy(todo1.getAssignedBy());
                t.setAssignedTo(todo1.getAssignedTo());
                t.setEmployeeCode(todo1.getAssignedTo());

                todoRepository.save(t);
            }
        }

    }

    @Override
    public List<TodoDTO> showCreatedToDo(String employeeCode) throws TodoException {
        if( employeeCode==null || todoRepository.findByStringId(employeeCode)==null){
            throw new TodoException("Details not exist");
        }
        else {
            List<Todo> todoList = todoRepository.findByStringId(employeeCode);
            List<TodoDTO> todoDTOList = new ArrayList<>();
            for (Todo t : todoList) {
                todoDTOList.add(modelMapper.map(t, TodoDTO.class));
            }
            return todoDTOList;
        }
    }

    @Override
    public TodoDTO updateCreatedToDo(Integer id, TodoDTO todoDTO) throws TodoException {

        if(todoRepository.findTodoById(id)==null || todoDTO==null) {
            throw new TodoException("Details not exist");
        }
        else {
            Todo t = todoRepository.findTodoById(id);

            if (!t.getAssignedTo().equals(t.getAssignedBy())) {
                Todo t1 = todoRepository.findRowByAssignedToColumn(t.getTaskName(), t.getAssignedTo());

                t1.setTaskName(todoDTO.getTaskName());
                t1.setDescription(todoDTO.getDescription());
                t1.setCompletionDate(todoDTO.getCompletionDate());
                t1.setDueDate(todoDTO.getDueDate());
                t1.setStatus(todoDTO.getStatus());
                t1.setPriority(todoDTO.getPriority());
                t1.setAssignedBy(todoDTO.getAssignedBy());
                t1.setAssignedTo(todoDTO.getAssignedTo());
                t1.setEmployeeCode(todoDTO.getAssignedTo());

                todoRepository.save(t1);
            }


            t.setTaskName(todoDTO.getTaskName());
            t.setDescription(todoDTO.getDescription());
            t.setCompletionDate(todoDTO.getCompletionDate());
            t.setDueDate(todoDTO.getDueDate());
            t.setStatus(todoDTO.getStatus());
            t.setPriority(todoDTO.getPriority());
            t.setAssignedBy(todoDTO.getAssignedBy());
            t.setAssignedTo(todoDTO.getAssignedTo());
            t.setEmployeeCode(todoDTO.getEmployeeCode());

//        String str = restTemplate.getForObject(uri, String.class);
//        t.setEmployeeCode(str);

            todoRepository.save(t);

            return modelMapper.map(t, TodoDTO.class);
        }
    }

    @Override
    public void deleteCreatedToDo(String employeeCode, String taskName, String assignedTo) throws TodoException {

        if(employeeCode==null || taskName==null || assignedTo==null) {
            throw new TodoException("Details not exist");
        }
        else if (todoRepository.findByStringId(employeeCode)==null) {
            throw new TodoException("employeeCode not exist");
        }
        else if (todoRepository.findByStringTaskName(taskName)==null) {
            throw new TodoException("taskName not exist");
        }
        else if (todoRepository.findByStringAssignTo(assignedTo)==null) {
            throw new TodoException("assignedTo not exist");
        }
        else {
            todoRepository.deleteByStringIdForCreateToDo(employeeCode, taskName);
            if (!employeeCode.equals(assignedTo)) {
                todoRepository.deleteByStringIdForAssignedToDoUser(taskName, assignedTo);
            }
        }

    }

}
