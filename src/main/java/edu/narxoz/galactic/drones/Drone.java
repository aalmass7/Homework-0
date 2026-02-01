package edu.narxoz.galactic.drones;

public abstract class Drone {
    private String id;
    private DroneStatus status;
    private double maxPayloadKg;

    protected Drone(String id, double maxPayloadKg){
        if(maxPayloadKg <= 0){
            throw new IllegalArgumentException("Payload must be positive");
        }
        this.id = id;
        this.maxPayloadKg = maxPayloadKg;
        this.status = DroneStatus.IDLE;

    }
    public String getId(){
        return id;
    }

    public DroneStatus getStatus(){
        return  status;
    }

    public double getMaxPayloadKg(){
        return maxPayloadKg;
    }
    protected void setStatus(DroneStatus status){
        this.status = status;
    }
    public void markInFlight(){
        setStatus(DroneStatus.IN_FLIGHT);
    }
    public void markIdle(){
        setStatus(DroneStatus.IDLE);
    }
    public abstract double speedKmPerMin();
}
