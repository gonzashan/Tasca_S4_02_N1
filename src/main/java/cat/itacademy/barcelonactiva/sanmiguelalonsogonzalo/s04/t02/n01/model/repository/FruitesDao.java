package cat.itacademy.barcelonactiva.sanmiguelalonsogonzalo.s04.t02.n01.model.repository;

import cat.itacademy.barcelonactiva.sanmiguelalonsogonzalo.s04.t02.n01.model.domain.Fruita;
import org.springframework.data.repository.CrudRepository;

//  FruitesDao nos permite Integer?
public interface FruitesDao extends CrudRepository<Fruita, Integer> {

}