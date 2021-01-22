package ru.alfabank.testtask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.alfabank.testtask.dao.BoxRepository;
import ru.alfabank.testtask.model.Box;
import ru.alfabank.testtask.request.Request;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    BoxRepository boxRepository;

    @PostMapping("/")
    public List<Long> getBoxes(@RequestBody Request request) {
        Box box = boxRepository.getOne(request.getBox());
        List<Long> result = new ArrayList<>();
        box.findItemsByColor(request.getColor(), result);
        return result;
    }
}
