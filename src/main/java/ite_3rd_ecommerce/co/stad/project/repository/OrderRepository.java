package ite_3rd_ecommerce.co.stad.project.repository;

import ite_3rd_ecommerce.co.stad.project.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order , UUID> {
}
