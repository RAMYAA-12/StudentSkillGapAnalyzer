package service;

import util.DBConnection;

import java.sql.*;
import java.util.Scanner;

public class SkillGapService {

    Scanner sc = new Scanner(System.in);

    // Add Student
    public void addStudent() {

        try {

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();

            sc.nextLine();

            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            Connection con = DBConnection.getConnection();

            String query =
                    "INSERT INTO Students(student_id,name) VALUES (?,?)";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, id);
            ps.setString(2, name);

            ps.executeUpdate();

            System.out.println("Student Added Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add Skill
    public void addSkill() {

        try {

            System.out.print("Enter Skill ID: ");
            int id = sc.nextInt();

            sc.nextLine();

            System.out.print("Enter Skill Name: ");
            String skill = sc.nextLine();

            Connection con = DBConnection.getConnection();

            String query =
                    "INSERT INTO Skills(skill_id,skill_name) VALUES (?,?)";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, id);
            ps.setString(2, skill);

            ps.executeUpdate();

            System.out.println("Skill Added Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add Company
    public void addCompany() {

        try {

            System.out.print("Enter Company ID: ");
            int id = sc.nextInt();

            sc.nextLine();

            System.out.print("Enter Company Name: ");
            String company = sc.nextLine();

            Connection con = DBConnection.getConnection();

            String query =
                    "INSERT INTO Companies(company_id,company_name) VALUES (?,?)";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, id);
            ps.setString(2, company);

            ps.executeUpdate();

            System.out.println("Company Added Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Analyze Skill Gap
    public void analyzeSkillGap() {

        try {

            Connection con = DBConnection.getConnection();

            String query =
                    """
                    SELECT skill_name
                    FROM Skills
                    WHERE skill_id IN
                    (
                        SELECT skill_id
                        FROM CompanyRequirements
                        WHERE company_id = 1
                    )
                    AND skill_id NOT IN
                    (
                        SELECT skill_id
                        FROM StudentSkills
                        WHERE student_id = 1
                    )
                    """;

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            System.out.println("\nMissing Skills:");

            while (rs.next()) {
                System.out.println(rs.getString("skill_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Match Percentage
    public void viewMatchPercentage() {

        try {

            Connection con = DBConnection.getConnection();

            String totalQuery =
                    """
                    SELECT COUNT(*)
                    FROM CompanyRequirements
                    WHERE company_id = 1
                    """;

            Statement st1 = con.createStatement();

            ResultSet rs1 =
                    st1.executeQuery(totalQuery);

            int totalSkills = 0;

            if (rs1.next()) {
                totalSkills = rs1.getInt(1);
            }

            String matchQuery =
                    """
                    SELECT COUNT(*)
                    FROM CompanyRequirements
                    WHERE company_id = 1
                    AND skill_id IN
                    (
                        SELECT skill_id
                        FROM StudentSkills
                        WHERE student_id = 1
                    )
                    """;

            Statement st2 = con.createStatement();

            ResultSet rs2 =
                    st2.executeQuery(matchQuery);

            int matchedSkills = 0;

            if (rs2.next()) {
                matchedSkills = rs2.getInt(1);
            }

            double percentage =
                    ((double) matchedSkills / totalSkills) * 100;

            System.out.printf(
                    "\nMatch Percentage: %.2f%%\n",
                    percentage
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Learning Recommendations
    public void getRecommendations() {

        try {

            Connection con = DBConnection.getConnection();

            String query =
                    """
                    SELECT recommendation
                    FROM LearningResources
                    WHERE skill_name IN
                    (
                        SELECT skill_name
                        FROM Skills
                        WHERE skill_id IN
                        (
                            SELECT skill_id
                            FROM CompanyRequirements
                            WHERE company_id = 1
                        )
                        AND skill_id NOT IN
                        (
                            SELECT skill_id
                            FROM StudentSkills
                            WHERE student_id = 1
                        )
                    )
                    """;

            Statement st =
                    con.createStatement();

            ResultSet rs =
                    st.executeQuery(query);

            System.out.println(
                    "\nRecommended Learning:"
            );

            while (rs.next()) {

                System.out.println(
                        rs.getString("recommendation")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
