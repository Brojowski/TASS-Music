package com.tass;

/**
 * Created by alex on 6/25/16.
 */
public interface CreateGroupInterface {
    void tryLoginToSpotify(String groupname);
    void sessionCallback(boolean success, boolean isCreator);

}
