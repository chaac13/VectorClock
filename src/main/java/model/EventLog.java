package model;

import java.util.Arrays;

public class EventLog {
    private final EventType type;
    private final int processId;
    private final String content;
    private final int[] clockSnapshot;

    public EventLog(EventType type, int processId, String content, int[] clockSnapshot) {
        this.type = type;
        this.processId = processId;
        this.content = content;
        this.clockSnapshot = Arrays.copyOf(clockSnapshot, clockSnapshot.length);
    }

    @Override
    public String toString() {
        return "Evento: " + type +
                " | Processo: " + processId +
                " | Conteúdo: " + content +
                " | Relógio: " + Arrays.toString(clockSnapshot);
    }
}
 