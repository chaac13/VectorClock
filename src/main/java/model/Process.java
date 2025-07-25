package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Process implements Runnable {
    private int id;
    private List<EventLog> eventLogs = new ArrayList<>();
    private VectorClock clock;
    private List<Message> receivedMessages;
    private List<Process> processList; // para enviar mensagens
    private boolean running = true;
    private Random random = new Random();

    public Process(int id, int totalProcess) {
        this.id = id;
        this.clock = new VectorClock(totalProcess);
        this.receivedMessages = new ArrayList<>();
    }

    public void setProcessList(List<Process> processList) {
        this.processList = processList;
    }

    public void stop() {
        this.running = false;
    }

    public void localEvent(){
        clock.increment(id);
        eventLogs.add(new EventLog(EventType.LOCAL, id, "Evento local", clock.getClock()));
    }

    public void receiveMessage(){
        if (receivedMessages.isEmpty()) return;

        for(Message message: receivedMessages){
            clock.updateOnReceive(message.getClock(), id);
            eventLogs.add(
                new EventLog(EventType.RECEIVE, id, "Recebeu de P" + message.getSenderIndex() + ": " + message.getContent(),
                clock.getClock())
            );
        }
        receivedMessages.clear();
    }

    public void sendMessage(int idReceiver, String text){
        clock.increment(id);
        Message message = new Message(id, clock.copy(), text);
        Process receiver = processList.get(idReceiver);
        receiver.setReceivedMessages(message);
        eventLogs.add(new EventLog(EventType.SEND, id, "Enviou para P" + idReceiver + ": " + text, clock.getClock()));
    }

    public void setReceivedMessages(Message message){
        this.receivedMessages.add(message);
    }

    public List<EventLog> getEventLogs() {
        return eventLogs;
    }

    public int getId() {
        return id;
    }

    public VectorClock getClock() {
        return clock;
    }

    @Override
    public void run() {
        while (running) {
            try {
                int action = random.nextInt(3); // 0: local, 1: send, 2: receive
                switch (action) {
                    case 0 -> localEvent();
                    case 1 -> {
                        int target = random.nextInt(processList.size());
                        if (target != id) {
                            sendMessage(target, "Olá de P" + id);
                        }
                    }
                    case 2 -> receiveMessage();
                }

                Thread.sleep(500 + random.nextInt(500)); // 0.5s a 1s
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
