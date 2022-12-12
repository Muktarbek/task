package m.muku.task.db.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "action_details")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ActionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Tariff> tariffs;
    private String name;
    private String type;
    private Long hold_size;
}
