const accountEdit = {

    init: () => {
        accountEdit.bind()
    },

    bind: () => {
        document.querySelector('#updateAccount').addEventListener('click', accountEdit.updateAccount)
    },

    updateAccount: () => {
        accountEdit.updateAccountRequest();
    },

    updateAccountRequest: () => {
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
            alert("거래처 수정 완료")
            $("#modal").modal('hide');

            let currentPageNumber = document.querySelector("#currentPageNumber").value;
            location.href = "/account/accountMain?page=" + currentPageNumber;
        }

        fetch("/account/update", {
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