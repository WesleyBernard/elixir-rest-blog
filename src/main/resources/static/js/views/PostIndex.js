import createView from "../createView.js";
import {getHeaders} from "../auth.js";

const baseUri = "http://localhost:8080/api/posts";
export default function PostIndex(props) {
    console.log(props)

    return `
        <header>
            <h1>Posts Page</h1>
        </header>
        <main>
            <div>
                ${props.posts.map(post => `<h3>${post.title}</h3> <p class="content">${post.content}</p> <div> ${getCategories(post.categories)} </div> <div> ${post.author.username} </div> <button class="edit" id=${post.id}> edit </button> <button class="delete" id=${post.id}> delete </button>`).join('')}   
            </div>
            
            <br>
            <button class="addPost"> add post</button>
            
        </main>
    `;
}
const deletePost = function () {
    $(".delete").click(function (e) {
        fetch(baseUri + "/" +this.id, {
            method: "delete",
            headers: getHeaders()
        }).then(res => {
        console.log(res.status)
        createView("/posts")
    }).catch(error => {
        console.log(error);
        createView("/posts");
    })
    })
}

const editPost = function () {
    $(".edit").click(function (e) {
        let ppost = {
            title: "asdasdkj",
            content: "waoid"
        }
        fetch(baseUri + "/" + this.id, {
            method: "put",
            body: JSON.stringify(ppost),
            headers: { 'Content-Type': 'application/json' }
        }).then(res => {
        console.log(res.status)
        createView("/posts")
    }).catch(error => {
        console.log(error);
        createView("/posts");
    })
    })
}

const getCategories = function (categoryArray) {
    let html = `<div>`
    for (let i = 0; i < categoryArray.length; i++) {
        html +=`<span> ${categoryArray[i].name} </span>`
    }
    html += `</div>`;
    return html
}

const createPost = function () {
    $(".addPost").click(function (e) {
        let ppost = {
            title: "Hello World",
            content: "Goodbye World",
            id: 0
        }
        fetch(baseUri, {
            method: "post",
            body: JSON.stringify(ppost),
            headers: getHeaders()
        }).then(res => {
            console.log(res.status)
            createView("/posts")
        }).catch(error => {
            console.log(error);
            createView("/posts");
        })
    })
}

export function listeners (){
    deletePost();
    editPost();
    createPost()
}



