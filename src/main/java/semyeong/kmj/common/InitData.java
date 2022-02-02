package semyeong.kmj.common;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import semyeong.kmj.common.common.StatusType;
import semyeong.kmj.item.domain.ItemDomain;
import semyeong.kmj.item.dto.ItemRequest;
import semyeong.kmj.item.entity.Item;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Profile("local")
@Component
@RequiredArgsConstructor
public class InitData {

	private final InitDataService initDataService;

	@PostConstruct
	public void init() {
		initDataService.init();
	}

	@Component
	static class InitDataService {
		@PersistenceContext	EntityManager em;

		@Transactional
		public void init() {

			for (int i = 1; i <= 18; i++) {
				ItemRequest request = new ItemRequest();

				String unit = i % 2 == 0 ? "마리" : "팩";
				StatusType statusType = i % 2 == 0 ? StatusType.enable : StatusType.disable;

				request.setItemName("아이템" + i);
				request.setUnit(unit);
				request.setComment("비고" + i);
				request.setStatusType(statusType);
				request.setStockQuantity(0);

				em.persist(ItemDomain.from(request).toCreateEntity());
			}
		}
	}

}
