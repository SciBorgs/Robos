package org.sciborgs1155.robot.hanger;

public class Hanger {
    // two air pistons?? should just have one command "fire" that releases it iguess

    private HangerIO hardware;

    Hanger(HangerIO pivot) {
        this.hardware = pivot;
    }
}

