package model;

public class Message {
    private int senderIndex;
    private VectorClock clock;
    private String content;

    public Message(int senderIndex, VectorClock clock, String content) {
        this.senderIndex = senderIndex;
        this.clock = clock.copy();
        this.content = content;
    }

    public int getSenderIndex() {
        return senderIndex;
    }

    public VectorClock getClock() {
        return clock;
    }

    public String getContent() {
        return content;
    }
}
