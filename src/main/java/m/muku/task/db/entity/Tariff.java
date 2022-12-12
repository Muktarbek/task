package m.muku.task.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tariff")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long action_id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Rate> rates;


}
