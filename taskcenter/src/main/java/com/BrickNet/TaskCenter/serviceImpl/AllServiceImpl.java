package com.BrickNet.TaskCenter.serviceImpl;

import com.BrickNet.TaskCenter.dto.AllDTO;
import com.BrickNet.TaskCenter.dto.StoriesDTO;
import com.BrickNet.TaskCenter.dto.TodoDTO;
import com.BrickNet.TaskCenter.exception.TodoException;
import com.BrickNet.TaskCenter.model.Todo;
import com.BrickNet.TaskCenter.repository.TodoRepository;
import com.BrickNet.TaskCenter.service.AllService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AllServiceImpl implements AllService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AllDTO> getAll(List<StoriesDTO> storiesDTOList,String employeeCode)throws TodoException {

        List<AllDTO> allDTOList = new ArrayList<>();

        if(employeeCode!=null && todoRepository.findByStringEmployeeCode(employeeCode)!=null){
            List<Todo> todoList = todoRepository.findByStringEmployeeCode(employeeCode);
            List<TodoDTO> todoDTOList = new ArrayList<>();
            for (Todo t : todoList) {
                todoDTOList.add(modelMapper.map(t, TodoDTO.class));
            }

            for (TodoDTO todoDTO : todoDTOList) {
                AllDTO allDTO = new AllDTO();
                allDTO.setTaskName(todoDTO.getTaskName());
                allDTO.setStatus(todoDTO.getStatus());
                allDTO.setDescription(todoDTO.getDescription());
                allDTO.setAssignedTo(todoDTO.getAssignedTo());
                allDTO.setAssignedBy(todoDTO.getAssignedBy());
                allDTO.setEstimatedEndDate(todoDTO.getEstimatedEndDate());
                allDTO.setEstimatedStartDate(todoDTO.getEstimatedStartDate());
                allDTOList.add(allDTO);
            }
        }

        if(storiesDTOList!=null){
            for (StoriesDTO storiesDTO : storiesDTOList) {

                for (String str : storiesDTO.getUserAssigned()) {
                    AllDTO allDTO = new AllDTO();
                    allDTO.setTaskName(storiesDTO.getTitle());
                    allDTO.setStatus(storiesDTO.getStatus());
                    allDTO.setDescription(storiesDTO.getDescription());
                    allDTO.setAssignedBy(storiesDTO.getCreatorId());
                    allDTO.setEstimatedEndDate(storiesDTO.getEstimatedEndDate());
                    allDTO.setEstimatedStartDate(storiesDTO.getEstimatedStartDate());
                    allDTO.setAssignedTo(str);
                    allDTOList.add(allDTO);
                }

            }
        }
        return allDTOList;
    }
}
