window.onload = function () {

    document.getElementById("submit").onclick = function () {
        write_review();
    }
}

function write_review() {

    fetch('http://localhost:8080/v1/api/review/',
        {
            method: 'POST',
            body: JSON.stringify({
                'userId': document.getElementById("userId").value,
                'major': document.getElementById("major").value,
                'model': document.getElementById("model").value,
                'title': document.getElementById("title").value,
                'content': document.getElementById("content").value
            }),
            headers: {
                'Content-Type': 'application/json; charset=UTF-8'
            }
        })
        .then(res => res.json())
        .then(res => console.log('Success:', JSON.stringify(res)))
        .catch(error => console.error(error))
}
