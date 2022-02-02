package semyeong.kmj.item.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semyeong.kmj.common.common.StatusType;
import semyeong.kmj.item.dto.ItemListResponse;
import semyeong.kmj.item.dto.ItemRequest;
import semyeong.kmj.item.dto.ItemResponse;
import semyeong.kmj.item.service.ItemCRUDService;
import semyeong.kmj.item.service.ItemReadService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ItemApiController {

	private final ItemReadService itemReadService;
	private final ItemCRUDService itemCRUDService;

	@PostMapping("/item/save")
	public ResponseEntity<String> itemSave(@RequestBody final ItemRequest request) {
		log.info("start itemSave...");
		itemCRUDService.itemSave(request);
		return ResponseEntity.ok().build();
	}

	@PatchMapping("/item/update")
	public ResponseEntity<String> itemUpdate(@RequestBody final ItemRequest request) {
		log.info("start itemUpdate...");
		itemCRUDService.itemUpdate(request);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/item/delete")
	public ResponseEntity<String> itemDelete(@RequestBody final List<Long> itemIdArr) {
		log.info("start itemDelete...");
		itemCRUDService.itemDelete(itemIdArr);
		return ResponseEntity.ok("삭제되었습니다.");
	}

	@GetMapping("/item/list/{statusType}")
	public ResponseEntity<List<ItemResponse>> itemSelectByStatus(@PathVariable StatusType statusType) {
		List<ItemResponse> itemResponses = itemReadService.itemSelectByStatus(statusType);
		return ResponseEntity.ok(itemResponses);
	}
}
