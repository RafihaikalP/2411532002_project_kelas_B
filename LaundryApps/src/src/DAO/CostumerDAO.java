package DAO;
import java.util.List;

import Model.Costumer;

public interface CostumerDAO {
    void save(Costumer costumer);
    List<Costumer> show();
    void delete(String id);
    void update(Costumer costumer);
}
