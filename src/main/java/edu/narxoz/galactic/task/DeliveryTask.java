package edu.narxoz.galactic.task;

import edu.narxoz.galactic.bodies.CelestialBody;
import edu.narxoz.galactic.cargo.Cargo;
import edu.narxoz.galactic.drones.Drone;

public class DeliveryTask {
    private CelestialBody origin;
    private CelestialBody destination;
    private Cargo cargo;
    private TaskState state;
    private Drone assignedDrone;

    public DeliveryTask(CelestialBody origin, CelestialBody destination, Cargo cargo){
        if(origin == null || destination == null || cargo == null){
            throw new IllegalArgumentException("origin, destinition or cargo can't be null");
        }
        this.origin = origin;
        this.destination = destination;
        this.cargo = cargo;
        this.state = TaskState.CREATED;
        this.assignedDrone = null;
    }

    public CelestialBody getOrigin() {
        return origin;
    }

    public CelestialBody getDestination(){
        return destination;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public TaskState getState(){
        return state;
    }

    public Drone getAssignedDrone(){
        return assignedDrone;
    }
    public double estimateTime(){
        if(assignedDrone == null){
            throw new IllegalStateException("No assigned drone");
        }
        double speed = assignedDrone.speedKmPerMin();
        if(speed <= 0){
            throw new IllegalStateException("Invalid drone speed");
        }
        return origin.distanceTo(destination)/speed;
    }
    void setState(TaskState state){
        this.state = state;
    }
    void setAssignedDrone(Drone drone){
        this.assignedDrone = drone;
    }
    public void  assignFromDispatcher(Drone drone){
        if(drone == null){
            throw new IllegalArgumentException("Drone can't be null");
        }
        if(this.state != TaskState.CREATED){
            throw new IllegalStateException("Task must be CREATED");
        }
        this.assignedDrone = drone;
        this.state = TaskState.ASSIGNED;
    }
    public void completeFromDispatcher(){
        if(this.state != TaskState.ASSIGNED){
            throw new IllegalStateException("Task must be ASSIGNED");
        }
        this.state = TaskState.DONE;
    }

}
