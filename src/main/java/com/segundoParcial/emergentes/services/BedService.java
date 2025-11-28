package com.segundoParcial.emergentes.services;


import com.segundoParcial.emergentes.models.Bed;
import com.segundoParcial.emergentes.models.Pillow;
import com.segundoParcial.emergentes.repository.BedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BedService {
    @Autowired
    private BedRepository bedRepository;

    public List<Bed> getBeds() {
        return bedRepository.findAll();
    }

    public Bed getBedByCode(Long code) {
        return bedRepository.findById(code).orElse(null);
    }

    public Bed createBed(Bed bed) {
        if (bed.getPillows() != null) {
            for (Pillow pillow : bed.getPillows()) {
                pillow.setBed(bed);
            }
        }
        return bedRepository.save(bed);
    }


    public boolean updateBed(Long code, Bed bed) {
        if (bedRepository.existsById(code)) {
            bed.setCode(code);
            bedRepository.save(bed);
            return true;
        }
        return false;
    }

    public Optional<Bed> partiallyUpdateBed(Long code, Bed bed) {
        return bedRepository.findById(code).map(item -> {
            if (bed.getImage() != null) item.setImage(bed.getImage());
            if (bed.getLength() != 0) item.setLength(bed.getLength());
            if (bed.getWidth() != 0) item.setWidth(bed.getWidth());
            if (bed.getMaterial() != null) item.setMaterial(bed.getMaterial());
            return bedRepository.save(item);
        });
    }

    public boolean deleteBed(Long code) {
        if (!bedRepository.existsById(code)) {
            return false;
        }
        bedRepository.deleteById(code);
        return true;
    }

}
