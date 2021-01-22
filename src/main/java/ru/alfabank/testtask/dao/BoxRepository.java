package ru.alfabank.testtask.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alfabank.testtask.model.Box;

@Repository
public interface BoxRepository  extends JpaRepository<Box, Long> {

}
