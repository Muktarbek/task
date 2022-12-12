package m.muku.task.db.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "partner")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Category> categories;
    private String image;
    private String gotolink;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ActionDetail> action_details;
    private String products_xml_link;
}
