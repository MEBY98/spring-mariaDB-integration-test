package com.example.meby98.testWithMariaDB;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/country")
public class CountryRestController {
    @Autowired
    CountryService countryService;

    @GetMapping
    public List<CountryModel> getAll() {
        return this.countryService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<CountryModel> getById(@PathVariable(name = "id") Long id) {
        return this.countryService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Long> add(@Valid @RequestBody CountryRequest newCountry) {
        return new ResponseEntity<>(this.countryService.create(newCountry), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable(name = "id") Long id, @RequestBody CountryRequest updatedCountry) {
        return this.countryService.update(id, updatedCountry);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        this.countryService.delete(id);
    }
}
