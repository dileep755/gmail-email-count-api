<!DOCTYPE html>
<html>
<head>
    <title>Gmail OAuth Login</title>
    <link rel="stylesheet" 
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>

<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow p-4 mx-auto" style="max-width: 450px;">
        <h3 class="text-center mb-3">Login to Gmail (OAuth2)</h3>

        <form action="/auth/login" method="get">
            <label class="form-label">Enter Gmail Account</label>
            <input name="email" class="form-control" placeholder="your-email@gmail.com" required />

            <button type="submit" class="btn btn-danger w-100 mt-3">
                <i class="bi bi-google"></i> Login with Google
            </button>
        </form>
    </div>
</div>

</body>
</html>
