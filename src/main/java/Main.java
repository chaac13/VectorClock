import model.Message;
import model.Process;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Process> ProcessThread = new ArrayList<>();

        int numProcess = 2;
        for(int i = 0; i< numProcess;i++){
            ProcessThread.add(new Process(i,numProcess));
        }
        for(Process process: ProcessThread){
            process.localEvent();
            System.out.println(Arrays.toString(process.getClock().getClock()));//isso aqui ta horrivel kkk, mas o Evento Local ta funcionando
        }
        Process process1 = ProcessThread.get(0);
        Process process2 = ProcessThread.get(1);
        process1.sendMessage(process1,1,"Alôooo",ProcessThread);
        process2.receiveMessage();

        System.out.println(Arrays.toString(ProcessThread.get(1).getClock().getClock()));

        process2.sendMessage(process2,0,"Alôooo",ProcessThread);
        process1.receiveMessage();
        process1.localEvent();
        System.out.println(Arrays.toString(ProcessThread.get(0).getClock().getClock()));


    }
}

