package ru.itmo.kotiki;

import org.hibernate.Session;
import org.junit.*;
import ru.itmo.kotiki.model.Cat;
import ru.itmo.kotiki.model.Color;
import ru.itmo.kotiki.model.Owner;
import ru.itmo.kotiki.model.Breed;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

public class KotikiTest {
//    private Session session;
//    private OwnerService ownerService;
//    private CatService catService;
//
//    @Before
//    public void setup() {
//        session = mock(Session.class);
//        ownerService = mock(OwnerService.class);
//        catService = mock(CatService.class);
//    }
//
//    @Test
//    public void findTest() {
//        Owner denchik = new Owner("Gooolden Denchik", Date.valueOf("2002-10-12"));
//        Cat kotyara = new Cat("Kotyara", Date.valueOf("2003-11-13"), Breed.RAGDOLL, Color.BLACK);
//        denchik.addPet(kotyara);
//
//        when(ownerService.findOwner(1)).thenReturn(denchik);
//        when(catService.findCat(1)).thenReturn(kotyara);
//        assertEquals("Gooolden Denchik", ownerService.findOwner(1).getName());
//        assertEquals("Kotyara", catService.findCat(1).getName());
//    }
//
//    @Test
//    public void getTest() {
//        Owner denchik = new Owner("Gooolden Denchik", Date.valueOf("2002-10-12"));
//        Cat kisik = new Cat("Kisik", Date.valueOf("2003-11-13"), Breed.SIBERIAN, Color.RED);
//        denchik.addPet(kisik);
//
//        when(ownerService.findCat(1)).thenReturn(kisik);
//        assertEquals("Gooolden Denchik", ownerService.findCat(1).getOwner().getName());
//    }
//
//    @Test
//    public void deleteTest() {
//        Owner denchik = new Owner("Gooolden Denchik", Date.valueOf("2002-10-12"));
//        Cat kisik = new Cat("Kisik", Date.valueOf("2003-11-13"), Breed.SPHINX, Color.RED);
//        Cat kotyara = new Cat("Kotyara", Date.valueOf("2003-11-13"), Breed.SIBERIAN, Color.WHITE);
//        denchik.addPet(kisik);
//        denchik.addPet(kotyara);
//
//        when(ownerService.findCat(1)).thenReturn(kisik);
//        when(ownerService.findCat(2)).thenReturn(kotyara);
//        assertEquals("Kisik", ownerService.findCat(1).getName());
//        assertEquals("Kotyara", ownerService.findCat(2).getName());
//
//        ownerService.removeCat(denchik, kotyara);
//        catService.deleteCat(kotyara);
//
//        when(ownerService.findCat(2)).thenReturn(null);
//        assertNull(ownerService.findCat(2));
//    }
}
