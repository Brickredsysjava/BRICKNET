package com.BrickNet.TaskCenter.service;

import com.BrickNet.TaskCenter.dto.AllDTO;
import com.BrickNet.TaskCenter.exception.TodoException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StoriesTaskService {
    List<AllDTO> getAll(String employeeCode)throws TodoException;

}
