<%@page import="java.util.ArrayList, com.myprofile.model.ProfileBean"%>
<html>
<head>
    <title>Database Profiles</title>
    <link rel="stylesheet" href="css/style.css"> 
</head>
<body>
    <div class="container">
        <h2>Registered Student Profiles</h2>
        
        <table border="1" cellpadding="5">
            <tr>
                <th>Name</th>
                <th>Student ID</th>
                <th>Program</th>
            </tr>
            <% 
                ArrayList<ProfileBean> list = (ArrayList<ProfileBean>) request.getAttribute("profileList");
                if(list != null && !list.isEmpty()) {
                    for(ProfileBean p : list) {
            %>
            <tr>
                <td><%= p.getName() %></td>
                <td><%= p.getStudentID() %></td>
                <td><%= p.getProgram() %></td>
            </tr>
            <% 
                    }
                } else {
            %>
            <tr><td colspan="3">No profiles found matching that search.</td></tr>
            <% } %>
        </table>
        
        <br>
        <div class="back-link">
            <a href="profileForm.html">Back to Registration</a>
        </div>
    </div>
</body>
</html>