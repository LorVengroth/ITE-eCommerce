package ite_3rd_ecommerce.co.stad.project.feature.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order , UUID> {
}
