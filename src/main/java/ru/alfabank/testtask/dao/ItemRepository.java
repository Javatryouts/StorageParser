package ru.alfabank.testtask.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alfabank.testtask.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
