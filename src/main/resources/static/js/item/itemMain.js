const itemMain = {

    init: () => {
        itemMain.bind()
    },

    bind: () => {
        document.querySelector('#showItemNewBtn').addEventListener('click', itemMain.showItemNew)
        document.querySelector('#deleteItemBtn').addEventListener('click', itemMain.deleteItem)
        document.querySelectorAll('.page-link').forEach((target) => {
            target.addEventListener('click', itemMain.search);
        })
    },

    showItemNew: () => {
        itemMain.showItemNewRequest();
    },

    showItemEdit: () => {
        itemMain.showItemEditRequest();
    },

    deleteItem: () => {
        itemMain.deleteItemRequest();
    },

    search: (event) => {
        let pageNum = event.target.dataset.pagenum;
        location.href = "/item/itemMain?page=" + pageNum;
    },

    showItemNewRequest: () => {
        const successHandler= (data) => {
            document.querySelector('#modal-content').innerHTML = data;
            itemNew.bind();
        }

        fetch("/item/itemNew", {
            method: "GET",
        })
            .then((response) => response.text())
            .then((data) => successHandler(data))
    },

    showItemEditRequest: (itemId) => {
        const successHandler= (data) => {
            document.querySelector('#modal-content').innerHTML = data;
            itemEdit.bind();
        }

        fetch('/item/itemEdit/'+ itemId, {
            method: 'GET'
        })
            .then(response => response.text())
            .then(data => successHandler(data))
    },

    deleteItemRequest: (itemId) => {
        if(! confirm("선택하신 품목을 삭제하시겠습니까?")) return;

        let itemIdArr = []
        document.querySelectorAll('.checkbox').forEach(target => {
            if(target.checked)
                itemIdArr.push(target.value)
        })

        const request = itemIdArr

        const successHandler= (data) => {
            location.href = "/item/itemMain?page=0";
        }

        fetch('/item/delete', {
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

itemMain.init()