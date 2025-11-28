package com.segundoParcial.emergentes.controllers;
import com.segundoParcial.emergentes.models.Pillow;
import com.segundoParcial.emergentes.repository.PillowRepository;
import com.segundoParcial.emergentes.services.PillowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pillows")
public class PillowController {

    @Autowired
    private PillowService pillowService;

    @Autowired
    private PillowRepository pillowRepository;

    @GetMapping
    public ResponseEntity<List<Pillow>> getPillows(){
        return ResponseEntity.ok(pillowService.getPillows());
    }

    @GetMapping("/{code}")
    ResponseEntity<Pillow> getPillowByCode(@PathVariable Long code) {
        Pillow pillow = pillowService.getPillowsByCode(code);
        if(pillow == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pillow);
    }

    @PostMapping
    ResponseEntity<?>postPillow(@RequestBody Pillow pillow){
        Pillow saved = pillowService.createPilllow(pillow);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{code}")
                .buildAndExpand(pillow.getPillowCode())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }


    @PutMapping("/{code}")
    ResponseEntity<?> putPillow(@PathVariable Long code, @RequestBody Pillow pillow){
        boolean updatePillow = pillowService.updatePillow(code, pillow);
        if(!updatePillow)
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{code}")
    public ResponseEntity<?> patchPillow(@PathVariable Long code, @RequestBody Pillow pillow) {
        return pillowService.partiallyUpdatePillow(code, pillow)
                .map(updated -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{code}")
    ResponseEntity<?> deletePillow(@PathVariable Long code){
        boolean result = pillowService.deletePillow(code);
        if(!result)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.noContent().build();
    }

}
