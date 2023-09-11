package com.BrickNet.TaskCenter.service;

import com.BrickNet.TaskCenter.dto.AllDTO;
import com.BrickNet.TaskCenter.dto.StoriesDTO;
import com.BrickNet.TaskCenter.exception.TodoException;

import java.util.List;

public interface AllService {


    List<AllDTO> getAll(List<StoriesDTO> storiesDTO,String employeeCode)throws TodoException;

}
