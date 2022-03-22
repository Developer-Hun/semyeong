package semyeong.kmj.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semyeong.kmj.order.dto.OrderRequest;
import semyeong.kmj.order.service.OrderCRUDServic;
import semyeong.kmj.order.service.OrderReadService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderApiController {

	private final OrderCRUDServic orderCRUDServic;
	private final OrderReadService orderReadService;

	@PostMapping("/order/save")
	public ResponseEntity<Long> orderSave(@RequestBody final OrderRequest request) {
		log.info("start order save...");
		return ResponseEntity.ok().build();
	}

	@PutMapping("/order/edit")
	public ResponseEntity<Long> orderEdit(@RequestBody final OrderRequest request) {
		log.info("start order edit...");
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/order/delete")
	public ResponseEntity<String> orderDelete(@RequestBody final List<Long> orderIdList) {
		log.info("start order delete...");
		return ResponseEntity.ok().build();
	}
}
