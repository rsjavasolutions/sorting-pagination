package com.rsjavasolutions.sortingpagination.user;

import com.rsjavasolutions.sortingpagination.user.request.UserRequest;
import com.rsjavasolutions.sortingpagination.user.response.UserResponse;
import com.rsjavasolutions.sortingpagination.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUser(@PathVariable String uuid) {
        return userService.getUser(uuid);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String saveUser(@RequestBody UserRequest request) {
        return userService.saveUser(request);
    }

    @DeleteMapping("{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String uuid) {
        userService.deleteUser(uuid);
    }

    @PutMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUser(@PathVariable String uuid, @RequestBody UserRequest request) {
        return userService.updateUser(uuid, request);
    }
}
