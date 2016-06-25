package com.tass.services;



import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Queue;

public class Group {

    private ArrayList<QueueItem> _queue;

    public Group()
    {
        _queue = new ArrayList<>();
    }

    public void AddItem(QueueItem qi)
    {
        _queue.add(qi);
    }

    public ArrayList<QueueItem> getSongQueue() { return _queue; }
}
