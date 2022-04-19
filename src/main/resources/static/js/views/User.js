export default function User(props) {
return `<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>User Info</title>
</head>
<body>
<h1>Omegalol</h1>
<form id="User-info"></form>
<labe for="username"></labe>
<h5>Username</h5>
<input type="text" name="username" id="username" value="${props.users.username}" disabled>
<label for="password"></label>
<h5>password</h5>
<input type="text" name="password" id="password" value="${props.users.password}">

</body>
</html>`;

}