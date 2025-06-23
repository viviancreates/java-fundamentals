package com.example.repository;

import com.example.model.Party;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonWaitlist implements WaitlistRepository {
    private ArrayList<Party> parties;
    private final String path;
    private ObjectMapper mapper;

    public JsonWaitlist(String path) {
        this.path = path;
        parties = new ArrayList<>();
        mapper = new ObjectMapper();
        load();
    }

    @Override
    public void load() {
        try {
            File file = new File(path);

            if (file.exists()) {
                parties = mapper.readValue(new File(path), new TypeReference<ArrayList<Party>>() { });
            }
        } catch(IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void save() {
        File file = new File(path);

        // no parties, existing file, delete the file
        if (file.exists() && parties.isEmpty()) {
            file.delete();
            return;
        }

        // we have parties... replace the file
        if (!parties.isEmpty()) {
            try {
                mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), parties);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public void add(Party party) {
        parties.add(party);
        save();
    }

    @Override
    public Party remove(int index) {
        Party p;

        if (index >= 0 && index < parties.size()) {
            p = parties.remove(index);
        } else {
            p = null;
        }

        save();
        return p;
    }

    @Override
    public Party callNext() {
        if (parties.isEmpty()) {
            return null;
        } else {
            Party p = parties.remove(0);
            save();
            return p;
        }
    }

    @Override
    public ArrayList<Party> getAll() {
        return new ArrayList<Party>(parties);
    }
}
