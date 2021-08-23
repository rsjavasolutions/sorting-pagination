package com.rsjavasolutions.sortingpagination.user.mapper;

import com.rsjavasolutions.sortingpagination.user.UserEntity;
import com.rsjavasolutions.sortingpagination.user.request.UserRequest;
import com.rsjavasolutions.sortingpagination.user.response.UserResponse;

public class UserMapper {

    public static UserEntity mapToEntity(UserRequest request) {
        return new UserEntity(
                request.getName(),
                request.getSurname());
    }

    public static UserResponse mapToResponse(UserEntity entity) {
        return new UserResponse(
                entity.getUuid(),
                entity.getId(),
                entity.getName(),
                entity.getSurname(),
                entity.getCreationDateTime()
        );
    }
}
