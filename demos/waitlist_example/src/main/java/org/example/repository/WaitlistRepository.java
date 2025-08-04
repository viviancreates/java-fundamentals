package org.example.repository;

import org.example.model.Party;
import java.util.List;

public interface WaitlistRepository {
    ArrayList<Party> load();
    void save();
    void add(Party party);
    Party remove(int index);
    Party callNext();
    ArrayList<Party> getAll();

}
