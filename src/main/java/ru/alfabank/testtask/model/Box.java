package ru.alfabank.testtask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "boxes")
@Data
@AllArgsConstructor(staticName = "of")
public class Box {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "contained_in")
    private Long containedIn;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "contained_in")
    private Set<Box> boxes;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "contained_in")
    private Set<Item> items;

    public Box() {
        boxes = new HashSet<>();
        items = new HashSet<>();
    }

    public void findItemsByColor(String color, List<Long> list) {
        list.addAll(items.stream()
                .filter(item -> color.equals(item.getColor()))
                .map(item -> item.getId())
                .collect(Collectors.toList()));
        if (!boxes.isEmpty()) {
            boxes.stream().forEach(box -> box.findItemsByColor(color, list));
        }
    }

    public Box(Long id, Long containedIn) {
        this.id = id;
        this.containedIn = containedIn;
        boxes = new HashSet<>();
        items = new HashSet<>();
    }
}
