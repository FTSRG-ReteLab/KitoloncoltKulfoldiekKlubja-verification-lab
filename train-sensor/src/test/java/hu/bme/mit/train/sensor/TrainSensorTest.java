package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.user.TrainUserImpl;

import static org.mockito.Mockito.*;

public class TrainSensorTest {

	TrainUser user;
	TrainController controller;
	
    @Before
    public void before() {
    	TrainController moController = Mockito.spy(TrainController.class);
    	controller = moController;
    	
    	when(moController.getReferenceSpeed()).thenReturn(50);
    	
    	user = new TrainUserImpl(moController);
    	
    }

    @Test
    public void ThisIsAnExampleTestStub() {
    	controller.setSpeedLimit(150);
    	
    	Assert.
    }
}
