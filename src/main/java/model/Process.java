package model;

import java.util.ArrayList;
import java.util.List;

public class Process {
    private int id;
    private VectorClock clock;
    private List<Message> receivedMessages;
    private List<Message> readMessages;

    public void LocalEvent(){
        clock.increment(id);
    }
    public void ReceiveMessage(){
        for(Message message: receivedMessages){
            if(receivedMessages.isEmpty()) {
                System.out.println("Não há nenhuma mensagem para ser recebida");
                break;
            }
            clock.updateOnReceive(message.getClock());
            readMessages.add(message);
            receivedMessages.remove(message);
        }
    }

    public Process(int id, int totalProcess) {
        this.id = id;
        this.clock = new VectorClock(totalProcess);
        this.receivedMessages = new ArrayList<>();
        this.readMessages = new ArrayList<>();
    }
}
