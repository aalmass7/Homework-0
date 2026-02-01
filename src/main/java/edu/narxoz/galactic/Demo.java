package edu.narxoz.galactic;

import edu.narxoz.galactic.bodies.*;
import edu.narxoz.galactic.cargo.*;
import edu.narxoz.galactic.drones.*;
import edu.narxoz.galactic.task.*;
import edu.narxoz.galactic.dispatcher.*;

public class Demo {
    public static void main(String[] args) {
        Planet earth = new Planet("Earth", 0, 0, "Oxygen");
        SpaceStation station = new SpaceStation("ISS", 100, 0, 3);

        Cargo cargo = new Cargo(20, "Supplies");

        LightDrone light = new LightDrone("LD-1", 10);
        HeavyDrone heavy = new HeavyDrone("HD-1", 50);

        DeliveryTask task = new DeliveryTask(earth, station,cargo);

        Dispatcher dispatcher = new Dispatcher();

        System.out.println(dispatcher.assignTask(task, light));
        System.out.println(dispatcher.assignTask(task, heavy));
        System.out.println("Estimated time: " + task.estimateTime());
        System.out.println(dispatcher.completeTask(task));
        System.out.println("Final state: " + task.getState());
    }
}
