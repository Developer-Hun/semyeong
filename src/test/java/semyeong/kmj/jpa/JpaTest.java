package semyeong.kmj.jpa;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import semyeong.kmj.account.dao.AccountRepository;
import semyeong.kmj.account.entity.Account;
import semyeong.kmj.common.common.AccountType;
import semyeong.kmj.common.common.StatusType;
import semyeong.kmj.item.entity.Item;
import semyeong.kmj.managementItem.entity.ManagementItem;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static semyeong.kmj.account.entity.QAccount.*;
import static semyeong.kmj.item.entity.QItem.*;
import static semyeong.kmj.managementItem.entity.QManagementItem.*;

@SpringBootTest
@Transactional
@Commit
public class JpaTest {

	@Autowired AccountRepository accountRepository;
	@Autowired EntityManager em;

	JPAQueryFactory queryFactory;

	@BeforeEach
	public void before() {
		queryFactory = new JPAQueryFactory(em);

		Item item1 = Item.builder()
				.stockQuantity(100)
				.itemName("테스트 아이템1")
				.statusType(StatusType.enable)
				.unit("마리")
				.comments("테스트 아이템")
				.build();

		Item item2 = Item.builder()
				.stockQuantity(100)
				.itemName("테스트 아이템2")
				.statusType(StatusType.enable)
				.unit("마리")
				.comments("테스트 아이템")
				.build();

		em.persist(item1);
		em.persist(item2);

		ManagementItem managementItem1 = ManagementItem.builder()
				.basicPrice(10000)
				.comments("테스트 관리 품목1")
				.item(item1)
				.build();

		ManagementItem managementItem2 = ManagementItem.builder()
				.basicPrice(10000)
				.comments("테스트 관리 품목2")
				.item(item2)
				.build();

		List<ManagementItem> managementItemList = new ArrayList<>();

		managementItemList.add(managementItem1);
		managementItemList.add(managementItem2);

		Account account = Account.builder()
				.accountName("거래처1")
				.accountType(AccountType.purchase)
				.statusType(StatusType.enable)
				.comments("테스트 거래처")
				.build();

		account.addManagementItems(managementItemList);

		em.persist(account);

		Item item3 = Item.builder()
				.stockQuantity(100)
				.itemName("테스트 아이템3")
				.statusType(StatusType.enable)
				.unit("마리")
				.comments("테스트 아이템")
				.build();

		em.persist(item3);
	}

	@Test
	@Commit
	public void 연관관계_거래품목_정보_변경() throws Exception {
		Account findAccount = queryFactory.selectFrom(account)
				.join(account.managementItems, managementItem).fetchJoin()
				.join(managementItem.item, item).fetchJoin()
				.fetchOne();

		Item updateItem1 = Item.builder()
				.id(1L)
				.stockQuantity(100)
				.itemName("테스트 아이템1")
				.statusType(StatusType.enable)
				.unit("마리")
				.comments("테스트 아이템")
				.build();

		Item updateItem2 = Item.builder()
				.id(2L)
				.stockQuantity(100)
				.itemName("테스트 아이템2")
				.statusType(StatusType.enable)
				.unit("마리")
				.comments("테스트 아이템")
				.build();

		Item updateItem3 = Item.builder()
				.id(6L)
				.stockQuantity(100)
				.itemName("테스트 아이템3")
				.statusType(StatusType.enable)
				.unit("마리")
				.comments("테스트 아이템")
				.build();

		ManagementItem updateManagementItem1 = ManagementItem.builder()
				.id(4L)
				.basicPrice(10000)
				.comments("테스트 관리 품목1 변경")
				.item(updateItem1)
				.build();
		
		ManagementItem updateManagementItem2 = ManagementItem.builder()
				.id(5L)
				.basicPrice(10000)
				.comments("테스트 관리 품목2 변경")
				.item(updateItem2)
				.build();

		ManagementItem updateManagementItem3 = ManagementItem.builder()
				.basicPrice(10000)
				.comments("테스트 관리 품목2")
				.item(updateItem3)
				.build();

		List<ManagementItem> managementItemList = new ArrayList<>();

		managementItemList.add(updateManagementItem1);
		managementItemList.add(updateManagementItem2);
		managementItemList.add(updateManagementItem3);

		Account updateAccount = Account.builder()
				.id(3L)
				.accountName("거래처1 변경")
				.accountType(AccountType.purchase)
				.statusType(StatusType.enable)
				.comments("테스트 거래처 변경")
				.build();

		updateAccount.addManagementItems(managementItemList);

		for (ManagementItem managementItem : managementItemList) {
			if (managementItem.getId() == null) findAccount.addManagementItem(managementItem);
		}

		findAccount.updateManagementItems(updateAccount.getManagementItems());
	}
	
	@Test
	@Commit
	public void 연관관계_거래품목_추가() throws Exception {
		Account findAccount = queryFactory.selectFrom(account)
				.join(account.managementItems, managementItem).fetchJoin()
				.join(managementItem.item, item).fetchJoin()
				.fetchOne();

		Item updateItem3 = Item.builder()
				.id(6L)
				.stockQuantity(100)
				.itemName("테스트 아이템3 변경")
				.statusType(StatusType.enable)
				.unit("마리")
				.comments("테스트 아이템 변경")
				.build();

		ManagementItem updateManagementItem3 = ManagementItem.builder()
				.basicPrice(10000)
				.comments("테스트 관리 품목3 변경")
				.item(updateItem3)
				.build();

		findAccount.addManagementItem(updateManagementItem3);
	}

	@Test
	@Commit
	public void 연관관계_거래품목_삭제() throws Exception {
		List<ManagementItem> deleteManagementItems = queryFactory.selectFrom(managementItem)
				.where(managementItem.id.in(4L, 5L))
				.fetch();

		ArrayList<Long> deleteList = new ArrayList<>();
		for (ManagementItem deleteManagementItem : deleteManagementItems) {
			deleteManagementItem.deleteManagementItem(deleteManagementItem);
//		    em.remove(deleteManagementItem1);
//		    em.remove(deleteManagementItem2);

			deleteList.add(deleteManagementItem.getId());
		}


//		queryFactory.delete(managementItem)
//				.where(managementItem.id.in(deleteList))
//				.execute();

		em.createQuery("delete from ManagementItem m where m.id in (:deleteList)").setParameter("deleteList", deleteList).executeUpdate();
	}

	@Test
	@Commit
	public void 연관관계_거래품목_아이템_변경() throws Exception {
		Item changeItem = queryFactory.selectFrom(item)
				.where(item.id.eq(6L))
				.fetchOne();

		Item updateItem3 = Item.builder()
				.id(6L)
				.stockQuantity(100)
				.itemName("테스트 아이템3 변경")
				.statusType(StatusType.enable)
				.unit("마리")
				.comments("테스트 아이템 변경")
				.build();

		ManagementItem findManagementItem = queryFactory.selectFrom(managementItem)
				.where(managementItem.id.eq(5L))
				.fetchOne();

		findManagementItem.setItem(updateItem3);
	}
}
