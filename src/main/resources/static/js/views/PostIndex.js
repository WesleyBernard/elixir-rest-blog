const baseUri = "http://localhost:8080/api/posts";
export default function PostIndex(props) {

    return `
        <header>
            <h1>Posts Page</h1>
        </header>
        <main>
            <div>
                ${props.posts.map(post => `<h3>${post.title}</h3> <p class="content">${post.content}</p> <button class="edit" id=${post.id}> edit </button> <button class="delete" id=${post.id}> delete </button>`).join('')}   
            </div>
            
            <br>
            <button class="addPost"> add post</button>
            
        </main>
    `;
}
const deletePost = function () {
    $(".delete").click(function (e) {
        fetch(baseUri)
            .then(response => response.json())
            .then(data => {
                console.log(data)
                data.forEach(post => {
                    if (post.id == this.id) {
                        data.pop()
                        console.log(data)
                    }
                })
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
        })
    })
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
            headers: { 'Content-Type': 'application/json' }
        })
    })
}

export function listeners (){
    deletePost();
    editPost();
    createPost()
}



