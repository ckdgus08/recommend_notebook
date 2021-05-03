let index = 2;
let now = [0, 1];

window.onload = function () {

    let main_block = document.getElementsByClassName("slide__main-block");

    main_block.item(0).classList.remove('hidden');
    main_block.item(1).classList.remove('hidden');

    let choose_button = document.getElementsByClassName("move_button__type choose");

    for (const chooseButtonElement of choose_button) {

        chooseButtonElement.onclick = function () {
            let attribute = this.getAttribute("data-slide-index");
            let number = now.indexOf(parseInt(attribute));

            let main_block_show = document.getElementsByClassName("slide__main-block");
            for (const mainBlockShowElement of main_block_show) {
                if (!mainBlockShowElement.classList.contains('hidden') && parseInt(mainBlockShowElement.getAttribute("data-slide-index")) !== number) {
                    mainBlockShowElement.classList.add('hidden');

                    let innerText = document.getElementById("main_progress").innerText;
                    document.querySelector("div[data-slide-index='" + innerText + "']").classList.remove('hidden');
                    document.getElementById("main_progress").innerText = (parseInt(innerText) + 1).toString();
                    break;
                }
            }
        }

    }


}