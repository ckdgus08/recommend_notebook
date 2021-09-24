window.onload = function () {

    let main_block = document.getElementsByClassName("slide__main-block");

    main_block.item(0).classList.remove('hidden');
    main_block.item(1).classList.remove('hidden');

    let choose_button = document.getElementsByClassName("move_button__type choose");

    for (const chooseButtonElement of choose_button) {

        chooseButtonElement.onclick = function () {
            let attribute = this.getAttribute("data-slide-index");
            let main_block_show = document.getElementsByClassName("slide__main-block");
            for (const mainBlockShowElement of main_block_show) {
                if (!mainBlockShowElement.classList.contains('hidden') && mainBlockShowElement.getAttribute("data-slide-index") !== attribute) {
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
        url: '/v1/api/review?model=' + model + '&majorType=' + majorType + '&page=' + (page - 1),
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

