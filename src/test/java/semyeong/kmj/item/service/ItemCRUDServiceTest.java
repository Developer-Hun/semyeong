package semyeong.kmj.item.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import semyeong.kmj.common.common.StatusType;
import semyeong.kmj.exception.NotEnoughStockException;
import semyeong.kmj.item.dao.ItemRepository;
import semyeong.kmj.item.dto.ItemRequest;
import semyeong.kmj.item.dto.ItemResponse;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemCRUDServiceTest {

	@Autowired ItemCRUDService itemCRUDService;
	@Autowired ItemReadService itemReadService;
	@Autowired ItemRepository itemRepository;

	@Test
	public void 아이템_중복_예외() throws Exception {
	    // given
		ItemRequest request1 = new ItemRequest();
		ItemRequest request2 = new ItemRequest();

		request1.setItemName("아이템1");
		request1.setUnit("마리");
		request1.setStockQuantity(0);
		request1.setStatusType(StatusType.enable);

		request2.setItemName("아이템1");
		request2.setUnit("팩");
		request2.setStockQuantity(0);
		request2.setStatusType(StatusType.enable);

	    // when
		itemCRUDService.itemSave(request1);

	    // then
		assertThrows(IllegalStateException.class, () -> itemCRUDService.itemSave(request2));
	}

	@Test
	public void 아이템_등록() throws Exception {
	    // given
		ItemRequest request = new ItemRequest();

		request.setItemName("아이템1");
		request.setUnit("마리");
		request.setStockQuantity(0);
		request.setStatusType(StatusType.enable);

	    // when
		Long itemId = itemCRUDService.itemSave(request);
		ItemResponse findItem = itemReadService.itemOne(itemId);

		// then
		assertThat(itemId).isEqualTo(findItem.getId());
	}

	@Test
	public void 품목_수량_증가() throws Exception {
	    // given
		ItemRequest request = new ItemRequest();

		request.setItemName("아이템1");
		request.setUnit("마리");
		request.setStockQuantity(0);
		request.setStatusType(StatusType.enable);

		Long itemId = itemCRUDService.itemSave(request);

		request.setId(itemId);
		request.setStockQuantity(10);

		// when
		itemCRUDService.addStockQuantity(request);
		ItemResponse findItem = itemReadService.itemOne(itemId);

		// then
		assertThat(findItem.getStockQuantity()).isEqualTo(10);
	}

	@Test
	public void 품목_수량_감소() throws Exception {
	    // given
		ItemRequest request = new ItemRequest();

		request.setItemName("아이템1");
		request.setUnit("마리");
		request.setStockQuantity(0);
		request.setStatusType(StatusType.enable);

		Long itemId = itemCRUDService.itemSave(request);

		request.setId(itemId);
		request.setStockQuantity(10);

		itemCRUDService.addStockQuantity(request);
		request.setStockQuantity(5);

		// when
		itemCRUDService.removeStockQuantity(request);
		ItemResponse findItem = itemReadService.itemOne(itemId);

		// then
		assertThat(findItem.getStockQuantity()).isEqualTo(5);
	}

	@Test
	public void 품목_수량_0이하_예외() throws Exception {
		// given
		ItemRequest request = new ItemRequest();

		request.setItemName("아이템1");
		request.setUnit("마리");
		request.setStockQuantity(0);
		request.setStatusType(StatusType.enable);

		Long itemId = itemCRUDService.itemSave(request);

		request.setId(itemId);
		request.setStockQuantity(10);

		// when then
		assertThrows(NotEnoughStockException.class, () -> itemCRUDService.removeStockQuantity(request));
	}
}