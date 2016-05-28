package ait.servlet.utils;

import ait.entity.User;
import ait.utils.ObjectUtils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by suomiy on 5/27/16.
 */
public enum OnlineUsers {
    ;
    private static final Map<User, String> users = new HashMap<>();

    public static void addUser(User user) {
        Random random = new SecureRandom();
        String token = new BigInteger(130, random).toString(32);
        users.putIfAbsent(user, token);
    }

    public static void removeUser(User user) {
        users.remove(user);
    }

    public static List<User> getOnlineUsers() {
        return users.keySet().stream().collect(Collectors.toList());
    }

    public static boolean isUserOnline(User user) {
        return users.containsKey(user);
    }

    public static String getUserToken(User user) {
        return users.get(user);
    }

    public static boolean isTokenValid(String token) {
        return !ObjectUtils.isEmpty(token) && users.values().contains(token);
    }
}
