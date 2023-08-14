package com.elleined.mapstructwithlombok.entity;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Builder
public class Book {
    private int id;
    private String name;
    private LocalDateTime dateCreated;
    private User author;
}
