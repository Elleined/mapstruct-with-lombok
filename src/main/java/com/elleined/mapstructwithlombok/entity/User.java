package com.elleined.mapstructwithlombok.entity;

import lombok.*;

import java.util.List;

@Data
@Builder
public class User {
    private int id;
    private String name;

    private List<Book> books;
}
