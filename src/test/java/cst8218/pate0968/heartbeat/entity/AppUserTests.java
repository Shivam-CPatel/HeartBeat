/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.pate0968.heartbeat.entity;

import cst8218.pate0968.heartbeat.entity.AppUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Kelsey Phillips
 */
public class AppUserTests {
    
    public AppUserTests() {}

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }
    //TEST for AppUser
    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetId() {
        AppUser user = new AppUser();
        user.setId(1L);
        assertEquals(1L, user.getId());
    }

    @Test
    public void testGetUserId() {
        AppUser user = new AppUser();
        user.setUserId("testUser");
        assertEquals("testUser", user.getUserId());
    }

    @Test
    public void testGetGroupName() {
        AppUser user = new AppUser();
        user.setGroupName("testGroup");
        assertEquals("testGroup", user.getGroupName());
    }

    @Test
    public void testToString() {
        AppUser user = new AppUser();
        user.setId(1L);
        assertEquals("com.mycompany.appuser.entity.AppUser[ id=1 ]", user.toString());
    }

    @Test
    public void testEquals() {
        AppUser user1 = new AppUser();
        user1.setId(1L);
        AppUser user2 = new AppUser();
        user2.setId(1L);
        assertEquals(user1, user2);
    }

    @Test
    public void testNotEquals() {
        AppUser user1 = new AppUser();
        user1.setId(1L);
        AppUser user2 = new AppUser();
        user2.setId(2L);
        assertNotEquals(user1, user2);
    }
    
}
