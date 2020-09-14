'use strict';
function search() {
    let value = document.getElementById("search-input").value;
    if(value.length === 0){
        return;
    }else{
        window.location.href = '/search/'+value;
    }

};

let input = document.getElementById("search-input");
input.addEventListener("keyup",function (event) {
    if(event.keyCode === 13){
        event.preventDefault();
        document.getElementById("btn-search").click();
    }

});

function deleteUser(form) {
    let data = new FormData(form);
    console.log(data.get("userId"))
    fetch("/delete_user", {
        method: "POST",
        body: data
    }).then(data => {
        window.location.href = "/users"
    });


}


