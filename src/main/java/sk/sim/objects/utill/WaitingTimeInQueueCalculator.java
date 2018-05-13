package sk.sim.objects.utill;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.Instant;

@Getter
public class WaitingTimeInQueueCalculator
{
    private Instant startTime;
    private Instant endTime;

    public WaitingTimeInQueueCalculator(Instant startTime)
    {
        this.startTime = startTime;
    }

    public void setEndTime(Instant endTime)
    {
        this.endTime = endTime;
    }

    public Duration calculateTimeInQueue()
    {
        return Duration.between(startTime, endTime);
    }
}
