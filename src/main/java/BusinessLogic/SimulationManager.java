package BusinessLogic;

import GUI.SimulationFrame;
import Model.Server;
import Model.Task;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class SimulationManager implements Runnable{
    public Scheduler scheduler;
    public static BlockingQueue<Task> tasks;
    int policy = 1;
    int maxNoServers = 5;
    static int time = 0;
    static AtomicInteger maxClients;
    static AtomicInteger averageService;
    static AtomicInteger averageWaiting;
    static AtomicInteger times;
    public SimulationManager() throws InterruptedException {
        SimulationFrame simulationFrame = new SimulationFrame();

        while (SimulationFrame.starting == 0){
            Thread.sleep(1000);
        }

        maxClients = new AtomicInteger(0);
        averageService = new AtomicInteger(0);
        averageWaiting = new AtomicInteger(0);
        times = new AtomicInteger(0);

        maxNoServers = SimulationFrame.queuesNr;
        System.out.println(maxNoServers);
        scheduler = new Scheduler(maxNoServers);
        tasks = new LinkedBlockingQueue<>();

        getRandomTasks(SimulationFrame.clientNr);
        averageService.set(averageServiceTime());

    }

    public void getRandomTasks(int n){
        tasks = new LinkedBlockingQueue<>();
        for(int i = 0; i < n; i++){
            Random random = new Random();
            tasks.add(new Task(i, random.nextInt(SimulationFrame.minArr, SimulationFrame.maxArr), random.nextInt(SimulationFrame.minSer, SimulationFrame.maxSer)));
        }
    }

    public int averageServiceTime(){
        int t = 0;
        for(Task i : tasks){
            t += i.getServiceTime();
        }
        t /= SimulationFrame.clientNr;
        return t;
    }

    @Override
    public void run() {
        List<Thread> myThreads = scheduler.getThreads();
        for(Thread i : myThreads){
            i.start();
        }
        while ((!tasks.isEmpty() || scheduler.isEmptyEmpty()) && time < SimulationFrame.sim){
            String appendString = "";
            try {
                for(Task i : tasks){
                    if(i.getArrivalTime() == time){
                        scheduler.dispatchTask(i);
                        tasks.remove(i);
                    }
                }
            }catch (NoSuchElementException ff){

            }
            appendString = "Time : " + (time++) + "\n";
            SimulationFrame.simulation.append(appendString);
            appendString = "Clients : " + tasks + "\n";
            SimulationFrame.simulation.append(appendString);
            for(Server i : scheduler.servers){
                appendString = "Queue " + i.getNumberOfServer() +  " : " + i + "\n";
                SimulationFrame.simulation.append(appendString);
            }
            try {
                Thread.sleep(SimulationFrame.slep);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        int w = 0;
        try {
            w = averageWaiting.get();
            w /= SimulationFrame.clientNr;
        }catch (NullPointerException y){

        }

        SimulationFrame.simulation.append("It finished" + "\n");
        SimulationFrame.simulation.append("Peak hour : " + times + "\n");
        SimulationFrame.simulation.append("Average service : " + averageService + "\n");
        SimulationFrame.simulation.append("Average waiting : " + w + "\n");

        try{
            PrintWriter writer = new PrintWriter("clienti","UTF-8");
            writer.println(SimulationFrame.simulation.getText());
            writer.close();
        }catch (FileNotFoundException e){

        }catch (UnsupportedEncodingException ee){

        }

    }

    public static void main(String[] args) throws InterruptedException {
        SimulationManager simulationManager = new SimulationManager();
        while(SimulationFrame.starting == 0) {
            Thread.sleep(1000);
        }
        Thread thread = new Thread(simulationManager);
        thread.start();
    }

}
