package semyeong.kmj.item.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semyeong.kmj.item.dao.ItemRepository;
import semyeong.kmj.item.domain.ItemDomain;
import semyeong.kmj.item.dto.ItemRequest;
import semyeong.kmj.item.entity.Item;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ItemCRUDService {

	private final ItemRepository itemRepository;

	/**
	 * 품목 저장
	 */
	public Long itemSave(ItemRequest request) {
		Boolean duplications = itemRepository.existsByItemName(request.getItemName());

		if (duplications) throw new IllegalStateException("이미 존재하는 품목입니다.");

		Item item = itemRepository.save(ItemDomain.from(request).toCreateEntity());

		return item.getId();
	}

	/**
	 * 품목 수량 증가
	 */
	public void addStockQuantity(ItemRequest request) {
		Item item = itemRepository.findById(request.getId()).orElseThrow(
				() -> new NoSuchElementException("수정할 아이템이 존재하지 않습니다.")
		);

		item.addStock(request.getStockQuantity());
	}

	/**
	 * 품목 수량 감소
	 */
	public void removeStockQuantity(ItemRequest request) {
		Item item = itemRepository.findById(request.getId()).orElseThrow(
				() -> new NoSuchElementException("수정할 아이템이 존재하지 않습니다.")
		);

		item.removeStock(request.getStockQuantity());
	}

	public void itemUpdate(ItemRequest request) {
		Item item = itemRepository.findById(request.getId()).orElseThrow(
				() -> new NoSuchElementException("수정할 아이템이 존재하지 않습니다.")
		);

		item.updateItem(request.getItemName(), request.getUnit(), request.getStatusType(), request.getComments());
	}

	public void itemDelete(List<Long> itemIdArr) {
		Long count = itemRepository.itemBulkDelete(itemIdArr);
		log.info("delete item count = {}", count);
	}
}
