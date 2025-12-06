package com.pifirm.web.controller;

import com.pifirm.domain.dto.user.UserCreationDto;
import com.pifirm.domain.dto.user.UserDto;
import com.pifirm.domain.dto.user.UserUpdateDto;
import com.pifirm.domain.service.UserService;
import com.pifirm.persistence.entity.UserEntity;
import com.pifirm.persistence.mapper.UserMapper;
import com.pifirm.web.response.GenericResponse;
import com.pifirm.web.response.PageResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity currentUser = (UserEntity) authentication.getPrincipal();
        return ResponseEntity.ok(this.userMapper.toDto(currentUser));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public PageResponse<UserDto> allUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<UserDto> pageResult = userService.allUsers(pageable);
        return PageResponse.fromPage(pageResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@RequestBody UserUpdateDto userUpdateDto, @PathVariable Long id) {
        UserDto userDto = this.userService.update(userUpdateDto, id);
        return ResponseEntity.ok(userDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        UserDto userDto = this.userService.getById(id);
        return ResponseEntity.ok(userDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse> delete(@PathVariable Long id) {
        this.userService.delete(id);
        return ResponseEntity.ok().body(new GenericResponse(
                HttpStatus.OK.value(),
                "Eliminado correctamente"
        ));
    }

    @PostMapping
    public ResponseEntity<UserDto> signup(@RequestBody @Valid UserCreationDto userCreationDto) {
        return ResponseEntity.ok(this.userService.add(userCreationDto));
    }
}
