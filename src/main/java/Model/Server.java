package Model;

import BusinessLogic.Scheduler;
import BusinessLogic.SimulationManager;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private int numberOfServer;
    private int time = 0;

    public Server(int numberOfServer){
        this.numberOfServer = numberOfServer;
        tasks = new LinkedBlockingQueue<>();
        waitingPeriod = new AtomicInteger(0);
    }

    public Server(){

    }


    public int getNumberOfServer(){
        return numberOfServer;
    }
    public void addTask(Task newTask){
        tasks.add(newTask);
        waitingPeriod.addAndGet(newTask.getServiceTime());
    }

    public int getSize(){
        return tasks.size();
    }

    public int getTime(){
        int time = 0;
        for(Task i : tasks){
            time += i.getServiceTime();
        }
        return time;
    }

    public void decrementFirst(BlockingQueue<Task> x){
        try {
            x.peek().setServiceTime(x.peek().getServiceTime() - 1);
            if(x.peek().getServiceTime() == 0){
                x.poll();
            }
        }catch (NullPointerException e){

        }
    }

    @Override
    public void run() {
        while(!SimulationManager.tasks.isEmpty() || !tasks.isEmpty()){
            decrementFirst(tasks);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String toString() {
        return " " + tasks;
    }
}
