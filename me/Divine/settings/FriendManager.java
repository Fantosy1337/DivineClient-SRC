package me.Divine.settings;

import java.util.*;

public class FriendManager
{
    public static ArrayList friends;
    
    public static Friend getFriend(final String string) {
        for (final Object friendw : FriendManager.friends) {
            final Friend friend = (Friend)friendw;
            if (!friend.name.equalsIgnoreCase(string)) {
                continue;
            }
            return friend;
        }
        return null;
    }
    
    public static void removeFriend(final String string) {
        if (getFriend(string) != null) {
            FriendManager.friends.remove(getFriend(string));
        }
    }
    
    public static boolean isFriend(final String string) {
        return getFriend(string) != null;
    }
    
    public static void addFriend(final String string, final String string2) {
        FriendManager.friends.add(new Friend(string, string2));
    }
    
    static {
        FriendManager.friends = new ArrayList();
    }
}
