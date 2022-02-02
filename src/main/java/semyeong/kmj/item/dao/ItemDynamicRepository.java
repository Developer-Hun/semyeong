package semyeong.kmj.item.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import semyeong.kmj.common.common.StatusType;
import semyeong.kmj.item.entity.Item;

import java.util.List;

public interface ItemDynamicRepository {

	List<Item> itemSelectByStatus(StatusType status);

	Long itemBulkDelete(List<Long> itemIdArr);
}
