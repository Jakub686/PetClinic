package com.petclinic;

import com.petclinic.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PetClinicRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Owner> getAll() {
        return jdbcTemplate.query("SELECT id, name FROM owner"
                , BeanPropertyRowMapper.newInstance(Owner.class));
    }

    public Owner getById(int id) {
        return jdbcTemplate.queryForObject("SELECT id, name, FROM owner WHERE " + "id=?", BeanPropertyRowMapper.newInstance(Owner.class), id);
    }

    public int save(List<Owner> owners) {
        owners.forEach(owner -> jdbcTemplate
                .update("INSERT INTO owner(name) VALUES(?)",
                        owner.getName()));
        return 1;
    }

    public int update(Owner owner){
        return jdbcTemplate.update("UPDATE owner SET name=? WHERE id=?",
                owner.getName(), owner.getId());
    }

    public int delete(int id){
        return jdbcTemplate.update("DELETE FROM owner WHERE id=?",id);
    }

}