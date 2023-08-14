package com.elleined.mapstructwithlombok.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDTO {
    private int id;
    private String userName;
    private List<BookDTO> books;
    private int bookCount;
}
