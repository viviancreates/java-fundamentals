package com.example;

import com.example.model.Party;
import com.example.repository.JsonWaitlist;
import com.example.service.WaitlistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWaitlistTest {
    @BeforeEach
    public void tearDown() {
        File file = new File("data/waitlist.json");
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void waitlistInitializedAsEmpty() {
        WaitlistService wl = new WaitlistService(new JsonWaitlist("data/waitlist.json"));

        assertTrue(wl.getList().isEmpty());
    }

    @Test
    public void canAddParty() {
        WaitlistService wl = new WaitlistService(new JsonWaitlist("data/waitlist.json"));
        Party p = new Party("Wise", 3);
        wl.addParty(p);

        assertEquals(1, wl.getList().size());
        assertEquals(p, wl.getList().get(0));
    }

    @Test
    public void canReloadData() {
        WaitlistService wl = new WaitlistService(new JsonWaitlist("data/waitlist.json"));
        Party p = new Party("Wise", 3);
        wl.addParty(p);

        WaitlistService wl2 = new WaitlistService(new JsonWaitlist("data/waitlist.json"));
        assertEquals(1, wl2.getList().size());
        assertEquals(p, wl2.getList().get(0));
    }

}
