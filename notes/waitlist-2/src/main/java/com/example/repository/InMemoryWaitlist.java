package com.example.repository;

import com.example.model.Party;
import java.util.ArrayList;

public class InMemoryWaitlist implements WaitlistRepository {
    private ArrayList<Party> parties;

    public InMemoryWaitlist() {
        parties = new ArrayList<>();
    }

    //when we load, this is where we can do some sample data
    @Override
    public void load() {
//        parties.add(new Party("Wise", 3));
//        parties.add(new Party("Smith", 5));
    }

    //if your save is in memory, you are not saving anything to the system
    // will just return, not going to save anything -> bc we want the data to go away
    @Override
    public void save() {
        return;
    }

    @Override
    public void add(Party party) {
        parties.add(party);
    }

    @Override
    public Party remove(int index) {
        if (index >= 0 && index < parties.size()) {
            return parties.remove(index);
        } else {
            return null;
        }
    }

    @Override
    public Party callNext() {
        if (parties.isEmpty()) {
            return null;
        } else {
            return parties.remove(0);
        }
    }

    @Override
    public ArrayList<Party> getAll() {
        return new ArrayList<>(parties);
    }
}
