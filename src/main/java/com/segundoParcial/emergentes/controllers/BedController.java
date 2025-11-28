package com.segundoParcial.emergentes.controllers;

import com.segundoParcial.emergentes.models.Bed;
import com.segundoParcial.emergentes.repository.BedRepository;
import com.segundoParcial.emergentes.services.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/beds")
public class BedController {

    @Autowired
    private BedService bedService;

    @Autowired
    private BedRepository bedRepository;

    @GetMapping
    public ResponseEntity<List<Bed>> getBeds(){
        return ResponseEntity.ok(bedService.getBeds());
    }

    @GetMapping("/{code}")
    ResponseEntity<Bed> getBedByCode(@PathVariable Long code) {
        Bed bed = bedService.getBedByCode(code);
        if(bed == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bed);
    }

    @PostMapping
    ResponseEntity<?>postBed(@RequestBody Bed bed){
        Bed saved = bedService.createBed(bed);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{code}")
                .buildAndExpand(bed.getCode())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }


    @PutMapping("/{code}")
    ResponseEntity<?> putBed(@PathVariable Long code, @RequestBody Bed bed){
        boolean updateBed = bedService.updateBed(code, bed);
        if(!updateBed)
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{code}")
    public ResponseEntity<?> patchBed(@PathVariable Long code, @RequestBody Bed bed) {
        return bedService.partiallyUpdateBed(code, bed)
                .map(updated -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{code}")
    ResponseEntity<?> deleteBed(@PathVariable Long code){
        boolean result = bedService.deleteBed(code);
        if(!result)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.noContent().build();
    }
}
