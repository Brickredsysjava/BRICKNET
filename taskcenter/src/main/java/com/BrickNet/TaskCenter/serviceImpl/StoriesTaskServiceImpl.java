package com.BrickNet.TaskCenter.serviceImpl;

import com.BrickNet.TaskCenter.dto.AllDTO;
import com.BrickNet.TaskCenter.dto.StoriesDTO;
import com.BrickNet.TaskCenter.dto.TodoDTO;
import com.BrickNet.TaskCenter.exception.TodoException;
import com.BrickNet.TaskCenter.model.Status;
import com.BrickNet.TaskCenter.model.Todo;
//import com.BrickNet.TaskCenter.repository.StoriesTaskRepositry;
import com.BrickNet.TaskCenter.repository.TodoRepository;
import com.BrickNet.TaskCenter.service.StoriesTaskService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StoriesTaskServiceImpl implements StoriesTaskService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StoriesTaskServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<AllDTO> getAll(String employeeCode)throws TodoException {

        List<AllDTO> allDTOList = new ArrayList<>();

        if( employeeCode==null || (todoRepository.findAll()==null) ){
            throw new TodoException("Details not exist");
        }

        List<Todo> todoList = todoRepository.findAll().stream().filter(t->t.getEmployeeAssignedBy().equals(employeeCode) || t.getEmployeeAssignedTo().contains(employeeCode)).collect(Collectors.toList());

        if (todoList==null) {
            throw new TodoException("Details not exist");
        }


        List<TodoDTO> todoDTOList = new ArrayList<>();

            for (Todo t : todoList) {
                todoDTOList.add(modelMapper.map(t, TodoDTO.class));
            }

            for (TodoDTO todoDTO : todoDTOList) {
                AllDTO allDTO = new AllDTO();

                allDTO.setTitle(todoDTO.getTitle());
                allDTO.setStatus(todoDTO.getStatus());
                allDTO.setDescription(todoDTO.getDescription());
                allDTO.setEmployeeAssignedTo(todoDTO.getEmployeeAssignedTo());
                allDTO.setEmployeeAssignedBy(todoDTO.getEmployeeAssignedBy());
                allDTO.setEstimatedEndDate(todoDTO.getEstimatedEndDate());
                allDTO.setEstimatedStartDate(todoDTO.getEstimatedStartDate());

                allDTOList.add(allDTO);
            }

//        String employee_assigned_by = employeeCode;

        String sqlQuery = "SELECT * FROM Task t ";
        List<StoriesDTO> storiesDTOList = new ArrayList<>(); // Initialize the list


        jdbcTemplate.query(sqlQuery, (rs, rowNum) -> {
            StoriesDTO storiesDTO = new StoriesDTO();
            List<String> employeeAssignedTo;

            Blob blob = rs.getBlob("employee_assigned_to");
            byte[] bytes = blob.getBytes(1,(int)blob.length() );
            ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(bytes);

            try {
                ObjectInputStream objectInputStream=new ObjectInputStream(byteArrayInputStream);
                employeeAssignedTo = (List<String>)objectInputStream.readObject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            if(rs.getString("employee_assigned_by").contains(employeeCode) || employeeAssignedTo.contains(employeeCode)) {
                storiesDTO.setTitle(rs.getString("title"));
                storiesDTO.setStatus(Status.valueOf(rs.getString("status")));
                storiesDTO.setDescription(rs.getString("description"));
                storiesDTO.setEstimatedEndDate(LocalDateTime.parse(rs.getString("estimated_end_date")));
                storiesDTO.setEstimatedStartDate(LocalDateTime.parse(rs.getString("estimated_start_date")));
                storiesDTO.setEmployeeAssignedBy(rs.getString("employee_assigned_by"));
                storiesDTO.setEmployeeAssignedTo(employeeAssignedTo);
                storiesDTOList.add(storiesDTO);
            }
                return storiesDTO;
         });



        if(storiesDTOList!=null){
            for (StoriesDTO storiesDTO : storiesDTOList) {

                    AllDTO allDTO = new AllDTO();
                    allDTO.setTitle(storiesDTO.getTitle());
                    allDTO.setStatus(storiesDTO.getStatus());
                    allDTO.setDescription(storiesDTO.getDescription());
                    allDTO.setEmployeeAssignedBy(storiesDTO.getEmployeeAssignedBy());
                    allDTO.setEstimatedEndDate(storiesDTO.getEstimatedEndDate());
                    allDTO.setEstimatedStartDate(storiesDTO.getEstimatedStartDate());
                    allDTO.setEmployeeAssignedTo(storiesDTO.getEmployeeAssignedTo());
                    allDTOList.add(allDTO);

            }
        }

        return allDTOList;
    }
}
