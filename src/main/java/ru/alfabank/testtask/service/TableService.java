package ru.alfabank.testtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alfabank.testtask.dao.BoxRepository;
import ru.alfabank.testtask.dao.ItemRepository;
import ru.alfabank.testtask.model.Box;
import ru.alfabank.testtask.model.Item;

@Service("TableService")
public class TableService {
    BoxRepository boxRepository;
    ItemRepository itemRepository;

    @Autowired
    public void setBoxRepository(BoxRepository boxRepository) {
        this.boxRepository = boxRepository;
    }
    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void saveBox(Box box) {
        boxRepository.save(box);
    }

    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public Box getBox(Long id){
        return boxRepository.getOne(id);
    }
}
