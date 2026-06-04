package ite_3rd_ecommerce.co.stad.project.repository;

import ite_3rd_ecommerce.co.stad.project.domain.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine , Integer> {
}
