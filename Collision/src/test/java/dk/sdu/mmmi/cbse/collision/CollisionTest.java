/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.collision;

import data.Entity;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author BenPaxIndustries
 */
public class CollisionTest {
    
    public CollisionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of detectCollision method, of class Collision.
     */
    @Test
    public void testDetectCollisionCase1() {
        Collision instance = new Collision();
        
        // entity 1 test case state
        float x1 = 400;
        float y1 = 300;
        float radians1 = 0;
        float radius1 = 10;
        
        Entity entity1 = new Entity();
        
        entity1.setRadius(radius1);
        entity1.setRadians(radians1);
        entity1.setPositionX(x1);
        entity1.setPositionY(y1);
        
        System.out.println("Test case 1: " + instance.detectCollsion(entity1, entity1));
        assertTrue(instance.detectCollsion(entity1, entity1));
        
    }
    
    /**
     * Test of detectCollision method, of class Collision.
     */
    @Test
    public void testDetectCollisionCase2() {
        Collision instance = new Collision();
        
        // entity 1 test case state
        float x1 = 400;
        float y1 = 400;
        float radians1 = 0;
        float radius1 = 10;
        
        // entity 2 test case state
        float x2 = 405;
        float y2 = 390;
        float radians2 = 0;
        float radius2 = 12;
        
        Entity entity1 = new Entity();
        Entity entity2 = new Entity();
        
        entity1.setRadius(radius1);
        entity1.setRadians(radians1);
        entity1.setPositionX(x1);
        entity1.setPositionY(y1);
        
        entity2.setRadius(radius2);
        entity2.setRadians(radians2);
        entity2.setPositionX(x2);
        entity2.setPositionY(y2);
        
        System.out.println("Test case 2: " + instance.detectCollsion(entity1, entity2));
        assertTrue(instance.detectCollsion(entity1, entity2));
    }
    
    /**
     * Test of detectCollision method, of class Collision.
     */
    @Test
    public void testDetectCollisionCase3() {
        Collision instance = new Collision();
        
        // entity 1 test case state
        float x1 = 400;
        float y1 = 300;
        float radians1 = 0;
        float radius1 = 10;
        
        // entity 2 test case state
        float x2 = 6000;
        float y2 = 500;
        float radians2 = 0;
        float radius2 = 12;
        
        Entity entity1 = new Entity();
        Entity entity2 = new Entity();
        
        entity1.setRadius(radius1);
        entity1.setRadians(radians1);
        entity1.setPositionX(x1);
        entity1.setPositionY(y1);
        
        entity2.setRadius(radius2);
        entity2.setRadians(radians2);
        entity2.setPositionX(x2);
        entity2.setPositionY(y2);
        
        System.out.println("Test case 3: " + instance.detectCollsion(entity1, entity2));
        assertFalse(instance.detectCollsion(entity1, entity2));
    }
    
}
