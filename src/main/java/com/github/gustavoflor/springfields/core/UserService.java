package com.github.gustavoflor.springfields.core;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public User findById(final Long id) {
        return new User(id, "Homer Simpson", "000.000.000-00", "homer@mail.com");
    }

    public List<User> findAll() {
        return List.of(
            new User(1L, "Marge Simpson", "111.111.111-11", "marge@mail.com"),
            new User(2L, "Bart Simpson", "222.222.222-22", "bart@mail.com"),
            new User(3L, "Lisa Simpson", "333.333.333-33", "lisa@mail.com"),
            new User(4L, "Maggie Simpson", "444.444.444-44", "maggie@mail.com")
        );
    }

}
