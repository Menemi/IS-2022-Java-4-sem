package ru.itmo.kotiki;

import org.hibernate.Session;
import org.junit.*;
import ru.itmo.kotiki.model.Cat;
import ru.itmo.kotiki.model.Color;
import ru.itmo.kotiki.model.Owner;
import ru.itmo.kotiki.model.Breed;
import ru.itmo.kotiki.service.CatServiceImpl;
import ru.itmo.kotiki.service.OwnerServiceImpl;

import java.sql.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class KotikiTest {
    private Session session;
    private OwnerServiceImpl ownerService;
    private CatServiceImpl catService;

    @Before
    public void setup() {
        session = mock(Session.class);
        ownerService = mock(OwnerServiceImpl.class);
        catService = mock(CatServiceImpl.class);
    }

    @Test
    public void findTest() {
        Owner denchik = new Owner("Gooolden Denchik", Date.valueOf("2002-10-12"));
        Cat kotyara = new Cat("Kotyara", Date.valueOf("2003-11-13"), Breed.RAGDOLL, Color.BLACK);
        denchik.addPet(kotyara);

        when(ownerService.findOwner(1)).thenReturn(Optional.of(denchik));
        when(catService.findCat(1)).thenReturn(Optional.of(kotyara));
        assertEquals(Optional.of(denchik), ownerService.findOwner(1));
        assertEquals(Optional.of(kotyara), catService.findCat(1));
    }
}
