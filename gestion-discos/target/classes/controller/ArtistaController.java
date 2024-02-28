package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import discos.evaluado1.model.Artista;
import discos.evaluado1.repository.ArtistaRepository;

import java.util.List;

@RestController
@RequestMapping("/api/artistas")

public class ArtistaController {

	 @Autowired
	    private ArtistaRepository artistaRepository;

	    @GetMapping
	    public List<Artista> getAllArtistas() {
	        return artistaRepository.findAll();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Artista> getArtistaById(@PathVariable Long id) {
	        Artista artista = artistaRepository.findById(id).orElse(null);
	        if (artista != null) {
	            return new ResponseEntity<>(artista, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    @PostMapping
	    public ResponseEntity<Artista> createArtista(@RequestBody Artista artista) {
	        if (artista.getNombreArtista() == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	        Artista createdArtista = artistaRepository.save(artista);
	        return new ResponseEntity<>(createdArtista, HttpStatus.CREATED);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Void> updateArtista(@PathVariable Long id, @RequestBody Artista artista) {
	        if (!artistaRepository.existsById(id)) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        artista.setId(id);
	        artistaRepository.save(artista);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteArtista(@PathVariable Long id) {
	        if (!artistaRepository.existsById(id)) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        artistaRepository.deleteById(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
}
