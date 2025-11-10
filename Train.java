
public class Train
{
    private String departTime, destination;
    public Train(String departTime, String destination)
    {
        this.departTime = departTime;
        this.destination = destination;
    }
    // Retrieve departure time
    public String getDepartTime() {
        return departTime;
    }
    // Setter for time in case of delay
    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }
    // Retrieve destination
    public String getDestination() {
        return destination;
    }
    // Setter for destination in case of rerouting
    public void setDestination(String destination) {
        this.destination = destination;
    }
}
