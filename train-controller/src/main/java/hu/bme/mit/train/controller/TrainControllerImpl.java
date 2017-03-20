package hu.bme.mit.train.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hu.bme.mit.train.interfaces.TrainController;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	private boolean run;
	
	private List<Object> notify;
	
	public TrainControllerImpl() {
		notify = Collections.synchronizedList(new ArrayList<Object>());
	}

	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = 0;
            }
		}

		enforceSpeedLimit();
	}
	
	@Override
	public void startTimer() {
		run = true;
		Thread t = new Thread() {
			@Override
			public synchronized void run() {
				while(run) {
					try {
						this.wait(1);
						tick();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		t.start();
	}
	
	@Override
	public void stopTimer() {
		run = false;
	}
	
	private synchronized void tick() {
		System.out.println("Tick");
		followSpeed();
		
		synchronized(notify) {
			for(Object obj: notify) {
				//obj.notify();
			}
		}
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		
	}

	@Override
	public void addToMonitor(Object me) {
		notify.add(me);
		
	}

}
