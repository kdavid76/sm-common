package com.bkk.sm.mongo.authentication.service.impl;

import com.bkk.sm.mongo.authentication.repository.ReactiveUserRepository;
import com.bkk.sm.mongo.authentication.service.ReactiveUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@Service
public class ReactiveUserServiceImpl implements ReactiveUserService {

    private final ReactiveUserRepository repository;

    @Autowired
    ReactiveUserServiceImpl(ReactiveUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
       return repository.findByUsername(username).map(UserDetails.class::cast)
               .onErrorResume(Objects::nonNull, exception -> {
                   if( exception instanceof UsernameNotFoundException) {
                       log.info(exception.getMessage(), exception);
                       return Mono.error(exception);
                   }
                   log.error("Unexpected problem occurred while taking to authentication datastore", exception);
                   return Mono.error(
                           new AuthenticationServiceException(
                                   "Unexpected problem occurred while taking to authentication datastore",
                                   exception
                           )
                   );
               })
               .switchIfEmpty(Mono.defer(() -> Mono.error(new UsernameNotFoundException(
                       String.format("User with username=%s cannot be found.", username)
               ))));
    }
}
