package org.example;

import org.example.model.Party;
import org.example.service.WaitlistService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WaitlistTest {

    @Test
    //we know we can create a waitlist object
    public void WaitlistInitializedAsEmpty() {
        WaitlistService wl = new WaitlistService();

        assertTrue(wl.getList().isEmpty());
    }

    @Test
    public void canAddParty() {
        //create the waitlist service
        WaitlistService wl = new WaitlistService();
        //create a party
        Party p = new Party("Wise", 3);
        wl.addParty(p);

        assertEquals(1, wl.getList().size());
        assertEquals(p, wl.getList().get(0));

    }

}
