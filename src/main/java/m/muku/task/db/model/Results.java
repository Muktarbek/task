package m.muku.task.db.model;

import lombok.Getter;
import lombok.Setter;
import m.muku.task.db.entity.Partner;

import java.util.List;
@Setter
@Getter
public class Results {
    private List<Partner> results;
}
