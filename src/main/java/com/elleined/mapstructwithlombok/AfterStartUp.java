package com.elleined.mapstructwithlombok;

import com.elleined.mapstructwithlombok.dto.UserDTO;
import com.elleined.mapstructwithlombok.entity.Book;
import com.elleined.mapstructwithlombok.entity.User;
import com.elleined.mapstructwithlombok.mapper.UserMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class AfterStartUp {

    private final UserMapper userMapper;

    @PostConstruct
    void init() {
        User jkRowling = User.builder()
                .id(1)
                .name("jkRowling")
                .books(new ArrayList<>())
                .build();

        Book harryPotterChamberOfSecrets = Book.builder()
                .id(1)
                .name("harryPotterChamberOfSecrets")
                .dateCreated(LocalDateTime.now())
                .author(jkRowling)
                .build();

        Book harryPotterGobletOfFire = Book.builder()
                .id(2)
                .name("harryPotterGobletOfFire")
                .dateCreated(LocalDateTime.now())
                .author(jkRowling)
                .build();

        jkRowling.getBooks().add(harryPotterChamberOfSecrets);
        jkRowling.getBooks().add(harryPotterGobletOfFire);

        UserDTO userDTO = userMapper.toDTO(jkRowling);
        System.out.println(userDTO);
    }
}
