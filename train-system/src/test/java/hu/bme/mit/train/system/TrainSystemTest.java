package hu.bme.mit.train.system;

import org.junit.After;
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
		
		controller.addToMonitor(this);
		
		controller.startTimer();

		sensor.overrideSpeedLimit(50);
	}
	
	/*@Test
	public void OverridingJoystickPosition_IncreasesReferenceSpeed() {
		sensor.overrideSpeedLimit(10);

		Assert.assertEquals(0, controller.getReferenceSpeed());
		
		user.overrideJoystickPosition(5);

		//controller.followSpeed();
		Assert.assertEquals(5, controller.getReferenceSpeed());
		//controller.followSpeed();
		Assert.assertEquals(10, controller.getReferenceSpeed());
		//controller.followSpeed();
		Assert.assertEquals(10, controller.getReferenceSpeed());
	}

	@Test
	public void OverridingJoystickPositionToNegative_SetsReferenceSpeedToZero() {
		user.overrideJoystickPosition(4);
		//controller.followSpeed();
		user.overrideJoystickPosition(-5);
		//controller.followSpeed();
		Assert.assertEquals(0, controller.getReferenceSpeed());
	}*/
	
	@Test
	public synchronized void OverridingJoystickPosition() throws InterruptedException {
		sensor.overrideSpeedLimit(2);
		
		Assert.assertEquals(0, controller.getReferenceSpeed());
		
		this.wait(1);
		user.overrideJoystickPosition(1);
		
		this.wait(1);
		Assert.assertEquals(1, controller.getReferenceSpeed());

		this.wait(1);
		Assert.assertEquals(2, controller.getReferenceSpeed());
		
		this.wait(1);
		Assert.assertEquals(2, controller.getReferenceSpeed());
		
		user.overrideJoystickPosition(0);

		this.wait(1);
		Assert.assertEquals(2, controller.getReferenceSpeed());
		
		this.wait(1);
		Assert.assertEquals(2, controller.getReferenceSpeed());
		
		user.overrideJoystickPosition(-1);
		
		this.wait(1);
		Assert.assertEquals(1, controller.getReferenceSpeed());
		user.overrideJoystickPosition(0);
		
		this.wait(1);
		Assert.assertEquals(1, controller.getReferenceSpeed());
		
	}

	@After
	public void after() {
		controller.stopTimer();
	}
	
}
