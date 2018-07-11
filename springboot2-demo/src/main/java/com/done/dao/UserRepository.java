package com.done.dao;

import com.done.model.User;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Done Lin on 2018/3/25.
 */
@Repository
public class UserRepository {
    private  List<User> users = Arrays.asList(new User("1", "User1"), new User("2", "User2"));

    public Mono<User> getUserById(String id) {
        return Mono.justOrEmpty(users.stream().filter(user -> {
            return user.getId().equals(id);
        }).findFirst().orElse(null));
    }

    public Flux<User> getUsers() {
        return Flux.fromIterable(users);
    }


}
