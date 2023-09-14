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
    public TodoDTO addToDo(TodoDTO todoDTO) throws TodoException {

        if (todoDTO==null) {
            throw new TodoException("Details not exist");
        }

        else {
            Todo todo1 = modelMapper.map(todoDTO, Todo.class);
            todoRepository.save(todo1);
            todoDTO.setId(todo1.getId());

            try {
                Date date = new Date();
                LocalDateTime localDateTime = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
                String message =
                        "You have a new Task Assigned \n" + "\n"
                                + "TASK DETAILS--" + "\n"
                                + "FROM:  " + todoDTO.getAssignedBy() + "\n"
                                + "To:  " + todoDTO.getAssignedTo() + "\n"
                                + "TITLE:  " + todoDTO.getTitle() + "\n"
                                + "CLICK HERE for more info" + "\n" +
                                "\n";
                NotificationDTO notificationDTO = new NotificationDTO();
                notificationDTO.setMessage(message);
                notificationDTO.setRecipient("kabiraneja021@gmail.com");
                notificationDTO.setTimeStamp(localDateTime);

                pushNotification(notificationDTO);
            } catch(Exception e) {
                e.printStackTrace();
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
            List<Todo> todoList = todoRepository.findByStringAssignedBy(employeeCode);
            List<Todo> todoList1 = todoRepository.findByStringAssignedTo(employeeCode);

            List<TodoDTO> todoDTOList = new ArrayList<>();

            for (Todo t : todoList) {
                todoDTOList.add(modelMapper.map(t, TodoDTO.class));
            }
            for (Todo t : todoList1) {
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

                t.setTitle(todoDTO.getTitle());
                t.setDescription(todoDTO.getDescription());
                t.setActualEndDate(todoDTO.getActualEndDate());
                t.setActualStartDate(todoDTO.getActualStartDate());
                t.setEstimatedEndDate(todoDTO.getEstimatedEndDate());
                t.setEstimatedStartDate(todoDTO.getEstimatedStartDate());
                t.setStatus(todoDTO.getStatus());
                t.setPriority(todoDTO.getPriority());
                t.setAssignedBy(todoDTO.getAssignedBy());
                t.setAssignedTo(todoDTO.getAssignedTo());
                t.setEmployeeCode(todoDTO.getAssignedTo());

                todoRepository.save(t);

            return modelMapper.map(t, TodoDTO.class);
        }
    }

    @Override
    public void deleteToDo(String id1) throws TodoException{
        if(id1==null) {
            throw new TodoException("Details not exist");
        }

        else if(todoRepository.findByStringId(id1)==null) {
            throw new TodoException("Details not exist");
        }

        else {
            todoRepository.deleteByStringId(id1);
        }

    }

    @Override
    public void pushNotification (NotificationDTO notificationDTO) throws ServiceNotFoundException
    {
        String jsonBody ="{\"key\": \"value\"}";
        webClientBuilder.baseUrl("http://20.198.3.41:8080/send")
                .build().post().uri("/email").bodyValue(notificationDTO).retrieve().toBodilessEntity().block();
    }
}
