package ru.alvisid.votingsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alvisid.votingsystem.repository.MenusRepository;

@Service
public class MenusServiseImpl {

    private final MenusRepository repository;

    @Autowired
    public MenusServiseImpl(MenusRepository repository) {
        this.repository = repository;
    }
}
