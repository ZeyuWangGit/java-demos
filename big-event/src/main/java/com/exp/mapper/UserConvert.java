package com.exp.mapper;

import com.exp.dto.UserUpdateDTO;
import com.exp.pojo.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * MapStruct interface for converting between UserUpdateDTO and User entity.
 */
@Mapper(componentModel = "spring")
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    /**
     * Converts UserUpdateDTO to User entity.
     */
    User toEntity(UserUpdateDTO dto);

    /**
     * Converts User entity to UserUpdateDTO (optional reverse mapping).
     */
    UserUpdateDTO toDto(User user);
}