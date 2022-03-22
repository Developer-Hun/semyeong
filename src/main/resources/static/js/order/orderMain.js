const orderMain = {

    init: () => {
        orderMain.bind()
    },

    bind: () => {
        document.querySelector('#showOrderNewBtn').addEventListener('click', orderMain.showOrderNew)
        document.querySelector('#deleteOrderBtn').addEventListener('click', orderMain.deleteOrder)
        document.querySelectorAll('.page-link').forEach((target) => {
            target.addEventListener('click', orderMain.search)
        })
    },

    showOrderNew: () => {
        orderMain.showOrderNewRequest();
    },

    showOrderEdit: () => {
        orderMain.showOrderEditRequest();
    },

    deleteOrder: () => {
        orderMain.deleteItemRequest();
    },

    search: (event) => {
        let pageNum = event.target.dataset.pagenum;
        location.href = "/order/orderMain?page=" + pageNum;
    },

    showOrderNewRequest: () => {
        const successHandler= (data) => {
            document.querySelector('#modal-content').innerHTML = data;
            orderNew.bind();
            orderNew.datetimepicker();
        }

        fetch("/order/orderNew", {
            method: "GET",
        })
            .then((response) => response.text())
            .then((data) => successHandler(data))
    },

    showOrderEditRequest: (orderId) => {
        const successHandler= (data) => {
            document.querySelector('#modal-content').innerHTML = data;
            // orderEdit.bind();
        }

        fetch('/order/orderEdit/'+ orderId, {
            method: 'GET'
        })
            .then(response => response.text())
            .then(data => successHandler(data))
    },

    deleteItemRequest: (itemId) => {
        if(! confirm("선택하신 거래처를 삭제하시겠습니까?")) return;

        let orderIdArr = []
        document.querySelectorAll('.checkbox').forEach(target => {
            if(target.checked)
                orderIdArr.push(target.value)
        })

        const request = orderIdArr

        const successHandler= (data) => {
            location.href = "/order/orderMain?page=0";
        }

        fetch('/order/delete', {
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

orderMain.init()