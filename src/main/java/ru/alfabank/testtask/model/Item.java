package ru.alfabank.testtask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "items")
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Item {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "contained_in")
    private Long containedIn;
    @Column(name = "color")
    private String color;
}
