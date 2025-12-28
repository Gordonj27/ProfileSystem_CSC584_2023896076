package com.myprofile.servlet;

import com.myprofile.model.ProfileBean; 
import com.myprofile.util.DBConnection;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {

   @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    String studentID = request.getParameter("studentID");
    
    ProfileBean profile = new ProfileBean();
    profile.setName(request.getParameter("name"));
    profile.setStudentID(studentID);
    profile.setProgram(request.getParameter("program"));
    profile.setEmail(request.getParameter("email"));
    profile.setHobbies(request.getParameter("hobbies"));
    profile.setSelfIntro(request.getParameter("selfIntro"));

    String message;
    boolean isDuplicate = false;

    try (Connection con = DBConnection.getConnection()) {
        
        String checkSql = "SELECT studentID FROM profiles WHERE studentID = ?";
        PreparedStatement checkPs = con.prepareStatement(checkSql);
        checkPs.setString(1, studentID);
        ResultSet rs = checkPs.executeQuery();

        if (rs.next()) {
            
            message = "Error: Student ID " + studentID + " already exists!";
            isDuplicate = true;
        } else {
            String sql = "INSERT INTO profiles (name, studentID, program, email, hobbies, selfIntro) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, profile.getName());
            ps.setString(2, profile.getStudentID());
            ps.setString(3, profile.getProgram());
            ps.setString(4, profile.getEmail());
            ps.setString(5, profile.getHobbies());
            ps.setString(6, profile.getSelfIntro());
            
            ps.executeUpdate();
            message = "Profile saved successfully!";
        }
    } catch (Exception e) {
        message = "Database error: " + e.getMessage();
    }

    request.setAttribute("profile", profile);
    request.setAttribute("saveMessage", message);
    request.setAttribute("isDuplicate", isDuplicate); 
    request.getRequestDispatcher("profile.jsp").forward(request, response);
}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        ArrayList<ProfileBean> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM profiles WHERE studentID LIKE ? OR name LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProfileBean p = new ProfileBean();
                p.setName(rs.getString("name"));
                p.setStudentID(rs.getString("studentID"));
                p.setProgram(rs.getString("program"));
                list.add(p);
            }
        } catch (Exception e) { e.printStackTrace(); }
        request.setAttribute("profileList", list);
        request.getRequestDispatcher("viewProfiles.jsp").forward(request, response);
    }
}