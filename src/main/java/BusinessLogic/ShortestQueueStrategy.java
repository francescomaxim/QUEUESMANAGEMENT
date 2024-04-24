package BusinessLogic;

import GUI.SimulationFrame;
import Model.Server;
import Model.Task;

import java.util.List;

public class ShortestQueueStrategy implements Strategy{
    @Override
    public void addTask(List<Server> servers, Task newTask) {

        int index = shortestQueue(servers);
        try {
            SimulationManager.averageWaiting.getAndAdd(servers.get(index).getTime());
            SimulationManager.averageWaiting.getAndAdd(newTask.getServiceTime());
        }catch (IndexOutOfBoundsException e){

        }
        catch (NullPointerException ee){

        }
        servers.get(index).addTask(newTask);
    }
    private int shortestQueue(List<Server> x){
        int min = Integer.MAX_VALUE;
        int index = -1;
        for(Server i : x){
            if(i.getSize() < min){
                index = x.indexOf(i);
                min = i.getSize();
            }
        }
        return index;
    }
}
