package org.example.service;

import org.example.model.Party;

import java.util.ArrayList;

public class WaitlistService {
    private ArrayList<org.example.model.Party> parties;

    //create a new instance of the waitlist
    public WaitlistService() {
        parties = new ArrayList<>();
    }

    //set the parties equal to a new arraylist
    public ArrayList<org.example.model.Party> getList() {
        //we do not want them to have the pointer
        //so we give them a copy of the list
        //do not want pointer, want a new list
        return new ArrayList<>(parties);
        //new keyword is what creates the new memory pointer, pass in original
        // say new array list and give it the original, it will make a copy of all the data in it
    }


    public void addParty(Party party) {
        parties.add(party);
    }

    punlic Party callNext(Party party) {
        if (parties.isEmpty()) {
            return null;
        } else {
            return parties.remove(0);
        }
//PAUSE AT 17:37
    }
}
