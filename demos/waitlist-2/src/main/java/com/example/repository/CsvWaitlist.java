package com.example.repository;

import com.example.model.Party;

import java.io.*;
import java.util.ArrayList;

public class CsvWaitlist implements WaitlistRepository{
    private ArrayList<Party> parties;
    private final String path;

    public CsvWaitlist(String path) {
        this.parties = new ArrayList<>();
        this.path = path;
        load();
    }

    @Override
    public void load() {
        File file = new File(path);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    Party p = new Party(data[0], Integer.parseInt(data[1]));
                    parties.add(p);
                }
            } catch (IOException e) {
                System.out.println(e);
            }
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
            try (PrintWriter writer = new PrintWriter(path)) {
                for(Party party : parties) {
                    writer.printf("%s,%d%n", party.name(), party.size());
                }
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
        return new ArrayList<>(parties);
    }
}
