package com.bkk.sm.mongo.authentication.repository;

import com.bkk.sm.mongo.authentication.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ReactiveUserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findByUsername(String username);

}
