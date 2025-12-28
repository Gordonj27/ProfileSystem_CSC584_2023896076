<head>
    <link rel="stylesheet" href="css/style.css"> </head>
<body>
    <div class="container"> <h2>Submission Status</h2>
        
        <p style="text-align:center; font-weight:bold;">${saveMessage}</p>

        <div class="profile-card"> <p><strong>Name:</strong> ${profile.name}</p>
            <p><strong>Student ID:</strong> ${profile.studentID}</p>
            <p><strong>Program:</strong> ${profile.program}</p>
            <hr>
            <p><strong>Self-Introduction:</strong></p>
            <blockquote class="intro-quote">${profile.selfIntro}</blockquote> </div>

        <div class="back-link">
            <a href="profileForm.html">? Register Another</a>
            <a href="ProfileServlet?keyword=">View All Profiles</a>
        </div>
    </div>
</body>