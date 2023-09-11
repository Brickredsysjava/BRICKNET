package com.BrickNet.TaskCenter.serviceImpl;

import com.BrickNet.TaskCenter.dto.TodoDTO;
import com.BrickNet.TaskCenter.dto.NotificationDTO;
import com.BrickNet.TaskCenter.exception.TodoException;
import com.BrickNet.TaskCenter.model.Todo;
import com.BrickNet.TaskCenter.repository.TodoRepository;
import com.BrickNet.TaskCenter.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.web.reactive.function.client.WebClient;
import javax.management.ServiceNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{

    private WebClient.Builder webClientBuilder;

    public TodoServiceImpl(WebClient.Builder webClientBuilder)
    {
        this.webClientBuilder = webClientBuilder;
    }


    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public TodoDTO addToDo(TodoDTO todoDTO) throws TodoException , ServiceNotFoundException{

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
                t.setActualEndDate(todo1.getActualEndDate());
                t.setActualStartDate(todo1.getActualStartDate());
                t.setEstimatedEndDate(todo1.getEstimatedEndDate());
                t.setEstimatedStartDate(todo1.getEstimatedStartDate());
                t.setStatus(todo1.getStatus());
                t.setPriority(todo1.getPriority());
                t.setAssignedBy(todo1.getAssignedBy());
                t.setAssignedTo(todo1.getAssignedTo());
                t.setEmployeeCode(todo1.getAssignedTo());

                todoRepository.save(t);
            }

            try {
                Date date = new Date();
                LocalDateTime localDateTime = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
                String message =
                        "You have a new Task Assigned \n" + "\n"
                                + "TASK DETAILS--" + "\n"
                                + "FROM:  " + todoDTO.getAssignedBy() + "\n"
                                + "To:  " + todoDTO.getAssignedTo() + "\n"
                                + "TITLE:  " + todoDTO.getTaskName() + "\n"
                                + "CLICK HERE for more info" + "\n" +
                                "\n";
                NotificationDTO notificationDTO = new NotificationDTO();
                notificationDTO.setMessage(message);
                notificationDTO.setRecipient("kabiraneja021@gmail.com");
                notificationDTO.setTimeStamp(localDateTime);

                pushNotification(notificationDTO);
            } finally {
                System.out.println("CONNECTION REFUSED");
            }

        }
            return todoDTO;
    }

    @Override
    public List<TodoDTO> showCreatedToDo(String employeeCode) throws TodoException {
        if( employeeCode==null || todoRepository.findByStringEmployeeCode(employeeCode)==null){
            throw new TodoException("Details not exist");
        }
        else {
            List<Todo> todoList = todoRepository.findByStringEmployeeCode(employeeCode);
            List<TodoDTO> todoDTOList = new ArrayList<>();
            for (Todo t : todoList) {
                todoDTOList.add(modelMapper.map(t, TodoDTO.class));
            }
            return todoDTOList;
        }
    }

    @Override
    public TodoDTO updateCreatedToDo(String id, TodoDTO todoDTO) throws TodoException {
        if(todoRepository.findByStringId(id)==null || todoDTO==null) {
            throw new TodoException("Details not exist");
        }
        else {
            Todo t = todoRepository.findByStringId(id);

            if (!t.getAssignedTo().equals(t.getAssignedBy())) {
                Todo t1 = todoRepository.findRowByAssignedToColumn(t.getTaskName(), t.getAssignedTo());

                t1.setTaskName(todoDTO.getTaskName());
                t1.setDescription(todoDTO.getDescription());
                t1.setActualEndDate(todoDTO.getActualEndDate());
                t1.setActualStartDate(todoDTO.getActualStartDate());
                t1.setEstimatedEndDate(todoDTO.getEstimatedEndDate());
                t1.setEstimatedStartDate(todoDTO.getEstimatedStartDate());
                t1.setStatus(todoDTO.getStatus());
                t1.setPriority(todoDTO.getPriority());
                t1.setAssignedBy(todoDTO.getAssignedBy());
                t1.setAssignedTo(todoDTO.getAssignedTo());
                t1.setEmployeeCode(todoDTO.getAssignedTo());

                todoRepository.save(t1);
            }


            t.setTaskName(todoDTO.getTaskName());
            t.setDescription(todoDTO.getDescription());
            t.setActualEndDate(todoDTO.getActualEndDate());
            t.setActualStartDate(todoDTO.getActualStartDate());
            t.setEstimatedEndDate(todoDTO.getEstimatedEndDate());
            t.setEstimatedStartDate(todoDTO.getEstimatedStartDate());
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
    public void deleteCreatedToDo(String assignedBy, String taskName, String assignedTo) throws TodoException {

        if(assignedBy==null || taskName==null || assignedTo==null) {
            throw new TodoException("Details not exist");
        }
        else if (todoRepository.checkToDoTaskExist(assignedBy,assignedTo,taskName)==null) {
            throw new TodoException("Details not exist");
        }
        else {
//            todoRepository.deleteByStringIdForCreateToDo(assignedBy, taskName);
//            if (!assignedBy.equals(assignedTo)) {
//                todoRepository.deleteByStringIdForAssignedToDoUser(taskName, assignedTo);
//            }
            todoRepository.deleteToDoTask(assignedBy,assignedTo,taskName);
        }
    }

    @Override
    public void deleteToDo(String id1, String id2) throws TodoException{
        if(id1==null || id2==null) {
            throw new TodoException("Details not exist");
        }

        else if(todoRepository.findByStringId(id1)==null || todoRepository.findByStringId(id2)==null) {
            throw new TodoException("Details not exist");
        }

        else {
            todoRepository.deleteByStringId(id1);
            todoRepository.deleteByStringId(id2);
        }

    }

    @Override
    public void pushNotification (NotificationDTO notificationDTO) throws ServiceNotFoundException
    {
        String jsonBody ="{\"key\": \"value\"}";
        webClientBuilder.baseUrl("http://192.168.1.25:8096/send")
                .build().post().uri("/email").bodyValue(notificationDTO).retrieve().toBodilessEntity().block();
    }
}
