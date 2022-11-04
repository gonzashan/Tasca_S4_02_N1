package cat.itacademy.barcelonactiva.sanmiguelalonsogonzalo.s04.t02.n01.model.services;

import cat.itacademy.barcelonactiva.sanmiguelalonsogonzalo.s04.t02.n01.model.domain.Fruita;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface IFruitesService {

    // Method showing all the records
    List<Fruita> getAll();

    // Method for saving a record
    Fruita save(Fruita newFruita);

    // Method to delete a record
    void delete(int id) throws EntityNotFoundException;

    // Method returning a record requested
    Fruita getOne(int id) throws RuntimeException;


}