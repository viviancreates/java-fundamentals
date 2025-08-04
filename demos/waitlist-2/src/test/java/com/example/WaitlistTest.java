package com.example;

import com.example.model.Party;
import com.example.repository.InMemoryWaitlist;
import com.example.service.WaitlistService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WaitlistTest {
    @Test
    public void waitlistInitializedAsEmpty() {
        WaitlistService wl = new WaitlistService(new InMemoryWaitlist());

        assertTrue(wl.getList().isEmpty());
    }

    @Test
    public void canAddParty() {
        WaitlistService wl = new WaitlistService(new InMemoryWaitlist());
        Party p = new Party("Wise", 3);
        wl.addParty(p);

        assertEquals(1, wl.getList().size());
        assertEquals(p, wl.getList().get(0));
    }

    @Test
    public void emptyListReturnsNullNextParty() {
        WaitlistService wl = new WaitlistService(new InMemoryWaitlist());

        Party p = wl.callNext();
        assertNull(p);
    }

    @Test
    public void waitListReturnsFirstPartyOnCall() {
        WaitlistService wl = new WaitlistService(new InMemoryWaitlist());

        Party p = new Party("Wise", 3);
        wl.addParty(p);

        Party p2 = new Party("Smith", 2);
        wl.addParty(p2);

        Party next = wl.callNext();

        assertNotNull(next);
        assertEquals("Wise", next.name());
        assertEquals(1, wl.getList().size());
    }

    @Test
    public void badIndexRemovedReturnsNull() {
        WaitlistService wl = new WaitlistService(new InMemoryWaitlist());

        Party p = new Party("Wise", 3);
        wl.addParty(p);

        Party removed = wl.remove(10);
        assertNull(removed);
    }

    @Test
    public void canRemoveByIndex() {
        WaitlistService wl = new WaitlistService(new InMemoryWaitlist());

        Party p = new Party("Wise", 3);
        wl.addParty(p);

        Party p2 = new Party("Smith", 2);
        wl.addParty(p2);

        Party removed = wl.remove(1); // Smith

        assertEquals("Smith", removed.name());
        assertEquals(1, wl.getList().size());
    }
}
