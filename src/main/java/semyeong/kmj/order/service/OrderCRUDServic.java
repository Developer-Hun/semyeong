package semyeong.kmj.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semyeong.kmj.order.dao.OrderRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderCRUDServic {

	private final OrderRepository orderRepository;

}
