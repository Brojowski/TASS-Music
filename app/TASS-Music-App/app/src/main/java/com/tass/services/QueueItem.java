package com.tass.services;


public class QueueItem
{
    private String title;
    private String _artist;
    public String _uri;
    private int _votes;

    public QueueItem(String uri, int votes)
    {
        _uri = uri;
        _votes = votes;
    }

    public String getTitle()
    {
        return _uri;
    }

    public String getAuthor()
    {
         return "STR_TASS_AUTHOR_NAME";
    }

    private void getTrackInfo()
    {
        //resolve from the server here
    }
}
