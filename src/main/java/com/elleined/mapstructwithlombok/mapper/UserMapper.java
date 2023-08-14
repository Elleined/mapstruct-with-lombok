package com.elleined.mapstructwithlombok.mapper;

import com.elleined.mapstructwithlombok.dto.UserDTO;
import com.elleined.mapstructwithlombok.entity.User;
import com.elleined.mapstructwithlombok.service.UserService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = BookMapper.class)
public abstract class UserMapper {

    @Autowired
    protected UserService userService;

    @Mappings({
            @Mapping(target = "books", source = "user.books"),
            @Mapping(target = "id", source = "user.id"),
            @Mapping(target = "userName", source = "user.name"),
            @Mapping(target = "bookCount", expression = "java(userService.getBookCount(user))")
    })
    public abstract UserDTO toDTO(User user);

    @Mappings({
            @Mapping(target = "id", source = "userDTO.id"),
            @Mapping(target = "name", source = "userDTO.userName"),
            @Mapping(target = "books", expression = "java(new java.util.ArrayList<>())"),
    })
    public abstract User toEntity(UserDTO userDTO);


    @BeforeMapping
    protected void toDTOBeforeMapping(User user, @MappingTarget UserDTO userDTO) {
        // Automatically execute code before toDTO method
    }

    @AfterMapping
    protected void toDTOAfterMapping(User user, @MappingTarget UserDTO userDTO) {
        // Automatically execute code after toDTO method
    }
}
