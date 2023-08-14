package com.elleined.mapstructwithlombok.mapper;

import com.elleined.mapstructwithlombok.dto.BookDTO;
import com.elleined.mapstructwithlombok.entity.Book;
import com.elleined.mapstructwithlombok.entity.User;
import org.mapstruct.*;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = LocalDateTime.class)
public abstract class BookMapper {
    @Mappings({
            @Mapping(target = "id", source = "book.id"),
            @Mapping(target = "bookName", source = "book.name"),
            @Mapping(target = "dateCreated", source = "book.dateCreated"),
            @Mapping(target = "authorId", expression = "java(book.getAuthor().getId())")
    })
    public abstract BookDTO toDTO(Book book);

    @Mappings({
            @Mapping(target = "id", source = "bookDTO.id"),
            @Mapping(target = "name", source = "bookDTO.bookName"),
            @Mapping(target = "dateCreated", expression = "java(LocalDateTime.now())"),
            @Mapping(target = "author", expression = "java(author)")
    })
    public abstract Book toEntity(BookDTO bookDTO, @Context User author);
}
