package cat.itacademy.barcelonactiva.sanmiguelalonsogonzalo.s04.t02.n01.model.services;

import cat.itacademy.barcelonactiva.sanmiguelalonsogonzalo.s04.t02.n01.model.domain.Fruita;
import cat.itacademy.barcelonactiva.sanmiguelalonsogonzalo.s04.t02.n01.model.repository.FruitesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class FruitesService implements IFruitesService {

    @Autowired
    private FruitesDao fruitesDao;


    public List<Fruita> getAll() {
        return (List<Fruita>) fruitesDao.findAll();
    }

    public Fruita save(Fruita newFruita) {
        return fruitesDao.save(newFruita);
    }

    public void delete(int id) throws EmptyResultDataAccessException {
            fruitesDao.deleteById(id);
    }


    public Fruita getOne(int id) throws EntityNotFoundException {

        Optional<Fruita> fruiteResponse = fruitesDao.findById(id);
        Fruita fruita = null;

        if(fruiteResponse.isPresent()) {
            fruita = fruiteResponse.get();

        }else {
            throw new EntityNotFoundException("No em trobat cap fruite amb id: " + id);
        }

        return fruita;
    }


}
