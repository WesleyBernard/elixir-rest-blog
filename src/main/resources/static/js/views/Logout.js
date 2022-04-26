import createView from "../createView.js";
export default function Logout(props) {
    localStorage.clear()
    createView("/")
}