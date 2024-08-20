package main.java.org.sciborgs1155.robot.wrist;

public interface WristIO {

    /**
     * Sets the voltage of the wrist.
     * @param voltage The voltage of the wrist.
     */
    public void setVoltage(double voltage);

    /**
     * Sets the current limit for the wrist.
     * @param limit The proposed current limit for the wrist.
     */
    public void setCurrentLimit(measure<Current> limit);

    /**
     * Gets the position of the wrist.
     * 
     * @return The position of the wrist in radians.
     */
    public double getPosition();

    /**
     * Gets the velocity of the wrist.
     * 
     * @return The velocity of the wrist in radians / second.
     */
    public double getVelocity();

}
