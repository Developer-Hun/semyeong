package semyeong.kmj.item.dao;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import semyeong.kmj.common.common.StatusType;
import semyeong.kmj.item.entity.Item;
import semyeong.kmj.item.entity.QItem;

import javax.persistence.EntityManager;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static semyeong.kmj.item.entity.QItem.*;

public class ItemRepositoryImpl implements ItemDynamicRepository {

	private final JPAQueryFactory queryFactory;

	public ItemRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public List<Item> itemSelectByStatus(StatusType status) {
		List<Item> itemList = queryFactory
				.select(item)
				.from(item)
				.where(item.statusType.eq(status))
				.fetch();

		return itemList;
	}

	@Override
	public Long itemBulkDelete(List<Long> itemIdArr) {
		long count = queryFactory.delete(item).where(item.id.in(itemIdArr)).execute();
		return count;
	}

//	private BooleanExpression statusEq(StatusType status) {
//		return hasText(status) ? item.statusType.eq(status) : null;
//	}


}
