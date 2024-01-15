package com.bricknet.workflow.controller;

import com.bricknet.workflow.model.Work;
import com.bricknet.workflow.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequestMapping("/stories/graph")
@CrossOrigin("*")
public class GraphQlController {
    @Autowired
    private WorkRepository workRepository;
    @QueryMapping
    public Work workById(@Argument String id) {
        Optional<Work> work= workRepository.findById(id);
        return  work.get();
    }


}
