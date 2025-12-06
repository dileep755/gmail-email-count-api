📧 Gmail OAuth Email Counter – Spring Boot Project

This project implements a Spring Boot-based web application that integrates with Gmail API using Google OAuth2 authentication and allows authenticated users to search and count emails from a specific sender.

The project includes:
✔ Google OAuth Login
✔ Session-based authentication (no username/password)
✔ Gmail API integration
✔ JSP views (Bootstrap UI)
✔ Spring Security (custom session authentication)
✔ Email search and count
✔ Fully responsive Bootstrap UI

🚀 Features
🔐 OAuth2 Authentication
Users authenticate using their Gmail account
Google’s OAuth2 flow is used (no password stored)
Access tokens stored server-side

📬 Gmail API Integration
Uses Google Gmail API (REST)
Fetches emails using query "from:<email>"
🛡 Session-Based Security
Spring Security integrated
Custom session authentication filter
/gmail/home protected behind OAuth login

🎨 UI / UX
Responsive Bootstrap JSP pages
Redirect page for smooth OAuth experience
Error handling and clean UI

Project Structure
src/
 ├─ main/
 │   ├─ java/com/example/gmailapi/
 │   │      ├─ controller/
 │   │      │     ├─ LoginController.java
 │   │      │     ├─ GmailController.java
 │   │      ├─ config/
 │   │      │     ├─ SecurityConfig.java
 │   │      │     └─ SessionAuthFilter.java
 │   │      ├─ service/
 │   │      │     ├─ GoogleOAuthService.java
 │   │      │     └─ GmailService.java
 │   │      └─ util/
 │   │            └─ GoogleAuthUtil.java
 │   │      
 │   ├─ webapp/
 │   │    └─ WEB-INF/views/
 │   │           ├─ login.jsp
 │   │           ├─ redirect-page.jsp
 │   │           └─ email-count.jsp
 │   │
 └─ resources/
       ├─ application.properties
       └─ static (optional)
  
🛠 Technologies Used
Technology	Purpose
Spring Boot 3+	Backend Framework
Spring Security	Session-based auth protection
Google OAuth2 Client	Handle login with Google
Gmail API v1	Email search/count
JSP + Bootstrap 5	UI pages
Java 17	Language
Maven	Build tool
🔧 Setup Instructions
1️⃣ Create Google OAuth Credentials

Go to Google Cloud Console → APIs & Services → Credentials
Create:

✔ OAuth Client ID (Web App)
✔ Add Authorized Redirect URI:
http://localhost:8080/auth/callback

Enable Gmail API:
APIs & Services → Library → Gmail API → ENABLE
Add yourself as a Test User if the OAuth app is in “Testing” mode.
Update application.properties
spring.mvc.view.prefix=/WEB-INF/views/
spring.mvc.view.suffix=.jsp

spring.web.resources.add-mappings=true

google.client.id=YOUR_CLIENT_ID
google.client.secret=YOUR_CLIENT_SECRET
google.redirect.uri=http://localhost:8080/auth/callback
google.scopes=https://www.googleapis.com/auth/gmail.readonly

3️⃣ Run the Project
mvn spring-boot:run


Application will start at:

http://localhost:8080/

✨ How It Works
Step 1 → Login Page

User enters Gmail → clicks "Login with Google"

Step 2 → Google OAuth

Redirects to Google login page
User grants Gmail read permission

Step 3 → Callback Handler

Stores OAuth token
Stores session attribute userEmail
Redirects to /gmail/home

Step 4 → Email Count Page

User enters sender email
System calls Gmail API and returns email count

🔐 Security Flow

✔ /
✔ /auth/login
✔ /auth/callback
are public.

Everything else (like /gmail/home) is protected by:

🔸 Custom SessionAuthFilter

It marks user authenticated if:

session.getAttribute("userEmail") != null


Thus enabling full Spring Security integration.

📥 API Endpoint
Method	Endpoint	Description
GET	/gmail/count?loginEmail=&sender=	Returns JSON count of emails
Example response
{
  "email": "user@gmail.com",
  "sender": "abc@example.com",
  "count": 12
}

🖥 UI Pages
✔ login.jsp

Google sign-in prompt

✔ redirect-page.jsp

Auto redirects to Google OAuth authorization URL

✔ email-count.jsp

Form to count emails
Displays results dynamically via JavaScript fetch API

🧪 Testing the Flow

Start the application

Navigate to:

http://localhost:8080/


Enter Gmail → login

Redirected automatically to Google

Authenticated → redirected to /gmail/home

Enter sender email

Get count ✔

🧩 Known Issues / Notes

Gmail API requires the user to be added as a Test User unless app is Verified

Tokens are stored in-memory (for demo). For production, use DB.

Session authentication does not survive restarts (by design).

📜 License

This project is created as an assignment/demo and may be used freely for learning and development.

🙌 Author

Dileep Shukla
Java & Spring Boot Developer
