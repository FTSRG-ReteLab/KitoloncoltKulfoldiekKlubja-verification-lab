package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.user.TrainUserImpl;

import static org.mockito.Mockito.*;

public class TrainSensorTest {

	TrainUser user;
	TrainController controller;
	TrainSensor sensor;
	
	private Object note;
	
    @Before
    public void before() {
    	TrainController moController = Mockito.spy(TrainController.class);
    	controller = moController;
    	
    	note = new Object();
		controller.addToMonitor(note);
		
		
		when(moController.getReferenceSpeed()).thenReturn(50);
    	
    	user = new TrainUserImpl(moController);
    	
    	
    	sensor = new TrainSensorImpl(controller, user);
		
    	
    }

    @Test
    public void AlarmStateToAbsoluteMargin() throws InterruptedException {
    	sensor.overrideSpeedLimit(150);
    	
    	Assert.assertEquals(50, controller.getReferenceSpeed());
    	Assert.assertEquals(true, sensor.getAlarmState());
    	
    	when(controller.getReferenceSpeed()).thenReturn(140);
    	
    	Assert.assertEquals(140, controller.getReferenceSpeed());
    	Assert.assertEquals(false, sensor.getAlarmState());
    	
    	when(controller.getReferenceSpeed()).thenReturn(-5);
    	Assert.assertEquals(true, sensor.getAlarmState());
    	
    	when(controller.getReferenceSpeed()).thenReturn(501);
    	Assert.assertEquals(true, sensor.getAlarmState());
    }
}
