package model;

public class VectorClock {
    private final int[] clock;

    public void increment(int ProcessIndex){
        clock[ProcessIndex]++;
    }

    public void updateOnReceive(VectorClock receivedClock){
        for (int i = 0; i < clock.length; i++) {
            clock[i] = Math.max(clock[i], receivedClock.clock[i]);
        }
    }

    public VectorClock copy() {
        VectorClock copy = new VectorClock(clock.length);
        System.arraycopy(clock, 0, copy.clock, 0, clock.length);
        return copy;
    }
    public VectorClock(int size){
        this.clock = new int[size];
    }
    public int[] getClock() {
        return clock;
    }
}
