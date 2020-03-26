package com.example.sql_example.repository;

public class SQLScripts {
    public static String initDbUserScript() {
        return "create table user(" +
                "id integer primary key autoincrement," +
                "name text not null," +
                "password text not null" +
                ");";
    }


    public static String initDbFriendshipScript() {
        return "create table friendship(" +
                "id integer primary key autoincrement," +
                "firstUserId integer," +
                "secondUserId integer," +
                "isConfirm boolean " +
                ");";
    }

    public static String insertUserScript(String name, String password) {
        String _name = "\"" + name + "\"";
        String _password = "\"" + password + "\"";

        return "insert into user" +
                "(name, password)" +
                "values" +
                "(" + _name + "," + _password +
                ");";
    }

    public static String getUserScript(String name, String password) {
        String _name = "\"" + name + "\"";
        String _password = "\"" + password + "\"";

        return "select * from user" +
                " where name = " + _name +
                " and password = " + _password +
                ";";
    }

    public static String getAllUsersScript() {
        return "select * from user;";
    }

    public static String getAllUsersScript(int limit) {
        return "select * from user" +
                " limit " + limit +
                ";";
    }

    public static String insertFriendshipScript(int firstUserId, int secondUserId) {

        return "insert into friendship" +
                " (firstUserId, secondUserId, isConfirm)" +
                " values" +
                "(" + firstUserId + "," + secondUserId + ", false" +
                ");";
    }


    public static String updateFriendshipScript(int firstUserId, int secondUserId) {
        return "UPDATE friendship set isсonfirm = 1" +
                " WHERE firstuserid = " + firstUserId +
                " and seconduserid = " + secondUserId +
                ";";
    }

    public static String deleteFriendshipScript(int firstUserId, int secondUserId) {
        return "DELETE FROM friendship "+
                "where firstuserid = " + firstUserId +
                " and seconduserid = " + secondUserId +
                ";";
    }

    public static String getOutgoingFriendshipScript(int firstUserId) {
        return "SELECT firstuserid, seconduserid, isconfirm from friendship " +
                "where firstuserid = " + firstUserId +
                ";";
    }

    public static String getIncomingFriendshipScript(int secondUserId) {
        return "SELECT firstuserid, seconduserid, isconfirm from friendship " +
                "where secondUserId = " + secondUserId +
                ";";
    }

    public static String checkOutgoingFriendshipScript(int firstUserId,int secondUserId) {
            return "SELECT isconfirm from friendship " +
                    "where firstuserid = " + firstUserId +
                    "and secondUserId = " + secondUserId +
                    ";";
        }

        public static String checkIncomingFriendshipScript(int firstUserId,int secondUserId) {
            return "SELECT isconfirm from friendship " +
                    "where firstuserid = " + secondUserId +
                    "and secondUserId = " + firstUserId +
                    ";";
        }

}