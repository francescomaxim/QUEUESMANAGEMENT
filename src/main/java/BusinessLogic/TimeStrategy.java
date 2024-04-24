package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;

public class TimeStrategy implements Strategy{
    @Override
    public void addTask(List<Server> servers, Task newTask) {
        int index = findFastest(servers);
        try {
            SimulationManager.averageWaiting.getAndAdd(servers.get(index).getTime());
            SimulationManager.averageWaiting.getAndAdd(newTask.getServiceTime());
        }catch (IndexOutOfBoundsException e){

        }
        catch (NullPointerException ee){

        }
        servers.get(index).addTask(newTask);
    }
    private int findFastest(List<Server> x){
        int index = -1;
        int min = Integer.MAX_VALUE;
        for(Server i : x){
            if(i.getTime() < min){
                index = x.indexOf(i);
                min = i.getTime();
            }
        }
        return index;
    }
}
