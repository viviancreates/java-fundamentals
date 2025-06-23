package com.example.service;

import com.example.model.Party;
import com.example.repository.WaitlistRepository;
import java.util.ArrayList;

// in-memory
public class WaitlistService {
    WaitlistRepository repository;

    public WaitlistService(WaitlistRepository repository) {
        this.repository = repository;
    }

    public ArrayList<Party> getList() {
        return this.repository.getAll();
    }

    public void addParty(Party party) {
        this.repository.add(party);
    }

    public Party callNext() {
        return this.repository.callNext();
    }

    public Party remove(int index) {
        return this.repository.remove(index);
    }
}
