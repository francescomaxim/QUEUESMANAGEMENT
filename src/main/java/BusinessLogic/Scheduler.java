package BusinessLogic;

import GUI.SimulationFrame;
import Model.Server;
import Model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Scheduler{

    public List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;
    private int strategyy;
    public List<Thread> threads;
    public Scheduler(int maxNoServers){
        if(SimulationFrame.checkBox.getSelectedIndex() == 0) {
            this.strategyy = 0;
        }else{
            this.strategyy = 1;
        }
        setStrategy();
        servers = new ArrayList<>();
        threads = new ArrayList<>();
        for(int i = 0; i < maxNoServers; i++){
            servers.add(new Server(i));
            threads.add(new Thread(servers.get(i)));
        }
    }

    public boolean isEmptyEmpty(){
        for(Server i : servers){
            if(i.getSize() != 0){
                return true;
            }
        }
        return false;
    }
    public void setStrategy(){
        if(strategyy == 0){
            strategy = new ShortestQueueStrategy();
        }else{
            strategy = new TimeStrategy();
        }
    }

    private int isPeakHour(){
        int p = 0;
        for(Server i : servers){
            p += i.getSize();
        }
        return p;
    }

    public void dispatchTask(Task newTask){

        if(isPeakHour() > SimulationManager.maxClients.get()){
            SimulationManager.maxClients.set(isPeakHour());
            SimulationManager.times.set(SimulationManager.time);
        }

        strategy.addTask(servers, newTask);
    }

    public List<Thread> getThreads(){
        return threads;
    }

}
