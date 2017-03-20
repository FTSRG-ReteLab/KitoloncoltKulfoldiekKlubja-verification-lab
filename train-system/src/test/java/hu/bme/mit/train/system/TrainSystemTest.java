package hu.bme.mit.train.system;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.system.TrainSystem;

public class TrainSystemTest {

	TrainController controller;
	TrainSensor sensor;
	TrainUser user;
	
	@Before
	public void before() {
		TrainSystem system = new TrainSystem();
		controller = system.getController();
		sensor = system.getSensor();
		user = system.getUser();

		sensor.overrideSpeedLimit(50);
	}
	
	@Test
	public void OverridingJoystickPosition_IncreasesReferenceSpeed() {
		sensor.overrideSpeedLimit(10);
		Assert.assertEquals(0, controller.getReferenceSpeed());
		try {
			user.overrideJoystickPosition(5);
			this.wait(1);
			Assert.assertEquals(5, controller.getReferenceSpeed());
			this.wait(1);
			Assert.assertEquals(10, controller.getReferenceSpeed());
			this.wait(1);
			Assert.assertEquals(10, controller.getReferenceSpeed());
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}

	@Test
	public void OverridingJoystickPositionToNegative_SetsReferenceSpeedToZero() {
		try {
			user.overrideJoystickPosition(4);
			this.wait(1);
			user.overrideJoystickPosition(-5);
			this.wait(1);
			Assert.assertEquals(0, controller.getReferenceSpeed());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
}
