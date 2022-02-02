package semyeong.kmj.item.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import semyeong.kmj.item.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemDynamicRepository {
	Boolean existsByItemName(String itemName);

//	Page<Item> findAllByStatus(Pageable pageable, String status);
}
