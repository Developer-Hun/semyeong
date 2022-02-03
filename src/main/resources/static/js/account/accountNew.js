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

    addItemRow: () => {
        return;
        // TODO : 객체 반복문으로 새로운 아이템 추가하는 로직 구현
        let tbodyTag = document.querySelector('#itemList');

        $(".catecory").empty()

        const rows = accountNew.items.map((item, i) => {
            return `<option value="" selected>오리 10호</option> <a href="#" class="cat-item">${item.categoryName}</a>`
        }).join('')


        let row =   `<tr>
                        <td>
                            <select class="select2_single form-control" tabIndex="-1">
                                <option value="" selected>오리 10호</option>
                                <option value="">닭 7호</option>
                            </select>
                        </td>
                        <td>
                            <select class="select2_single form-control" tabIndex="-1">
                                <option value="" selected>마리</option>
                                <option value="">팩</option>
                            </select>
                        </td>
                        <td>
                            <input type="text" class="form-control" value="12,000">
                        </td>
                        <td>
                            <input type="text" class="form-control" readOnly="readonly" value="100">
                        </td>
                        <td>
                            <input type="text" class="form-control" readOnly="readonly" value="오리다">
                        </td>
                    </tr>`

        tbodyTag.insertAdjacentHTML('beforeend', row);
    },

    getItemsRequest: () => {
        const successHandler= (data) => {

            accountNew.items = data.reduce((res, item, index, array) => {
                res[item.id] = item
                return res;
            }, {});

            // accountNew.items = data;
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