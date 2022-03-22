const orderNew = {

    init: () => {
        orderNew.bind()
    },

    bind: () => {
        // document.querySelector('#addItem').addEventListener('click', orderNew.addItem)
    },

    saveAccount: () => {
        orderNew.saveAccountRequest();
    },

    checkAll: () => {
        let checked = document.querySelector(`#managementItem-check-all`).checked;
        document.querySelectorAll(`input[name=managementItem-check]`).forEach( target => {
            if (checked) {
                target.checked = true;
            } else {
                target.checked = false;
            }
        })
    },

    getItems: () => {
        if (orderNew.items == undefined) {
            orderNew.getItemsRequest();
            return;
        }
        if (orderNew.items.length == 0) return alert ("현재 시스템에 등록된 품목이 없습니다.");

        orderNew.addManagementItemRow();
    },

    setItemInfo: (selectedTarget) => {
        const selectedId = selectedTarget.value;

        document.querySelectorAll('.item-row').forEach((target) => {
            console.log("selectedTarget = ", selectedTarget);
            console.log("target = ", target.querySelector(`[name='item']`));
            if (selectedTarget == target.querySelector(`[name='item']`)) return console.log("동일한 태그입니다.")
            if (target.querySelector(`[name='item']`).value == selectedId) {
                selectedTarget.value = '';
                return alert("같은 품목이 존재합니다.");
            }
        })

        if (selectedTarget.value == '') return;

        const findItem = orderNew.items.find((item) => item.id == selectedId);

        let itemRow = selectedTarget.closest(`.item-row`);
        itemRow.querySelector(`[name='unit']`).value = findItem.unit;
        itemRow.querySelector(`[name='stockQuantity']`).value = findItem.stockQuantity;
        itemRow.querySelector(`[name='comments']`).value = findItem.comments;
    },

    addManagementItemRow: () => {
        let tbodyTag = document.querySelector('#itemList');

        const options = orderNew.items.map((item, i) => {
            return `<option value="${item.id}" >${item.itemName}</option>`
        }).join('')

        let row =  `<tr class="item-row">
                        <td style="vertical-align: middle;">
                            <div style="display: flex;">
                                <input type="checkbox" name="managementItem-check" value="null">
                            </div>
                        </td>
                        <td>
                            <select class="select2_single form-control" tabIndex="-1" name="item" onchange="orderNew.setItemInfo(this)">
                                <option value="" selected>선택해주세요</option>
                                ${options}
                            </select>
                        </td>
                        <td>
                            <input type="text" class="form-control" name="unit" readOnly="readonly" value="">
                        </td>
                        <td>
                            <input type="text" class="form-control" name="basicPrice" value="">
                        </td>
                        <td>
                            <input type="text" class="form-control" name="stockQuantity" value="" readOnly="readonly">
                        </td>
                        <td>
                            <input type="text" class="form-control" name="comments" value="" readOnly="readonly">
                        </td>
                    </tr>`

        tbodyTag.insertAdjacentHTML('beforeend', row);
    },

    deleteManagementItemRow: () => {
        if (!confirm("선택하신 거래 품목을 삭제하시겠습니까?")) return;

        document.querySelectorAll(`input[name=managementItem-check]:checked`).forEach(target => {
            target.closest(`.item-row`).remove();
        })
    },

    getItemsRequest: () => {
        const successHandler= (data) => {
            orderNew.items = data;
            orderNew.addManagementItemRow();
        }

        let statusType = 'enable';

        fetch('/item/list/'+ statusType, {
            method: 'GET'
        })
            .then(response => response.json())
            .then(data => successHandler(data))
    },

    saveAccountRequest: () => {
        const accountName = document.querySelector('#accountName').value
        const accountType = document.querySelector('#accountType').value
        const statusType = document.querySelector('#statusType').value
        const comments = document.querySelector('#comments').value

        const request = {
            accountName,
            accountType,
            statusType,
            comments,
            managementItemRequests: []
        }

        document.querySelectorAll('.item-row').forEach((target) => {

            if (target.querySelector(`[name='item']`).value == '') return console.log("아이템을 선택하지 않았습니다.");

            const itemRow = {
                basicPrice: target.querySelector(`[name='basicPrice']`).value,
                itemRequest : {
                    id: target.querySelector(`[name='item']`).value,
                    unit: target.querySelector(`[name='unit']`).value,
                    stockQuantity: target.querySelector(`[name='stockQuantity']`).value,
                    comments: target.querySelector(`[name='comments']`).value,
                }
            }
            request.managementItemRequests.push(itemRow);
        })

        const successHandler= (data) => {
            let parseData = JSON.parse(data);

            if (parseData.status == undefined) {
                alert("거래처 추가 완료")
                $("#modal").modal('hide');

                let currentPageNumber = document.querySelector("#currentPageNumber").value;
                location.href = "/account/accountMain?page=" + currentPageNumber;
            } else {
                return alert(parseData.message);
            }
        }

        fetch("/account/save", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json;charset=UTF-8',
            },
            body: JSON.stringify(request)
        })
            .then((response) => response.text())
            .then((data) => successHandler(data))
            .catch((error) => alert(error))
    },

    datetimepicker: () => {
        $('input[name="orderDay"]').daterangepicker({
            singleDatePicker: true,
            locale: {
                "format": "YYYY-MM-DD",
                "separator": " - ",
                "applyLabel": "Apply",
                "cancelLabel": "Cancel",
                "fromLabel": "From",
                "toLabel": "To",
                "customRangeLabel": "Custom",
                "weekLabel": "W",
                "daysOfWeek": [ "일", "월", "화", "수", "목", "금", "토" ],
                "monthNames": ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
                "firstDay": 1
            }
        });
    }
}