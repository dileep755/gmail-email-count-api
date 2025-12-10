<%@ page session="true" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Email Counter</title>
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
    />
  </head>

  <body class="bg-white">
    <div class="container mt-5">
      <input type="hidden" id="loggedUser" value="${sessionScope.userEmail}" />

      <div class="card shadow p-4 mx-auto" style="max-width: 600px">
        <h3 class="text-center mb-3">Count Emails</h3>

        <p><strong>Logged in as:</strong> ${sessionScope.userEmail}</p>

        <div class="mb-3">
          <input
            id="sender"
            class="form-control"
            placeholder="Enter sender email..."
          />
        </div>

        <!-- <button class="btn btn-primary w-100" onclick="countEmails()">Count Emails</button> -->
        <button type="button" onclick="getEmailSubjects()">Get Subjects</button>

        <p id="result" class="mt-3 fw-bold text-success"></p>
      </div>
    </div>

    <script>
      function countEmails() {
        let sender = document.getElementById("sender").value;
        let loginEmail = document.getElementById("loggedUser").value;
        //console.log("login emial",loginEmail);

        fetch("/gmail/count?loginEmail=" + loginEmail + "&sender=" + sender)
          .then((r) => r.json())
          .then((data) => {
            document.getElementById("result").innerHTML =
              "Found" + data.count + " emails from <b>" + data.sender + "</b>";
          });
      }

      function getEmailSubjects() {
    	    let sender = document.getElementById("sender").value;
    	    let loginEmail = document.getElementById("loggedUser").value;

    	    fetch("/gmail/subjects?loginEmail=" + loginEmail + "&sender=" + sender)
    	        .then((r) => r.json())
    	        .then((data) => {

    	            let html = "<h3>Emails from <b>" + data.sender + "</b></h3>" +
    	                       "<p>Logged-in Email: <b>" + data.email + "</b></p>" +
    	                       "<p>Total: " + data.subjects.length + " email(s)</p>" +
    	                       "<ul>";

    	            data.subjects.forEach((sub) => {
    	                html += "<li>" + sub + "</li>";
    	            });

    	            html += "</ul>";

    	            document.getElementById("result").innerHTML = html;
    	        });
    	}

    </script>
  </body>
</html>
