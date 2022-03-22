package semyeong.kmj.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import semyeong.kmj.item.service.ItemReadService;
import semyeong.kmj.order.service.OrderReadService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderIndexController {

	private final OrderReadService orderReadService;
	private final ItemReadService itemReadService;

	@GetMapping("/order/orderMain")
	public String orderMain(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) final Pageable pageable, Model model) {
		log.info("start orderMain...");
		return "order/orderMain";
	}

	@GetMapping("/order/orderNew")
	public String orderNew() {
		log.info("start orderNew...");
		return "order/orderNew";
	}

	@GetMapping("/order/orderEdit/{orderId}")
	public String orderEdit(@PathVariable Long orderId, Model model) {
		log.info("start orderEdit...");
		return "order/orderEdit";
	}
}
