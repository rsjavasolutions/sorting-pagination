package com.rsjavasolutions.sortingpagination.user.service;

import com.rsjavasolutions.sortingpagination.user.UserEntity;
import com.rsjavasolutions.sortingpagination.user.UserRepository;
import com.rsjavasolutions.sortingpagination.user.exception.UserNotFoundException;
import com.rsjavasolutions.sortingpagination.user.mapper.UserMapper;
import com.rsjavasolutions.sortingpagination.user.request.UserRequest;
import com.rsjavasolutions.sortingpagination.user.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

import static com.rsjavasolutions.sortingpagination.user.mapper.UserMapper.mapToEntity;
import static com.rsjavasolutions.sortingpagination.user.mapper.UserMapper.mapToResponse;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Set<UserResponse> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::mapToResponse)
                .collect(Collectors.toSet());
    }

    @Transactional
    public UserResponse getUser(String uuid) {
        UserEntity userEntity = userRepository.findByUuid(uuid).orElseThrow(() -> new UserNotFoundException(uuid));
        return mapToResponse(userEntity);
    }

    @Transactional
    public String saveUser(UserRequest request) {
        log.debug("Save user request with params: {}", request);

        return userRepository.save(mapToEntity(request)).getUuid();
    }

    @Transactional
    public UserResponse updateUser(String uuid, UserRequest request) {
        UserEntity userEntity = userRepository.findByUuid(uuid).orElseThrow(() -> new UserNotFoundException(uuid));

        userEntity.setName(request.getName());
        userEntity.setSurname(request.getSurname());

        return mapToResponse(userEntity);
    }

    @Transactional
    public void deleteUser(String uuid) {
        userRepository.deleteByUuid(uuid);
    }
}
