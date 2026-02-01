package edu.narxoz.galactic.dispatcher;

import edu.narxoz.galactic.drones.Drone;
import edu.narxoz.galactic.drones.DroneStatus;
import edu.narxoz.galactic.task.DeliveryTask;
import edu.narxoz.galactic.task.TaskState;

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
        task.assignFromDispatcher(drone);
        drone.markInFlight();
        return new Result(true, "Assigned");
    }

    public Result completeTask(DeliveryTask task){
        if(task == null){
            return new Result(false, "Task null");
        }
        if(task.getState() != TaskState.ASSIGNED){
            return new Result(false, "Task not ASSIGNED");
        }
        Drone drone = task.getAssignedDrone();
        task.completeFromDispatcher();
        drone.markIdle();
        return new Result(true, "Completed");
    }
}
