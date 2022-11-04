package cat.itacademy.barcelonactiva.sanmiguelalonsogonzalo.s04.t02.n01.controllers;

import java.util.List;

import cat.itacademy.barcelonactiva.sanmiguelalonsogonzalo.s04.t02.n01.model.domain.Fruita;
import cat.itacademy.barcelonactiva.sanmiguelalonsogonzalo.s04.t02.n01.model.exception.ErrorDetails;
import cat.itacademy.barcelonactiva.sanmiguelalonsogonzalo.s04.t02.n01.model.exception.Message;
import cat.itacademy.barcelonactiva.sanmiguelalonsogonzalo.s04.t02.n01.model.exception.RequestOk;
import cat.itacademy.barcelonactiva.sanmiguelalonsogonzalo.s04.t02.n01.model.services.IFruitesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
public class FruitesController {


    @Autowired
    private IFruitesService fruitesService;

    //Mapping for get all records
    @GetMapping(value = "fruita/getAll")
    public List<Fruita> getAll() {
        return fruitesService.getAll();
    }


    //Mapping for get one record
    @RequestMapping(value = "fruita/getOne/{id}", method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<?> getOne(@PathVariable int id) {
        try {
            Fruita fruitaResponse = fruitesService.getOne(id);
            return new ResponseEntity<Fruita>(fruitaResponse,HttpStatus.OK);

        } catch (EntityNotFoundException ex){

            return new ResponseEntity<ErrorDetails>
                    (new ErrorDetails(500, id + " no se ha trobat."),HttpStatus.NOT_FOUND);
        }
    }


    //Mapping for addition records
    @PostMapping(path = "fruita/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<? extends Message> create(@RequestBody Fruita newFruita)   {

        Fruita fruita = fruitesService.save(newFruita);

        if (fruita == null) {
            return new ResponseEntity<ErrorDetails>
                    (new ErrorDetails(500, "No se ha pogut guardar la fruita."),HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<RequestOk>
                    (new RequestOk("La fruita se ha introduït correctament."),HttpStatus.CREATED);
        }
    }


    //Mapping for update records
    @PutMapping(path = "fruita/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<? extends Message> update(@RequestBody Fruita updateFruita)   {

        Fruita fruitaOld = fruitesService.getOne(updateFruita.getId());
        if(fruitaOld == null){
            return new ResponseEntity<ErrorDetails>
                    (new ErrorDetails(500, "No se ha pogut modificar la fruita amb el id: " + updateFruita.getId())
                            , HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            Fruita fruita = fruitesService.save(updateFruita);

            if (fruita == null) {
                return new ResponseEntity<ErrorDetails>
                        (new ErrorDetails(500, "No se ha pogut modificar la fruita."), HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return new ResponseEntity<RequestOk>
                        (new RequestOk("La fruita se ha modificat correctament."), HttpStatus.CREATED);
            }
        }
    }


    //Mapping for deleting record
    @RequestMapping(value = "/fruita/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public HttpEntity<? extends Message> deleteFruite(@PathVariable int id) {
        try {
            String fruit = fruitesService.getOne(id).getNom();
            fruitesService.delete(id);
            return new ResponseEntity<RequestOk>
                    (new RequestOk(fruit + " s'has eliminat correctament."),HttpStatus.OK);

        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<ErrorDetails>
                    (new ErrorDetails(500, id + " no se ha trobat."),HttpStatus.NOT_FOUND);
        }
    }


}