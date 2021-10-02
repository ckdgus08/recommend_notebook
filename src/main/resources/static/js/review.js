window.onload = function () {

    document.getElementById("submit").onclick = function () {
        write_review();
    }

    let ttt = document.getElementsByClassName("ttt");
    let ggg = document.getElementsByClassName("ggg");

    for (const t of ttt) {
        t.onclick = function () {
            document.getElementById("major").value = t.innerText;
            for (const a of ttt) {
                a.classList.add("hidden")
            }
        }
    }
    for (const t of ggg) {
        t.onclick = function () {
            document.getElementById("model").value = t.innerText;
            getReview()
            for (const a of ggg) {
                a.classList.add("hidden")
            }
        }
    }

    document.getElementById("model").onsubmit = function () {
        getReview()
    }
}

function getReview() {

    let model = document.getElementById("model").value
    let review = document.getElementById("review")
    $.ajax({
        method: 'GET',
        url: '/v1/api/review?model=' + model,
        dataType: 'json',
        success: function (data) {
            if (data.length > 0) {
                review.innerHTML = "";
                for (let i in data) {
                    let keywords = deep(data[i].content)
                    let list = document.createElement("li");
                    list.innerHTML = "<div class='review_title'>" + data[i].title + "</div>"
                    list.innerHTML += "<div class='review_detail'>" + data[i].detail + "</div>"
                    list.innerHTML += "<div class='review_content'>" + data[i].content + "</div>"
                    let review_str = "<div class='review_content_deep'>"
                    for (const keyword of keywords) {
                        if (keyword.length === 0) continue
                        review_str += "<span class='review_deep_line'><span class='review_content_deep_prefix'>" + keyword[0] + "\t" + "</span><span>" + keyword[1] + "</span></span>";
                    }
                    review_str += "</div>"
                    list.innerHTML += review_str
                    review.append(list);
                }
            }
        }
    })

}

function filter() {
    let value = document.getElementById("model").value;

    let items = document.getElementsByClassName("ggg");

    for (let item of items) {
        if (item.innerHTML.indexOf(value) > -1 || item.innerHTML.toLowerCase().indexOf(value) > -1) {
            item.classList.remove("hidden")
        } else {
            item.classList.add("hidden")
        }
    }
}

function filterMajor() {
    let value = document.getElementById("major").value;

    let items = document.getElementsByClassName("ttt");

    for (let item of items) {
        if (item.innerHTML.indexOf(value) > -1 || item.innerHTML.toLowerCase().indexOf(value) > -1) {
            item.classList.remove("hidden")
        } else {
            item.classList.add("hidden")
        }
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

function deep(str) {
    let temp_index = 0;
    let keywords = [[], [], [], [], []];
    $.ajax({
        async: false,
        method: 'GET',
        url: 'http://localhost:8000/api/deep/' + str,
        dataType: 'json',
        success: function (data) {
            if (data.length > 0) {
                for (let element of data) {
                    let split = element.toString().split(',');
                    console.log(split)
                    if (split.length <= 1) continue
                    keywords[temp_index][0] = split[0];
                    keywords[temp_index][1] = split[1];
                    temp_index++;
                }
            }
        }
    })
    return keywords;
}
