<body>
    <link rel="stylesheet" href="css/style.css">
    <div class="container"> <h2>? ${saveMessage}</h2>

        <div class="profile-card"> <p><strong>Name:</strong> ${profile.name}</p>
            <p><strong>Student ID:</strong> ${profile.studentID}</p>
            <p><strong>Program:</strong> ${profile.program}</p>
            <p><strong>Email:</strong> ${profile.email}</p>
            <hr>
            <p><strong>Hobbies:</strong> ${profile.hobbies}</p>
            <p><strong>Self-Introduction:</strong></p>
            <blockquote class="intro-quote">${profile.selfIntro}</blockquote>
        </div>
        
        <p class="back-link">
            <a href="profileForm.html">? Register Another</a> | 
            <a href="ProfileServlet?keyword=">View All Profiles</a>
        </p>
    </div>
</body>