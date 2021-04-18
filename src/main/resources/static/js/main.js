let index = 1;

window.onload = function () {
    let move_button__left = document.getElementsByClassName("move_button__left")[0]
    let move_button__right = document.getElementsByClassName("move_button__right")[0]
    let weight_slide = new Slider('#weight_', {});
    let price_slide = new Slider('#price_', {});
    let inch_slide = new Slider('#inch_', {});

    setPurposeTypeButtonClickEvent();

    move_button__right.onclick = function () {
        let previous = document.querySelectorAll("div[data-slide-index='" + index.toString() + "']")
        let next = document.querySelectorAll("div[data-slide-index='" + (index + 1).toString() + "']")
        let input_value = document.getElementsByName(parseIndexToString(index))
        let result = document.getElementById(parseIndexToString(index))
        let temp = '';

        for (const value of input_value) {
            if (value.getAttribute("data-value") != null) {
                temp = value.getAttribute("data-value")
                break;
            }
            if (value.checked == true)
                if (temp == '')
                    temp = value.value;
                else
                    temp += ',' + value.value;
        }
        result.value = temp;

        for (const element of previous)
            element.classList.add("hidden")
        for (const element of next)
            element.classList.remove("hidden")


        if (index == 1)
            setNextPurpose(temp);

        move_button__left.classList.remove("hide")
        index++;
    }

    move_button__left.onclick = function () {
        let previous = document.querySelectorAll("div[data-slide-index='" + (index - 1).toString() + "']")
        let next = document.querySelectorAll("div[data-slide-index='" + (index).toString() + "']")


        for (const element of previous)
            element.classList.remove("hidden")
        for (const element of next)
            element.classList.add("hidden")

        if (index == 2)
            move_button__left.classList.add("hide")
        index--;
    }

}

function parseIndexToString(i) {
    switch (i) {
        case 1 :
            return "major";
        case 2 :
            return "purpose";
        case 3 :
            return "weight";
        case 4 :
            return "inch";
        case 5 :
            return "price";
    }
}

