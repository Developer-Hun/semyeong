package semyeong.kmj.account.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import semyeong.kmj.account.entity.Account;
import semyeong.kmj.account.entity.QAccount;
import semyeong.kmj.item.entity.QItem;
import semyeong.kmj.managementItem.entity.QManagementItem;

import javax.persistence.EntityManager;
import java.util.List;

import static semyeong.kmj.account.entity.QAccount.*;
import static semyeong.kmj.item.entity.QItem.*;
import static semyeong.kmj.managementItem.entity.QManagementItem.*;

public class AccountRepositoryImpl implements AccountDynamicRepository {

	private final JPAQueryFactory queryFactory;

	public AccountRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public Account findAllByFetchJoin(Long accountId) {
		return queryFactory.selectFrom(account)
//				.join(account.managementItems, managementItem)
//				.join(managementItem.item, item)
				.where(account.id.eq(accountId))
				.fetchOne();
	}
}
