import {isLoggidIn} from "../../auth.js";
export default function Navbar(props) {
    if (isLoggidIn()) {
        return `
        <nav>
            <a href="/" data-link>Home</a>
            <a href="/posts" data-link>Posts</a>
            <a href="/about" data-link>About</a>
            <a href="/login" data-link>Login</a>
            <a href="/register" data-link>Register</a>
            <a href="/user" data-link>User</a>
            <a href="/Logout" data-link>Logout</a>
        </nav>
    `;
    } else {
        return `
        <nav>
            <a href="/" data-link>Home</a>
            <a href="/posts" data-link>Posts</a>
            <a href="/about" data-link>About</a>
            <a href="/login" data-link>Login</a>
            <a href="/register" data-link>Register</a>
            <a href="/user" data-link>User</a>
        </nav>
    `;
    }
}