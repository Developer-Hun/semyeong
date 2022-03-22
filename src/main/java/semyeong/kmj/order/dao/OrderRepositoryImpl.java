package semyeong.kmj.order.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;


public class OrderRepositoryImpl implements OrderDynamicRepository {

	private final JPAQueryFactory queryFactory;

	public OrderRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

}
