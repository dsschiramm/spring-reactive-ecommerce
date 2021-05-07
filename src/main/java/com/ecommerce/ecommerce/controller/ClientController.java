package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.Client;
import com.ecommerce.ecommerce.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.Optional;

@RestController()
@RequestMapping(value = "/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientRepository clientRepository;

    @GetMapping("")
    public Flux<Client> getAll(@RequestParam(value = "name", required = false) String name) {
        return this.clientRepository.findAll()
                .filter(client -> Optional.ofNullable(name)
                        .map(parameter -> client.getName().contains(parameter)).orElse(true)
                )
                .sort(Comparator.comparing(Client::getDateCreated).reversed());
    }

    @GetMapping("/{id}")
    public Mono<Client> get(@PathVariable("id") Long id) {
        return this.clientRepository.findById(id);
    }

    @PostMapping("")
    public Mono<Client> create(@RequestBody Client client) {
        return this.clientRepository.save(client);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Client> update(@PathVariable("id") Long id, @RequestBody Client client) {
        return this.clientRepository.findById(id)
                .map(c -> {

                    c.setName(client.getName());
                    c.setEmail(client.getEmail());
                    c.setPassword(client.getPassword());

                    return c;
                }).flatMap(this.clientRepository::save);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") Long id) {
        return this.clientRepository.deleteById(id);
    }
}
