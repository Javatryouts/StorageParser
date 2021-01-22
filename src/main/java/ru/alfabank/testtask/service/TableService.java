package ru.alfabank.testtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alfabank.testtask.dao.BoxRepository;
import ru.alfabank.testtask.dao.ItemRepository;
import ru.alfabank.testtask.model.Box;
import ru.alfabank.testtask.model.Item;

@Service("TableService")
public class TableService {
    @Autowired
    BoxRepository boxRepository;
    @Autowired
    ItemRepository itemRepository;

    public void saveBox(Box box) {
        boxRepository.save(box);
    }

    public void saveItem(Item item) {
        itemRepository.save(item);
    }
}
