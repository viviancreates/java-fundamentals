package com.example.repository;

import com.example.model.Party;

import java.util.ArrayList;

public interface WaitlistRepository {
    //need to be able to get a list of parties -> the provider of the list can be anything other than the array list
    void load();
    void save();
    void add(Party party);
    Party remove(int index);
    //return a party
    Party callNext();
    ArrayList<Party> getAll();
}

/* CRUD
 * Create (add new)
 * Retrieve (GetAll(), GetByX()
 * Update (replace)
 * Delete (delete)
 *
 */
