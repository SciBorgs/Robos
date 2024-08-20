package org.sciborgs1155.robot.hanger;

import monologue.Logged;

public interface HangerIO extends AutoCloseable, Logged {

    /**
     * retracts the pistons to release the hangers.
     */
    public void retract();
}
