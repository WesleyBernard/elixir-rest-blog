import {getHeaders} from "../auth.js";

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
<input type="text" name="username" id="username" value="${props.user.username}" disabled>
<label for="password"></label>
<h5>password</h5>
<input type="text" name="password" id="password" value="${props.user.password}" disabled>
<label for="email"></label>
<h5>email</h5>
<input type="text", name="email", id="email", value="${props.user.email}" disabled>
<label for="changePassword"></label>
<h5>Please enter the new password below</h5>
<input type="password" name="changePassword" id="changePassword" placeholder="new password here">

<button id="changePasswordBtn"> submit</button>

</body>
</html>`;

}

const changePassword = function (e) {
    $("#changePasswordBtn").click(function (e) {
        fetch("http://localhost:8080/api/users/updatepassword?oldPassword=" + $("#password").val() + "&newPassword=" + $("#changePassword").val(), {
            method: "PUT",
            headers: getHeaders()
        })
    })
}

export function updates() {
    changePassword()
}