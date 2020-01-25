package de.invesdwin.nowicket.examples.guide.page.documentation.datatypes.thirdcar;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
public class Motor extends AValueObject {

    private int powerInHP;
    private MotorState state;

    public Motor(final int hp) {
        this.powerInHP = hp;
        this.state = MotorState.off;
    }

    public MotorState getState() {
        return state;
    }

    public int getPowerInHP() {
        return powerInHP;
    }

    public void setPowerInHP(final int powerInPS) {
        this.powerInHP = powerInPS;
    }

    public void turnOn() {
        this.state = MotorState.on;
    }

    public void turnOff() {
        this.state = MotorState.off;
    }

}
