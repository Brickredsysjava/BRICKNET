package com.BrickNet.TaskCenter.serviceImpl;

import com.BrickNet.TaskCenter.dto.PostTodoDTO;
import com.BrickNet.TaskCenter.dto.TodoDTO;
import com.BrickNet.TaskCenter.dto.NotificationDTO;
import com.BrickNet.TaskCenter.dto.UpdateTodoDTO;
import com.BrickNet.TaskCenter.exception.TodoException;
import com.BrickNet.TaskCenter.model.Priority;
import com.BrickNet.TaskCenter.model.Status;
import com.BrickNet.TaskCenter.model.Todo;
import com.BrickNet.TaskCenter.repository.TodoRepository;
import com.BrickNet.TaskCenter.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.web.reactive.function.client.WebClient;
import javax.management.ServiceNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public PostTodoDTO addToDo(PostTodoDTO postTodoDTO) throws TodoException {

        if (postTodoDTO==null) {
            throw new TodoException("Details not exist");
        }

        else {
            Todo todo = Todo.builder()
                    .title(postTodoDTO.getTitle())
                    .description(postTodoDTO.getDescription())
                    .estimatedStartDate(postTodoDTO.getEstimatedStartDate())
                    .estimatedEndDate(postTodoDTO.getEstimatedEndDate())
                    .priority(postTodoDTO.getPriority())
                    .employeeAssignedBy(postTodoDTO.getEmployeeAssignedBy())
                    .employeeAssignedTo(postTodoDTO.getEmployeeAssignedTo())
                    .status(Status.Initial)
                    .build();

            todoRepository.save(todo);
            postTodoDTO.setId(todo.getId());
            sendNotification(postTodoDTO);
        }
            return postTodoDTO;
    }

    public void sendNotification(PostTodoDTO postTodoDTO) {
        try {
            Date date = new Date();
            LocalDateTime localDateTime = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
            String message =
                    "You have a new Task Assigned \n" + "\n"
                            + "TASK DETAILS--" + "\n"
                            + "FROM:  " + postTodoDTO.getEmployeeAssignedBy() + "\n"
                            + "To:  " + postTodoDTO.getEmployeeAssignedTo() + "\n"
                            + "TITLE:  " + postTodoDTO.getTitle() + "\n"
                            + "CLICK HERE for more info" + "\n" +
                            "\n";
            NotificationDTO notificationDTO = new NotificationDTO();
            notificationDTO.setMessage(message);
            notificationDTO.setTimeStamp(localDateTime);
            if(postTodoDTO.getEmployeeAssignedTo() != null){
                for ( String i : postTodoDTO.getEmployeeAssignedTo()){
                    notificationDTO.setRecipient(getEmployeeEmailByEmployeeCode(i));
                    pushNotification(notificationDTO);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("CONNECTION REFUSED");
        }
    }

    @Override
    public String getEmployeeEmailByEmployeeCode(String employeeCode) {
        return webClientBuilder.baseUrl("http://192.168.1.9:8081")
                .build().get().uri("/user/profile/getEmailByEmployeeCode?employeeCode=" + employeeCode).retrieve().bodyToMono(String.class).block();
    }

    @Override
    public String setStatus(String id, Status status)throws TodoException {

        try{
            Todo todo = todoRepository.findByStringId(id);
            todo.setStatus(status);
            if(Status.InProgress.equals(status)) {
                todo.setActualStartDate(LocalDateTime.now());
            } else if (Status.Completed.equals(status)) {
                todo.setActualEndDate(LocalDateTime.now());
            }
            todoRepository.save(todo);
            return "Status updated";

        }
        catch (Exception e) {
            throw new TodoException("Details not exist");
        }
    }

    @Override
    public String setPriority(String id, String employeeCode, Priority priority)throws TodoException {

        try{
            Todo todo = todoRepository.findByStringId(id);
            if(todo.getEmployeeAssignedBy().equals(employeeCode)) {
                todo.setPriority(priority);
                todoRepository.save(todo);
                return "Priority Updated";
            }
            else{
                return "You can't update priority";
            }
        }
        catch (Exception e) {
            throw new TodoException("Details not exist");
        }
    }

    @Override
    public List<TodoDTO> showCreatedToDo(String employeeCode) throws TodoException {

        if(employeeCode==null || (todoRepository.findAll()==null) ){
            throw new TodoException("Details not exist");
        }

        List<Todo> todoList = todoRepository.findAll().stream().filter(t->t.getEmployeeAssignedBy().equals(employeeCode) || t.getEmployeeAssignedTo().contains(employeeCode)).collect(Collectors.toList());

        if (todoList==null) {
            throw new TodoException("Details not exist");
        }

        else {

            List<TodoDTO> todoDTOList = new ArrayList<>();

            for (Todo t : todoList) {
                todoDTOList.add(modelMapper.map(t, TodoDTO.class));
            }

            return todoDTOList;
        }
    }

    @Override
    public TodoDTO updateCreatedToDo(String id, String employeeCode , UpdateTodoDTO updateTodoDTO) throws TodoException {
        try {
            Todo t = todoRepository.findByStringId(id);

            if (updateTodoDTO != null && t.getEmployeeAssignedBy().equals(employeeCode)) {
                t.setTitle(updateTodoDTO.getTitle());
                t.setDescription(updateTodoDTO.getDescription());
                t.setEstimatedEndDate(updateTodoDTO.getEstimatedEndDate());
                t.setEstimatedStartDate(updateTodoDTO.getEstimatedStartDate());
                t.setStatus(updateTodoDTO.getStatus());
                t.setPriority(updateTodoDTO.getPriority());
                t.setEmployeeAssignedTo(updateTodoDTO.getEmployeeAssignedTo());

                todoRepository.save(t);

                return modelMapper.map(t, TodoDTO.class);
            }
        } catch (Exception e) {
            throw new TodoException("Details not exist");
        }
        return null;
    }

    @Override
    public void deleteToDo(String id1,String employeeCode) throws TodoException{
        if(id1==null) {
            throw new TodoException("Details not exist");
        }

        else if(todoRepository.findByStringId(id1)==null) {
            throw new TodoException("Details not exist");
        }

        else {
            Todo todo = todoRepository.findByStringId(id1);
            if(todo.getEmployeeAssignedBy().equals(employeeCode)) {
                todoRepository.deleteByStringId(id1);
            }
            else throw new TodoException("Task not deleted");
        }

    }

    @Override
    public void pushNotification (NotificationDTO notificationDTO) throws ServiceNotFoundException
    {
        String jsonBody ="{\"key\": \"value\"}";
        webClientBuilder.baseUrl("http://192.168.1.9:8084/send")
                .build().post().uri("/email").bodyValue(notificationDTO).retrieve().toBodilessEntity().block();
    }


}