function setNextPurpose(major) {
    let all = document.querySelector("div.main-block__check-group div.all")
    let maj = document.querySelector("div.main-block__check-group div.maj")
    let gam = document.querySelector("div.main-block__check-group div.gam")
    let vid = document.querySelector("div.main-block__check-group div.vid")
    let pro = document.querySelector("div.main-block__check-group div.pro")

    let all_list = ["유튜브", "넷플릭스", "영화감상", "온라인강의", "화상회의", "zoom", "팀뷰어", "인터넷쇼핑", "웹서핑", "음악감상", "카카오톡", "주식",
        "문서작업", "파워포인트", "코딩"];

    all.innerHTML = ''
    for (let index in all_list)
        all.innerHTML += '<input type="checkbox" id="purpose_all_' + index + '" name="purpose" value="' + all_list[index] + '">' +
            '<label for="purpose_all_' + index + '" className="checkbox__label">' + all_list[index] + '</label>';

    let gam_list = ["저사양게임", "고사양게임", "리그오브레전드", "메이플스토리", "카트라이더", "바람의나라", "마인크래프트",
        "피파온라인", "테일즈런너", "블랙서바이벌", "발로란트", "스타크래프트1", "롤토체스", "크레이지아케이드",
        "던전앤파이터", "풋볼매니저", "서든어택", "마비노기", "하스스톤", "사이퍼즈", "리니지", "거상", "디아블로",
        "검은사막", "문명", "로스트아크", "히어로즈오브스톰", "배틀그라운드", "오버워치", "GTA",
        "위쳐", "와우", "콜오브듀티", "심즈", "블레이드앤소울", "위닝", "아이온", "스타크래프트2",
        "레인보우식스시즈", "삼국지토탈워", "어쌔신크리드", "몬스터헌터월드", "파이널판타지", "스팀게임",
        "모모앱플레이어", "녹스앱플레이어", "LD앱플레이어", "블루스택"];
    gam.innerHTML = ''
    for (let index in gam_list)
        gam.innerHTML += '<input type="checkbox" id="purpose_gam_' + index + '" name="purpose" value="' + gam_list[index] + '">' +
            '<label for="purpose_gam_' + index + '" className="checkbox__label">' + gam_list[index] + '</label>';

    let vid_list = ["영상편집", "파워디렉터", "블렌더", "무비메이커", "다빈치리졸브", "누크", "곰믹스", "프리미어프로", "포토샵", "파이널컷", "애프터이펙트"];
    vid.innerHTML = ''
    for (let index in vid_list)
        vid.innerHTML += '<input type="checkbox" id="purpose_vid_' + index + '" name="purpose" value="' + vid_list[index] + '">' +
            '<label for="purpose_vid_' + index + '" className="checkbox__label">' + vid_list[index] + '</label>';

    let pro_list = ["인터넷방송송출", "OBS"];
    pro.innerHTML = ''
    for (let index in pro_list)
        pro.innerHTML += '<input type="checkbox" id="purpose_pro_' + index + '" name="purpose" value="' + pro_list[index] + '">' +
            '<label for="purpose_pro_' + index + '" className="checkbox__label">' + pro_list[index] + '</label>';

    switch (major) {
        case "인문사회계열" :
            let maj_hum_list = ["케이렙", "ERP", "R프로그래밍", "AMOS", "SPSS", "STATA"];
            maj.innerHTML = ''

            for (let index in maj_hum_list)
                maj.innerHTML += '<input type="checkbox" id="purpose_maj_' + index + '" name="purpose" value="' + maj_hum_list[index] + '">' +
                    '<label for="purpose_maj_' + index + '" className="checkbox__label">' + maj_hum_list[index] + '</label>';
            break;
            break;
        case "자연과학계열" :
            maj.innerHTML = ''
            break;
        case "공학기술계열" :
            let maj_eng_list = ["2D캐드", "3D캐드", "오토캐드", "솔리드웍스", "매드랩", "아두이노", "인벤터", "카티아",
                "퓨전360", "마스터캠", "ansys", "크레오", "3dsMax", "NX", "UG", "피스파이스", "CAM", "orCad", "매트랩", "PLC",
                "트윈모션", "스케치업", "ArchiCAD", "루미온", "V_RAY", "레빗", "시네마4D"];
            maj.innerHTML = ''

            for (let index in maj_eng_list)
                maj.innerHTML += '<input type="checkbox" id="purpose_maj_' + index + '" name="purpose" value="' + maj_eng_list[index] + '">' +
                    '<label for="purpose_maj_' + index + '" className="checkbox__label">' + maj_eng_list[index] + '</label>';
            break;
        case "영상예술계열" :
            let maj_art_list = ["블렌더", "지브러시", "마야", "라이노", "키샷", "클립스튜디오", "브이레이", "Painter", "베가스", "옥테인",
                "어도비", "어도비클라우드", "포토샵", "인디자인", "일러스트레이터", "애프터이펙트", "프리미어프로", "XD", "라이트룸", "파이널컷",
                "큐베이스", "FL스튜디오", "기타프로", "가상악기", "음악작업", "에이블톤", "MIDI", "로직프로"];
            maj.innerHTML = ''

            for (let index in maj_art_list)
                maj.innerHTML += '<input type="checkbox" id="purpose_maj_' + index + '" name="purpose" value="' + maj_art_list[index] + '">' +
                    '<label for="purpose_maj_' + index + '" className="checkbox__label">' + maj_art_list[index] + '</label>';
            break;
        case "컴퓨터계열" :
            let maj_com_list = ["코딩", "웹개발", "앱개발", "VMware", "유니티", "언리얼",
                "R프로그래밍", "비주얼스튜디오코드", "비주얼스튜디오", "이클립스", "인텔리제이", "안드로이드스튜디오", "스프링", "쥬피터",
                "데이터베이스관리", "가상머신", "패킷트레이서", "QGIS", "Tableau", "텐서플로우"];
            maj.innerHTML = ''

            for (let index in maj_com_list)
                maj.innerHTML += '<input type="checkbox" id="purpose_maj_' + index + '" name="purpose" value="' + maj_com_list[index] + '">' +
                    '<label for="purpose_maj_' + index + '" className="checkbox__label">' + maj_com_list[index] + '</label>';
            break;
    }

}

function setPurposeTypeButtonClickEvent() {
    let move_button__type = document.querySelectorAll("button.move_button__type")
    let move_button__type_all = document.querySelector("button.move_button__type.all")
    let move_button__type_maj = document.querySelector("button.move_button__type.maj")
    let move_button__type_gam = document.querySelector("button.move_button__type.gam")
    let move_button__type_vid = document.querySelector("button.move_button__type.vid")
    let move_button__type_pro = document.querySelector("button.move_button__type.pro")

    let check_group__check = document.querySelectorAll("div.main-block__check-group div.check_group__check")
    let check_group__check_all = document.querySelector("div.main-block__check-group div.all")
    let check_group__check_maj = document.querySelector("div.main-block__check-group div.maj")
    let check_group__check_gam = document.querySelector("div.main-block__check-group div.gam")
    let check_group__check_vid = document.querySelector("div.main-block__check-group div.vid")
    let check_group__check_pro = document.querySelector("div.main-block__check-group div.pro")

    setClickEvent(move_button__type_all, check_group__check_all)
    setClickEvent(move_button__type_maj, check_group__check_maj)
    setClickEvent(move_button__type_gam, check_group__check_gam)
    setClickEvent(move_button__type_vid, check_group__check_vid)
    setClickEvent(move_button__type_pro, check_group__check_pro)

    function setClickEvent(check_, type_) {
        check_.onclick = function () {
            for (let ele of move_button__type)
                ele.style.backgroundColor = "#272b30"
            check_.style.backgroundColor = "#369bde"
            for (let ele of check_group__check)
                ele.classList.add("hidden")
            type_.classList.remove("hidden")
        }
    }
}
