package org.example.repository;

import org.example.model.Party;
import java.util.ArrayList;

public class InMemoryWaitlist implements WaitlistRepository{
    private ArrayList<Party> parties;

    public InMemoryWaitlist() {
        parties = new ArrayList<> ();
    }

    @Override
    public ArrayList<Party> load() {
        return null;
    }

    @Override
    
    @Override

    @Override

    @Override
    public Party callNext() {

    }

    @Override
    public ArrayList<Party> getAll() {
        return new ArrayList<>(parties);
    }
}
