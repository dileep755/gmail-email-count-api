<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Redirecting…</title>
    <meta http-equiv="refresh" content="1;URL=${redirectUrl}" />

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">

    <style>
        .center-box {
            margin-top: 15%;
        }
    </style>
</head>

<body class="bg-light">

<div class="container center-box text-center">

    <div class="card shadow p-4 mx-auto" style="max-width: 450px;">
        <h4 class="mb-3">Redirecting to Google Authentication…</h4>

        <p class="text-muted">
            Please wait, or click the button below if not redirected automatically.
        </p>

        <a href="${redirectUrl}" class="btn btn-primary w-100">
            Continue to Google Login
        </a>
    </div>

</div>

</body>
</html>
