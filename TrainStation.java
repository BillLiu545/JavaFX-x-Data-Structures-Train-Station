import java.util.Hashtable;
import java.util.*;


public class TrainStation extends LinkedQueue<Train>
{
    private class Destinations
    {
        private static String[] destinList =
        {"New York", "Boston", "Washington", "Chicago", "San Francisco", "Seattle"};
        public static String[] getList()
        {
            return destinList;
        }
    }
    
    public Train arrival()
    {
        String[] destinList = Destinations.getList();
        Random rand = new Random();
        Integer whichDestin = rand.nextInt(destinList.length);
        String destination  = destinList[whichDestin];
        
        Integer time = rand.nextInt(12);
        if (time == 0)
        {
            time = 12;
        }
        StringBuilder timeSB = new StringBuilder(time.toString());
        Integer whichTime = rand.nextInt(2);
        if (whichTime==1)
        {
            timeSB.append(" PM");
        }
        else
        {
            timeSB.append(" AM");
        }
        Train arrived = new Train(timeSB.toString(),destination);
        enqueue(arrived);
        return arrived;
    }
    
    public Train departure()
    {
        Train depart;
        if (!isEmpty()) {
            depart = dequeue();
        }
        else
        {
            depart = null;
        }
        return depart;
    }
    
    public static void main(String[] args) 
    {
        TrainStation station = new TrainStation();
        Train one = station.arrival();
        System.out.println("Train bound for " + one.getDestination() + " at " + one.getDepartTime() + " has arrived.");
        Train departed = station.departure();
        System.out.println("Train bound for " + departed.getDestination() + " at " + departed.getDepartTime() + " has departed.");
    }
}
