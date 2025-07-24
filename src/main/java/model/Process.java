package model;

import java.util.ArrayList;
import java.util.List;

public class Process {
    private int id;
    private VectorClock clock;
    private List<Message> receivedMessages;

    public void localEvent(){
        clock.increment(id);
    }
    public void receiveMessage(){
        for(Message message: receivedMessages){
            if(receivedMessages.isEmpty()) {
                System.out.println("Não há nenhuma mensagem para ser recebida");
            }
            clock.updateOnReceive(message.getClock(),getId());
        }
    }
    public void sendMessage(Process sender, int idReceiver, String text, List<Process> ProcessThread){
        clock.increment(id);
        Message message = new Message(sender.getId(), sender.getClock(),text);
        Process processReceiver = ProcessThread.get(idReceiver);
        processReceiver.setReceivedMessages(message);
    }

    public Process(int id, int totalProcess) {
        this.id = id;
        this.clock = new VectorClock(totalProcess);
        this.receivedMessages = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public VectorClock getClock() {
        return clock;
    }

    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(Message message){
        this.receivedMessages.add(message);
    }
}
