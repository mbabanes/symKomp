package sk.util;

public interface Observable
{
    public <T> void  addObserver(Observer<T> o);
    public void reportIsFree();
}
