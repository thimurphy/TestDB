package com.company;

import java.sql.*;

public class Main {

    public static final String DB_NAME = "testjava.db";
    public static final String  CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\v-thmur\\Documents\\IntelliJ\\TestDB\\"
            + DB_NAME;
    public static final String TABLE_CONTACTS = "contacts";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    public static void main(String[] args) {


//        try(Connection conn = DriverManager.getConnection(
//                "jdbc:sqlite:C:\\Users\\v-thmur\\Documents\\IntelliJ\\TestDB\\testjava.db");
//            Statement statement = conn.createStatement()){
//
//            statement.execute("CREATE TABLE contacts (name TEXT, phone INTEGER, email TEXT)");

        try{
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
//            conn.setAutoCommit(false);
            Statement statement = conn.createStatement();

            statement.execute("DROP TABLE IF EXISTS " +  TABLE_CONTACTS);

            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS
                    + " (" + COLUMN_NAME + " TEXT, " + COLUMN_PHONE + " INTEGER, "
                    + COLUMN_EMAIL + " TEXT)");



            //CRUD = CREATE , READ, UPDATE AND DELETE

//            statement.execute("INSERT INTO contacts (name, phone, email)" +
//                        "VALUES ('Joe', 1234561, 'joe@anywhere.com')");

            statement.execute("INSERT INTO " + TABLE_CONTACTS + " (" + COLUMN_NAME + ", "
                    + COLUMN_PHONE + ", " + COLUMN_EMAIL + ") "
                    + "VALUES ('Joe', 1234561, 'joe@anywhere.com')");
//
//            statement.execute("INSERT INTO contacts (name, phone, email)" +
//                    "VALUES ('Jane', 444222, 'jane@anywhere.com')");

            statement.execute("INSERT INTO " + TABLE_CONTACTS + " (" + COLUMN_NAME + ", "
                    + COLUMN_PHONE + ", " + COLUMN_EMAIL + ") "
                    + "VALUES ('Jane', 444222, 'jane@anywhere.com')");
//
//            statement.execute("INSERT INTO contacts (name, phone, email)" +
//                    "VALUES ('Thiago', 787878, 'thi@anywhere.com')");

            statement.execute("INSERT INTO " + TABLE_CONTACTS + " (" + COLUMN_NAME + ", "
                    + COLUMN_PHONE + ", " + COLUMN_EMAIL + ") "
                    + "VALUES ('Thiago', 787878, 'thi@anywhere.com')");

            //Using methods
            insertContact(statement,"Caomhan", 12332100, "cm@email.com");
            insertContact(statement,"Camila", 25254555, "camila@email.com");
            insertContact(statement,"Leandro", 66666666, "leo@anywhere.com");

//            statement.execute("UPDATE contacts SET phone=555555 WHERE name='Jane'");
            statement.execute("UPDATE " + TABLE_CONTACTS + " SET " + COLUMN_PHONE
                    + "= 555555 WHERE " + COLUMN_NAME + "='Jane'");


//            statement.execute("DELETE FROM contacts WHERE name='Joe'");
            statement.execute("DELETE FROM " + TABLE_CONTACTS + " WHERE " + COLUMN_NAME + "='Joe'");

//            statement.execute("SELECT * FROM contacts");
//            ResultSet results = statement.getResultSet();
            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS);

            while(results.next()){
                System.out.println(results.getString(COLUMN_NAME) + " "
                        + results.getInt(COLUMN_PHONE) + " "
                        + results.getString(COLUMN_EMAIL));
            }

            results.close();

            statement.close();
            conn.close();

        }catch (SQLException e){
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static void insertContact(Statement statement, String name, int phone, String email ) throws SQLException{

        statement.execute("INSERT INTO " + TABLE_CONTACTS + " (" + COLUMN_NAME + ", "
                + COLUMN_PHONE + ", " + COLUMN_EMAIL + ") "
                + "VALUES ('" +  name + "', " + phone + ", '" + email + "')");
    }

}
