package edu.narxoz.galactic.dispatcher;

import edu.narxoz.galactic.drones.Drone;
import edu.narxoz.galactic.drones.DroneStatus;
import edu.narxoz.galactic.task.DeliveryTask;
import edu.narxoz.galactic.task.TaskState;

public class Dispatcher {

    public Result assignTask(DeliveryTask task, Drone drone) {
        Result check = validateAssign(task, drone);
        if (!check.ok()) {
            return check;
        }
        applyAssign(task, drone);
        return new Result(true, "Assigned");
    }

    private Result validateAssign(DeliveryTask task, Drone drone) {
        if (task == null || drone == null) {
            return new Result(false, "Task or drone is null");
        }
        if (drone.getStatus() != DroneStatus.IDLE) {
            return new Result(false, "Drone is not IDLE");
        }
        if (task.getCargo().getWeightKg() > drone.getMaxPayloadKg()) {
            return new Result(false, "Cargo overweight");
        }
        if (task.getState() != TaskState.CREATED) {
            return new Result(false, "Task state is not CREATED");
        }
        return new Result(true, "");
    }

    private void applyAssign(DeliveryTask task, Drone drone) {
        task.assignFromDispatcher(drone);
        drone.markInFlight();
    }

    public Result completeTask(DeliveryTask task) {
        Result check = validateComplete(task);
        if (!check.ok()) {
            return check;
        }
        applyComplete(task);
        return new Result(true, "Completed");
    }

    private Result validateComplete(DeliveryTask task) {
        if (task == null) {
            return new Result(false, "Task null");
        }
        if (task.getState() != TaskState.ASSIGNED) {
            return new Result(false, "Task not ASSIGNED");
        }
        Drone drone = task.getAssignedDrone();
        if (drone == null) {
            return new Result(false, "No assigned drone");
        }
        if (drone.getStatus() != DroneStatus.IN_FLIGHT) {
            return new Result(false, "Drone isn't IN_FLIGHT");
        }
        return new Result(true, "");
    }

    private void applyComplete(DeliveryTask task) {
        Drone drone = task.getAssignedDrone();
        task.completeFromDispatcher();
        drone.markIdle();

    }
}

