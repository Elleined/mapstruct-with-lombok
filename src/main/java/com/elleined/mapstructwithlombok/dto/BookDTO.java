package com.elleined.mapstructwithlombok.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BookDTO {
    private int id;
    private String bookName;
    private LocalDateTime dateCreated;
    private int authorId;
}
