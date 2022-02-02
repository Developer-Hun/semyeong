package semyeong.kmj.item.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import semyeong.kmj.item.dto.ItemResponse;
import semyeong.kmj.item.service.ItemReadService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemIndexController {

	private final ItemReadService itemReadService;

	@GetMapping("/item/itemMain")
	public String main(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) final Pageable pageable, Model model) {
		log.info("start itemMain...");
		Page<ItemResponse> itemListResponse = itemReadService.itemAll(pageable).getItemListResponse();
		model.addAttribute("itemPage", itemListResponse);
		return "item/itemMain";
	}

	@GetMapping("/item/itemNew")
	public String itemNew() {
		log.info("start itemNew...");
		return "item/itemNew";
	}

	@GetMapping("/item/itemEdit/{itemId}")
	public String itemEdit(@PathVariable Long itemId, Model model) {
		log.info("start itemEdit...");
		ItemResponse itemResponse = itemReadService.itemOne(itemId);
		model.addAttribute("item", itemResponse);
		return "item/itemEdit";
	}
}
