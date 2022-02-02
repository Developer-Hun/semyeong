package semyeong.kmj.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semyeong.kmj.common.common.StatusType;
import semyeong.kmj.item.dao.ItemRepository;
import semyeong.kmj.item.dto.ItemListResponse;
import semyeong.kmj.item.dto.ItemResponse;
import semyeong.kmj.item.entity.Item;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemReadService {

	private final ItemRepository itemRepository;

	public ItemListResponse itemAll(Pageable pageable) {
		return ItemListResponse.from(itemRepository.findAll(pageable));
	}

	public List<ItemResponse> itemSelectByStatus(StatusType statusType) {
		List<ItemResponse> itemResponseList = itemRepository.itemSelectByStatus(statusType).stream().map(ItemResponse::from).collect(Collectors.toList());
		return itemResponseList;
	}

	public ItemResponse itemOne(Long itemId) {
		Item item = itemRepository.findById(itemId).orElseThrow(
				() -> new NoSuchElementException("해당 품목이 존재하지 않습니다.")
		);

		return ItemResponse.from(item);
	}
}
