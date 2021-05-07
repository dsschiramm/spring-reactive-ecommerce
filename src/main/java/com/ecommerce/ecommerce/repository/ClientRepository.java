package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.model.Client;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ClientRepository extends ReactiveCrudRepository<Client, Long> {

    @Query("SELECT * FROM posts WHERE title like $1")
    Flux<Client> findByNameContains(String name);
}