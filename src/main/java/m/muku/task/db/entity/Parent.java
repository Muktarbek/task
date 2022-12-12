package m.muku.task.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "parent")
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String language;
}
