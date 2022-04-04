/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.dao.TrainingDAO;
import model.dao.mysql.MySQLTrainingDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cathal
 */
public class AddTrainingTest {

    public AddTrainingTest() {
    }

    private Training training;
    private Price price;
    private String title;
    private String description;
    private int capacity;
    private int duration;
    private TrainingDAO trainingDAO;

    @Before
    public void setUp() {

        training = new Training();
        price = new Price();
        title = "title";
        description = "description";
        capacity = 10;
        duration = 10;

        price.setId(2);
        training.setPrice(price);
        training.setCapacity(capacity);
        training.setDuration(duration);
        training.setDescription(description);
        training.setTitle(title);

        trainingDAO = new MySQLTrainingDAO();

    }

    @Test
    public void saveTraining() throws Exception {

        int status = trainingDAO.save(training);

        System.out.println("status after insert = " + status);

        assertTrue("status ==1", status == 1);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
