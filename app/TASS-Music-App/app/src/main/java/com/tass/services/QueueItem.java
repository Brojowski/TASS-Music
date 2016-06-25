package com.tass.services;


public class QueueItem
{
    private String title;
    private String Artist;
    private String _uri;
    private int _votes;

    public QueueItem(String uri, int votes)
    {
        _uri = uri;
        _votes = votes;
    }

    public String getTitle()
    {
//        return title;
        return "title";
    }

    public String getAuthor()
    {
     return "autjpr";
//     return Artist;
    }

    private void getTrackInfo()
    {
        //resolve from the server here
    }
}
