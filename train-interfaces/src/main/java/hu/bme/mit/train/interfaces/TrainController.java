package hu.bme.mit.train.interfaces;

import java.util.List;

public interface TrainController {

	public void addToMonitor(Object me);

	void followSpeed();

	int getReferenceSpeed();

	void setSpeedLimit(int speedLimit);

	void setJoystickPosition(int joystickPosition);

	void startTimer();

	void stopTimer();

}
