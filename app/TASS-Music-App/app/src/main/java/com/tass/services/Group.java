package com.tass.services;



import java.util.ArrayList;

public class Group {

    public String Name;
    public String ID;
    public boolean isOpen = false;
    public String Current;
    public String Next;
    //public ArrayList<QueueItem> Queue;

    protected Group()
    {

    }
    protected Group(String name, String ID, boolean isOpen)
    {
        this.Name = name;
        this.ID = ID;
        this.isOpen = isOpen;
    }
}
