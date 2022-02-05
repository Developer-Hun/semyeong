const accountNew = {

    init: () => {
        accountNew.bind()
    },

    bind: () => {
        // document.querySelector('#addItem').addEventListener('click', accountNew.addItem)
    },

    getItems: () => {
        if (accountNew.items == undefined) {
            accountNew.getItemsRequest();
            return;
        }
        if (accountNew.items.length == 0) return alert ("현재 시스템에 등록된 품목이 없습니다.");

        accountNew.addItemRow();
    },

    setItem: (target) => {
        const selectedId = target.value;
        const findItem = accountNew.items.find((item) => item.id == selectedId);

        let tr = target.closest(`tr`);
        tr.querySelector(`[name='unit']`).value = findItem.unit;
        tr.querySelector(`[name='stockQuantity']`).value = findItem.stockQuantity;
        tr.querySelector(`[name='comment']`).value = findItem.comment;
    },

    addItemRow: () => {
        let tbodyTag = document.querySelector('#itemList');

        const options = accountNew.items.map((item, i) => {
            return `<option value="${item.id}" >${item.itemName}</option>`
        }).join('')

        let row =   `<tr>
                        <td>
                            <select class="select2_single form-control" tabIndex="-1" onchange="accountNew.setItem(this)">
                                <option value="" selected>선택해주세요</option>
                                ${options}
                            </select>
                        </td>
                        <td>
                            <input type="text" class="form-control" name="unit" readOnly="readonly" value="">
                        </td>
                        <td>
                            <input type="text" class="form-control" name="price" value="">
                        </td>
                        <td>
                            <input type="text" class="form-control" name="stockQuantity" value="" readOnly="readonly">
                        </td>
                        <td>
                            <input type="text" class="form-control" name="comment" value="" readOnly="readonly">
                        </td>
                    </tr>`

        tbodyTag.insertAdjacentHTML('beforeend', row);
    },

    getItemsRequest: () => {
        const successHandler= (data) => {
            accountNew.items = data;
            accountNew.addItemRow();
        }

        let statusType = 'enable';

        fetch('/item/list/'+ statusType, {
            method: 'GET'
        })
            .then(response => response.json())
            .then(data => successHandler(data))
    }
}