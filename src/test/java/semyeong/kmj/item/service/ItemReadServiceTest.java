package semyeong.kmj.item.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import semyeong.kmj.common.common.StatusType;
import semyeong.kmj.item.dao.ItemRepository;
import semyeong.kmj.item.dto.ItemListResponse;
import semyeong.kmj.item.dto.ItemRequest;
import semyeong.kmj.item.dto.ItemResponse;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemReadServiceTest {

	@Autowired ItemReadService itemReadService;
	@Autowired ItemCRUDService itemCRUDService;
	@Autowired ItemRepository itemRepository;

	@Test
	public void 모든_아이템_목록_가져오기() throws Exception {
	    // given
		ItemRequest request1 = new ItemRequest();
		ItemRequest request2 = new ItemRequest();
		ItemRequest request3 = new ItemRequest();

		request1.setItemName("아이템1");
		request1.setUnit("마리");
		request1.setStockQuantity(0);
		request1.setStatusType(StatusType.enable);

		request2.setItemName("아이템2");
		request2.setUnit("마리");
		request2.setStockQuantity(0);
		request2.setStatusType(StatusType.enable);

		request3.setItemName("아이템3");
		request3.setUnit("마리");
		request3.setStockQuantity(0);
		request3.setStatusType(StatusType.enable);

		itemCRUDService.itemSave(request1);
		itemCRUDService.itemSave(request2);
		itemCRUDService.itemSave(request3);

		Pageable pageable = PageRequest.of(0, 10);

		// when
		ItemListResponse itemListResponse = itemReadService.itemAll(pageable);

		// then
		assertThat(itemListResponse.getItemListResponse().getTotalElements()).isEqualTo(3);
	}

	@Test
	public void 존재하지않는_아이템_예외() throws Exception {
	    // given when then
		assertThrows(NoSuchElementException.class, () -> itemReadService.itemOne(1L));
	}

	@Test
	public void 특정_아이템_가져오기() throws Exception {
	    // given
		ItemRequest request = new ItemRequest();

		request.setItemName("아이템1");
		request.setUnit("마리");
		request.setStockQuantity(0);
		request.setStatusType(StatusType.enable);

		Long itemId = itemCRUDService.itemSave(request);

		// when
		ItemResponse findItem = itemReadService.itemOne(itemId);

		// then
		assertThat(findItem.getId()).isEqualTo(itemId);
	}
}