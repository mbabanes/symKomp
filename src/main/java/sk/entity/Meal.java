package sk.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Meal
{
    private int time;
    private String name;


    public Meal()
    {
    }

    public Meal(int time)
    {
        this.time = time;
    }

    public Meal(int time, String name)
    {
        this.time = time;
        this.name = name;
    }
}
