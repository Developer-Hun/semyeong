const itemEdit = {

    init: () => {
        itemEdit.bind()
    },

    bind: () => {
        document.querySelector('#updateItem').addEventListener('click', itemEdit.updateItem)
    },

    updateItem: () => {
        itemEdit.updateItemRequest();
    },

    updateItemRequest: () => {
        const id = document.querySelector('#id').value
        const itemName = document.querySelector('#itemName').value
        const unit = document.querySelector('#unit').value
        const statusType = document.querySelector('#statusType').value
        const comment = document.querySelector('#comment').value

        const request = {
            id,
            itemName,
            unit,
            statusType,
            comment,
        }

        const successHandler= (data) => {
            alert("품목 수정 완료")
            $("#modal").modal('hide');

            let currentPageNumber = document.querySelector("#currentPageNumber").value;
            location.href = "/item/itemMain?page=" + currentPageNumber;
        }

        fetch("/item/update", {
            method: "PATCH",
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