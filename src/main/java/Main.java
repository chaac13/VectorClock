import model.Process;
import model.VectorClock;
import model.EventType;
import model.EventLog;
import model.Message;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int numProcess = 3;
        List<Process> processList = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        // Inicializar processos
        for (int i = 0; i < numProcess; i++) {
            processList.add(new Process(i, numProcess));
        }

        // Referência global da lista para envio de mensagens
        for (Process p : processList) {
            p.setProcessList(processList);
        }

        // Iniciar threads
        for (Process p : processList) {
            Thread t = new Thread(p);
            threads.add(t);
            t.start();
        }

        // Executar por um tempo fixo
        Thread.sleep(8000); // simular por 8 segundos

        // Parar execução
        for (Process p : processList) {
            p.stop();
        }

        for (Thread t : threads) {
            t.join(); // Espera todas as threads terminarem
        }

        // Mostrar logs
        System.out.println("\n--- LOG DE EVENTOS ---");
        for (Process p : processList) {
            System.out.println("Processo P" + p.getId() + ":");
            for (EventLog log : p.getEventLogs()) {
                System.out.println(log);
            }
            System.out.println();
        }
    }
}
