package ite_3rd_ecommerce.co.stad.project.feature.order;

import ite_3rd_ecommerce.co.stad.project.feature.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_line")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @ManyToOne
    private Order order ;

    @ManyToOne
    private Product product ;
    private Integer qty ;
    private BigDecimal unitPrice ;


}
