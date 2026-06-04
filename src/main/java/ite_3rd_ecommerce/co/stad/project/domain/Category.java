package ite_3rd_ecommerce.co.stad.project.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne
    private Category parentCategory ;

    // mappedBy in OneTomany (category) because in Product we had private Category category ;
    @OneToMany(mappedBy = "category")
    private List<Product> products ;
}
