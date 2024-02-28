package controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import discos.evaluado1.model.Disco;
import discos.evaluado1.repository.DiscoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/discos")

public class DiscoController {
	@Autowired
    private DiscoRepository discoRepository;

    @GetMapping
    public List<Disco> getAllDiscos() {
        return discoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disco> getDiscoById(@PathVariable Long id) {
        Disco disco = discoRepository.findById(id).orElse(null);
        if (disco != null) {
            return new ResponseEntity<>(disco, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Disco> createDisco(@RequestBody Disco disco) {
        if (disco.getNombreDisco() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Disco createdDisco = discoRepository.save(disco);
        return new ResponseEntity<>(createdDisco, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDisco(@PathVariable Long id, @RequestBody Disco disco) {
        if (!discoRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        disco.setId(id);
        discoRepository.save(disco);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisco(@PathVariable Long id) {
        if (!discoRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        discoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
