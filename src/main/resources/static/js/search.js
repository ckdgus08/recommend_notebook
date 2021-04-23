function getPageId(n) {
    return 'article-page-' + n;
}

function getDocumentHeight() {
    const body = document.body;
    const html = document.documentElement;

    return Math.max(
        body.scrollHeight, body.offsetHeight,
        html.clientHeight, html.scrollHeight, html.offsetHeight
    );
}

function getScrollTop() {
    return (window.pageYOffset !== undefined) ? window.pageYOffset : (document.documentElement || document.body.parentNode || document.body).scrollTop;
}

function getArticlePage(page) {
    const pageElement = document.createElement('tbody');
    pageElement.id = getPageId(page);
    pageElement.className = 'article-list__page';
    let model = document.getElementById("model").textContent

    $.ajax({
        method: 'GET',
        url: 'api/search?model=' + model + '&page=' + (page - 1),
        dataType: 'json',
        success: function (data) {
            if (data.length > 0) {

                for (let i in data) {
                    let tr = document.createElement('tr');
                    tr.className = 'article-list__item normal';

                    let td1 = document.createElement("td");
                    td1.innerText = data[i].id;
                    tr.appendChild(td1);

                    let td4 = document.createElement("td");
                    td4.innerText = data[i].detail;
                    tr.appendChild(td4);

                    let td3 = document.createElement("td");
                    td3.innerText = data[i].title;
                    tr.appendChild(td3);

                    // let td5 = document.createElement("td");
                    // td5.innerText = data[i].content;
                    // tr.appendChild(td5);
                    let td5 = document.createElement("td");
                    for (let deep of data[i].review_deeps) {
                        td5.innerHTML += '<div class="' + deep.reviewType + '">' + deep.content + '</div>';
                    }
                    tr.appendChild(td5);

                    let tr2 = document.createElement('tr');
                    tr2.className = 'article-list__item normal';


                    pageElement.appendChild(tr);
                    pageElement.appendChild(tr2);

                }

            }
            articleList.classList.remove("loading");
        }
    })

    return pageElement;
}

function addPaginationPage(page) {
    const pageLink = document.createElement('a');
    pageLink.href = '#' + getPageId(page);
    pageLink.innerHTML = '<div class="btn right page_num">' + page + '</div>';

    const listItem = document.createElement('li');
    listItem.className = 'article-list__pagination__item';
    listItem.appendChild(pageLink);

    articleListPagination.appendChild(listItem);

    if (page === 1) {
        articleListPagination.classList.remove('article-list__pagination--inactive');
    }
}

function fetchPage(page) {
    articleList.appendChild(getArticlePage(page));
}

function addPage(page) {
    fetchPage(page);
    addPaginationPage(page);
    articleList.classList.add("loading");
}

let articleList;
let articleListPagination;
let page;

window.onload = function () {
    articleList = document.getElementById('article-list');
    articleListPagination = document.getElementById('article-list-pagination');

    page = 0;
    addPage(++page);
};

window.onscroll = function () {
    if (getScrollTop() < getDocumentHeight() - window.innerHeight) return;
    if (articleList.classList.contains("loading")) return;
    addPage(++page);
};