/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.pate0968.heartbeat.entity;

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
public class HeartTest {
    
    public HeartTest() {
    }
    public Heart heart;

//    @BeforeEach
//    public void setUp() throws Exception {
//        heart = new Heart();
//        System.out.println("inside Set up");
//    }

    @Test
    public void testGetX() {
        heart = new Heart();
        heart.setX(100);
        assertEquals(100, heart.getX());
    }

    @Test
    public void testGetY() {
        heart = new Heart();
        heart.setY(200);
        assertEquals(200, heart.getY());
    }

    @Test
    public void testGetSize() {
        heart = new Heart();
        heart.setSize(50);
        assertEquals(50, heart.getSize());
    }

    @Test
    public void testGetContractedSize() {
        heart = new Heart();
        heart.setContractedSize(60);
        assertEquals(60, heart.getContractedSize());
    }

    @Test
    public void testGetBeatCount() {
        heart = new Heart();
        heart.setBeatCount(100);
        assertEquals(100, heart.getBeatCount());
    }

    @Test
    public void testUpdates() {
        Heart oldHeart = new Heart();
        oldHeart.setX(10);
        oldHeart.setY(20);
        oldHeart.setSize(30);
        oldHeart.setContractedSize(40);
        oldHeart.setBeatCount(50);

        heart.setX(100);
        heart.setY(200);
        heart.setSize(300);
        heart.setContractedSize(400);
        heart.setBeatCount(500);

        heart.updates(oldHeart);

        assertEquals(100, oldHeart.getX());
        assertEquals(200, oldHeart.getY());
        assertEquals(300, oldHeart.getSize());
        assertEquals(400, oldHeart.getContractedSize());
        assertEquals(500, oldHeart.getBeatCount());
    }

    @Test
    public void testInit() {
        heart = new Heart();
        heart.setX(null);
        heart.setY(null);
        heart.setSize(null);
        heart.setContractedSize(null);
        heart.setBeatCount(null);

        heart.init();

        assertEquals(0, heart.getX());
        assertEquals(0, heart.getY());
        assertEquals(0, heart.getSize());
        assertEquals(0, heart.getContractedSize());
        assertEquals(0, heart.getBeatCount());
    }
    
    
}
