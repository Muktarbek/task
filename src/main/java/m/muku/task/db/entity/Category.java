package m.muku.task.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "category")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String language;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Parent parent;

}
