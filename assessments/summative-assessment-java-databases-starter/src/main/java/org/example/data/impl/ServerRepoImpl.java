package org.example.data.impl;

import org.example.data.ServerRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.model.Server;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Repository
public class ServerRepoImpl implements ServerRepo {

    @Override
    public Server getServerById(int id) throws InternalErrorException, RecordNotFoundException {
        throw new RecordNotFoundException();
    }

    @Override
    public List<Server> getAllAvailableServers(LocalDate date) throws InternalErrorException {
        return Collections.emptyList();
    }
}
