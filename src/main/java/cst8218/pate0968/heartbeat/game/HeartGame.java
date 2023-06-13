/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.pate0968.heartbeat.game;

import cst8218.pate0968.heartbeat.entity.Heart;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Owner
 */
// StartUp annotation run this class from 
@Startup
@Singleton
public class HeartGame {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    // Object to HeartFacade class
    @EJB
    private cst8218.pate0968.heartbeat.HeartFacade heartFacade;
    // change at which heat beat should change
    private double CHANGE_RATE = 10;
    // array lost to store all the hearts from database
    private List<Heart> hearts;
    
    // method with PostConstruct annotation run directly after the construction get runned by compilers
    @PostConstruct
    public void go() {
        new Thread(new Runnable() {
            public void run() {
                // the game runs indefinitely
                while (true) {
                    //update all the hearts and save changes to the database
                    hearts = heartFacade.findAll();
                    for (Heart heart : hearts) {
                        heart.advanceOneTimeIncrement();
                        heartFacade.edit(heart);
                    }
                    //sleep while waiting to process the next frame of the animation
                    try {
                        // wake up roughly CHANGE_RATE times per second
                        Thread.sleep((long)(1.0/CHANGE_RATE*1000));                               
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
