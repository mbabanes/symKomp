package sk.sim.objects.utill;

import lombok.Getter;

@Getter
public class WaitingTimeInQueueCalculator
{
    private double startTime;
    private double endTime;




    public WaitingTimeInQueueCalculator(double startTime)
    {
        this.startTime = startTime;
    }

    public void setEndTime(double endTime)
    {
        this.endTime = endTime;
    }

    public double calculateTimeInQueue()
    {
        return endTime - startTime;
    }
}
