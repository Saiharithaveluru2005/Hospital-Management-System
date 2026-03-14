package com.learnJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {

    private Connection connection;

    public Doctor(Connection connection) {
        this.connection = connection;
    }

    public void viewDoctors() {

        String query = "SELECT * FROM doctors";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultset = preparedStatement.executeQuery();

            System.out.println("Doctors: ");
            System.out.println("+-------------+-----------------+-------------------+");
            System.out.println("| Doctor ID   | Name            | Specialization    |");
            System.out.println("+-------------+-----------------+-------------------+");

            while (resultset.next()) {

                int id = resultset.getInt("doctor_id");  // ✅ Correct
                String name = resultset.getString("name");
                String specialization = resultset.getString("specialization");  // ✅ Correct spelling

                System.out.printf("|%-13d | %-15s | %-17s |\n",
                        id, name, specialization);
            }

            System.out.println("+-------------+-----------------+-------------------+");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getDoctorByID(int id) {

        String query = "SELECT * FROM doctors WHERE doctor_id = ?";  // ✅ Correct

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultset = preparedStatement.executeQuery();

            return resultset.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
