const accountMain = {

    init: () => {
        accountMain.bind()
    },

    bind: () => {
        document.querySelector('#showAccountNewBtn').addEventListener('click', accountMain.showAccountNew)
        document.querySelector('#deleteAccountBtn').addEventListener('click', accountMain.deleteAccount)
        document.querySelectorAll('.page-link').forEach((target) => {
            target.addEventListener('click', accountMain.search)
        })
    },

    showAccountNew: () => {
        accountMain.showAccountNewRequest();
    },

    showAccountEdit: () => {
        accountMain.showAccountEditRequest();
    },

    deleteAccount: () => {
        accountMain.deleteItemRequest();
    },

    search: (event) => {
        let pageNum = event.target.dataset.pagenum;
        location.href = "/account/accountMain?page=" + pageNum;
    },

    showAccountNewRequest: () => {
        const successHandler= (data) => {
            document.querySelector('#modal-content').innerHTML = data;
            accountNew.bind();
        }

        fetch("/account/accountNew", {
            method: "GET",
        })
            .then((response) => response.text())
            .then((data) => successHandler(data))
    },

    showAccountEditRequest: (itemId) => {
        const successHandler= (data) => {
            document.querySelector('#modal-content').innerHTML = data;
            accountEdit.bind();
        }

        fetch('/account/accountEdit/'+ itemId, {
            method: 'GET'
        })
            .then(response => response.text())
            .then(data => successHandler(data))
    },

    deleteItemRequest: (itemId) => {
        if(! confirm("선택하신 거래처를 삭제하시겠습니까?")) return;

        let accountIdArr = []
        document.querySelectorAll('.checkbox').forEach(target => {
            if(target.checked)
                accountIdArr.push(target.value)
        })

        const request = accountIdArr

        const successHandler= (data) => {
            location.href = "/account/accountMain?page=0";
        }

        fetch('/account/delete', {
            method: "DELETE",
            headers: {
                'Content-Type': 'application/json;charset=UTF-8',
            },
            body: JSON.stringify(request)
        })
            .then((response) => response.text())
            .then(data => successHandler(data))

    }
}

accountMain.init()