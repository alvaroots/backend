package com.segundoParcial.emergentes.services;

import com.segundoParcial.emergentes.models.Pillow;
import com.segundoParcial.emergentes.repository.PillowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PillowService {
@Autowired
    private PillowRepository pillowRepository;

    public List<Pillow> getPillows(){
        return pillowRepository.findAll();
    }

    public Pillow getPillowsByCode(Long code) {
        return pillowRepository.findById(code).orElse(null);
    }

    public Pillow createPilllow(Pillow pillow){
        pillowRepository.save(pillow);
        return pillow;
    }

    public boolean updatePillow(Long code, Pillow pillow){
        if(pillowRepository.existsById(code)){
            pillow.setPillowCode(code);
            pillowRepository.save(pillow);
            return true;
        }
        return false;
    }

    public Optional<Pillow> partiallyUpdateStudent(Long code, Pillow pillow) {
        return pillowRepository.findById(code).map(item -> {
            if (pillow.getColor() != null) item.setColor(pillow.getColor());
            if (pillow.getImage() != null) item.setImage(pillow.getImage());
            if (pillow.getPrice() != 0) item.setPrice(pillow.getPrice());
            if (pillow.getLength() != 0) item.setLength(pillow.getLength());
            if (pillow.getWidth() != 0) item.setWidth(pillow.getWidth());
            return pillowRepository.save(item);
        });
    }

    public boolean deleteStudent(Long code) {
        if (!pillowRepository.existsById(code)) {
            return false;
        }
        pillowRepository.deleteById(code);
        return true;
    }

}