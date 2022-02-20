const accountEdit = {

    init: () => {
        accountEdit.bind()
    },

    bind: () => {
        // document.querySelector('#addItem').addEventListener('click', accountNew.addItem)
    },

    editAccount: () => {
        accountEdit.editAccountRequest();
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
        if (accountEdit.items == undefined) {
            accountEdit.getItemsRequest();
            return;
        }
        if (accountEdit.items.length == 0) return alert ("현재 시스템에 등록된 품목이 없습니다.");

        accountEdit.addManagementItemRow();
    },

    // TODO : 관리품목 삭제 기능 추가 필요, selectbox 클릭 시 가져온 데이터 세팅, 수정 시 새로운 데이터 및 변경 데이터 적용 여부 확인
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

        const findItem = accountEdit.items.find((item) => item.id == selectedId);

        let itemRow = selectedTarget.closest(`.item-row`);
        itemRow.querySelector(`[name='unit']`).value = findItem.unit;
        itemRow.querySelector(`[name='stockQuantity']`).value = findItem.stockQuantity;
        itemRow.querySelector(`[name='comments']`).value = findItem.comments;
    },

    addManagementItemRow: () => {
        let tbodyTag = document.querySelector('#itemList');

        const options = accountEdit.items.map((item, i) => {
            return `<option value="${item.id}" >${item.itemName}</option>`
        }).join('')

        let row =  `<tr class="item-row">
                        <input type="hidden" name="managementItemId" value="null"/>
                        <td style="vertical-align: middle;">
                            <div style="display: flex;">
                                <input type="checkbox" name="managementItem-check" value="null">
                            </div>
                        </td>
                        <td>
                            <select class="select2_single form-control" tabIndex="-1" name="item" onchange="accountEdit.setItemInfo(this)">
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

        accountEdit.deleteManagementItemRequest();
    },

    getItemsRequest: () => {
        const successHandler= (data) => {
            accountEdit.items = data;
            accountEdit.addManagementItemRow();
        }

        let statusType = 'enable';

        fetch('/item/list/'+ statusType, {
            method: 'GET'
        })
            .then(response => response.json())
            .then(data => successHandler(data))
    },

    editAccountRequest: () => {
        const id = document.querySelector('#id').value
        const accountName = document.querySelector('#accountName').value
        const accountType = document.querySelector('#accountType').value
        const statusType = document.querySelector('#statusType').value
        const comments = document.querySelector('#comments').value

        const request = {
            id,
            accountName,
            accountType,
            statusType,
            comments,
            managementItemRequests: []
        }

        document.querySelectorAll('.item-row').forEach((target) => {

            if (target.querySelector(`[name='item']`).value == '') return console.log("아이템을 선택하지 않았습니다.");

            const itemRow = {
                id : target.querySelector(`[name='managementItemId']`).value,
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
            console.log(data);
            let parseData = JSON.parse(data);

            if (parseData.status == undefined) {
                alert("거래처 수정 완료")
                $("#modal").modal('hide');

                let currentPageNumber = document.querySelector("#currentPageNumber").value;
                location.href = "/account/accountMain?page=" + currentPageNumber;
            } else {
                return alert(parseData.message);
            }
        }

        fetch("/account/edit", {
            method: "PUT",
            headers: {
                'Content-Type': 'application/json;charset=UTF-8',
            },
            body: JSON.stringify(request)
        })
            .then((response) => response.text())
            .then((data) => successHandler(data))
            .catch((error) => alert(error))
    },

    deleteManagementItemRequest: () => {
        let deleteManagementItemIdList = []
        document.querySelectorAll(`input[name=managementItem-check]:checked`).forEach(target => {
            if (target.value != null) deleteManagementItemIdList.push(target.value);
            target.closest(`.item-row`).remove();
        })

        const request = deleteManagementItemIdList

        const successHandler= (data) => {
            alert("선택하신 거래 품목이 삭제되었습니다.");
        }

        fetch('/managementItem/delete', {
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