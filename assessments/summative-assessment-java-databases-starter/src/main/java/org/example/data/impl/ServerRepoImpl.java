package org.example.data.impl;

import org.example.data.ServerRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.model.Server;
import org.example.data.mappers.ServerMapper;
import org.springframework.stereotype.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ServerRepoImpl implements ServerRepo {
    @Autowired
    private JdbcTemplate jdbc;
    private ServerMapper serverMapper;

    public ServerRepoImpl(JdbcTemplate jdbc, ServerMapper serverMapper) {
        this.jdbc = jdbc;
        this.serverMapper = serverMapper;
    }
    @Override
    public Server getServerById(int id) throws InternalErrorException, RecordNotFoundException {
        String sql = "SELECT * FROM Server WHERE ServerID = ?";
        try {
            return jdbc.queryForObject(sql, serverMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new RecordNotFoundException();
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<Server> getAllAvailableServers(LocalDate date) throws InternalErrorException {
        String sql = """
            SELECT * FROM Server
            WHERE HireDate <= ?
              AND (TermDate IS NULL OR TermDate >= ?)
        """;
        try {
            return jdbc.query(sql, serverMapper, date, date);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }
}
