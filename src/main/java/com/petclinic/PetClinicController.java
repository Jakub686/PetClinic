package com.petclinic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/clinic")
public class PetClinicController {

    @Autowired
    PetClinicRepository petClinicRepository;

    @GetMapping("")
    public List<Owner> getAll() {
        return petClinicRepository.getAll();
    }

    @GetMapping("{id}")
    @ResponseStatus
    public Owner getById(@PathVariable("id") int id) {
        if (petClinicRepository.getById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return petClinicRepository.getById(id);
        }
    }

    @PostMapping("")
    public int add(@RequestBody List<Owner> owners) {
        return petClinicRepository.save(owners);
    }

    //put podmienia caly obiekt
    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Owner updatedOwner) {
        Owner owner = petClinicRepository.getById(id);

        if (owner != null) {
            owner.setName(updatedOwner.getName());
            petClinicRepository.update(owner);
            return 1;
        } else {
            return -1;
        }
    }

    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Owner updatedOwner) {
        Owner owner = petClinicRepository.getById(id);

        if (owner != null) {
            if (updatedOwner.getName() != null) owner.setName(updatedOwner.getName());

            petClinicRepository.update(owner);

            return 1;
        } else {
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id){
        return petClinicRepository.delete(id);
    }
}
