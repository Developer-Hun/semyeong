const itemNew = {

    init: () => {
        itemNew.bind()
    },

    bind: () => {
        document.querySelector('#saveItem').addEventListener('click', itemNew.saveItem)
    },

    saveItem: () => {
        itemNew.saveItemRequest();
    },

    saveItemRequest: () => {
        const itemName = document.querySelector('#itemName').value
        const unit = document.querySelector('#unit').value
        const statusType = document.querySelector('#statusType').value
        const comment = document.querySelector('#comment').value

        const request = {
            itemName,
            unit,
            statusType,
            comment,
        }

        const successHandler= (data) => {
            alert("품목 추가 완료")
            $("#modal").modal('hide');

            let currentPageNumber = document.querySelector("#currentPageNumber").value;
            location.href = "/item/itemMain?page=" + currentPageNumber;
        }

        fetch("/item/save", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json;charset=UTF-8',
            },
            body: JSON.stringify(request)
        })
            .then((response) => response.text())
            .then((data) => console.log(data))
            .then((data) => successHandler(data))
            .catch((error) => alert(error))
    }
}