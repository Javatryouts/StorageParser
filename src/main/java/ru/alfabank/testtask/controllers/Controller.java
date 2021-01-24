package ru.alfabank.testtask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.alfabank.testtask.model.Box;
import ru.alfabank.testtask.request.Request;
import ru.alfabank.testtask.service.TableService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    TableService tableService;

    @Autowired
    public void setTableService(TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping("/")
    public List<Long> getItems(@RequestBody Request request) {
        Box box = tableService.getBox(request.getBoxId());
        List<Long> result = new ArrayList<>();
        box.findItemsByColor(request.getColor(), result);
        return result;
    }
    @ExceptionHandler
    public String handleException(Exception ex){
        return "Error! " + ex.getMessage();
    }
}
