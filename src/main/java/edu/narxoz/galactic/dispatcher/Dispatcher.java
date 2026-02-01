package edu.narxoz.galactic.task;

import edu.narxoz.galactic.dispatcher.Result;
import edu.narxoz.galactic.drones.Drone;
import edu.narxoz.galactic.drones.DroneStatus;

public class Dispatcher {

    public Result assignTask(DeliveryTask task, Drone drone){
        if(task == null || drone == null){
            return new Result(false, "Task  or drone is null");
        }
        if(drone.getStatus() != DroneStatus.IDLE){
            return new Result(false, "Drone is not IDLE");
        }
        if(task.getCargo().getWeightKg() > drone.getMaxPayloadKg()) {
            return new Result(false, "Cargo overweight");
        }
        if(task.getState() != TaskState.CREATED){
            return new Result(false, "Task state is not CREATED");
        }
        task.setAssignedDrone(drone);
        task.setState(TaskState.ASSIGNED);
         drone.setЫ
    }
}
