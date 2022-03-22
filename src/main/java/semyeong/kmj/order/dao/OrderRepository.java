package semyeong.kmj.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import semyeong.kmj.common.common.AccountType;
import semyeong.kmj.order.entity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
