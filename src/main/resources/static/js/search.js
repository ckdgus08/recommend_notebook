let now = [0, 1];

window.onload = function () {

    let main_block = document.getElementsByClassName("slide__main-block");

    main_block.item(0).classList.remove('hidden');
    main_block.item(1).classList.remove('hidden');

    let choose_button = document.getElementsByClassName("move_button__type choose");

    for (const chooseButtonElement of choose_button) {

        chooseButtonElement.onclick = function () {
            let attribute = this.getAttribute("data-slide-index");
            let index = now.indexOf(parseInt(attribute));

            let main_block_show = document.getElementsByClassName("slide__main-block");
            for (const mainBlockShowElement of main_block_show) {
                if (!mainBlockShowElement.classList.contains('hidden') && parseInt(mainBlockShowElement.getAttribute("data-slide-index")) !== index) {
                    mainBlockShowElement.classList.add('hidden');

                    let innerText = document.getElementById("main_progress").innerText;
                    document.querySelector("div[data-slide-index='" + innerText + "']").classList.remove('hidden');
                    document.getElementById("main_progress").innerText = (parseInt(innerText) + 1).toString();
                    getReview(innerText, 1);
                    break;
                }
            }

        }

    }

    getReview(0, 1);
    getReview(1, 1);

}

function getReview(index, page) {
    let mainBlock = document.querySelector("div[data-slide-index=\"" + index + "\"].slide__main-block");
    let model = mainBlock.getElementsByClassName("radio__label")[1].innerHTML;
    let review = mainBlock.getElementsByClassName("review")[0];
    let majorType = "테스트";

    $.ajax({
        method: 'GET',
        url: 'api/review?model=' + model + '&majorType=' + majorType + '&page=' + (page - 1),
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
                    let deep_word = "";
                    for (const keyword of keywords) {
                        deep_word += keyword + "\n";
                    }
                    list.innerHTML += "<div class='review_content_deep'>" + deep_word + "</div>"

                    review.append(list);
                }
            }
        }
    })
}

function deep(str) {
    let temp_index = 0;
    let keywords = [];
    $.ajax({
        async: false,
        method: 'GET',
        url: 'http://localhost:8000/api/deep/' + str,
        dataType: 'json',
        success: function (data) {
            if (data.length > 0) {
                for (let element of data) {
                    keywords[temp_index] = element;
                    temp_index++;
                }
            }
        }
    })
    return keywords;
}

