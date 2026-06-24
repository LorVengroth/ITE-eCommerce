package ite_3rd_ecommerce.co.stad.project.feature.category;

import ite_3rd_ecommerce.co.stad.project.feature.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column(nullable = false , unique = true , length = 50)
    private String name ;

    private String description ;
    private String icon ;
    @NotNull
    private Boolean isDeleted ;

    @ManyToOne
    private Category parentCategory ;

    // mappedBy in OneTomany (category) because in Product we had private Category category ;
    @OneToMany(mappedBy = "category")
    private List<Product> products ;

    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY)
    private List<Category> subCategories = new ArrayList<>();
}
