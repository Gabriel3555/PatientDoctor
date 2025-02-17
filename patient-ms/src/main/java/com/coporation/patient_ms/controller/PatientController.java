package com.coporation.patient_ms.controller;

import com.coporation.patient_ms.domain.Patient;
import com.coporation.patient_ms.interfaces.IPatientService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final IPatientService patientService;

    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<Patient>> findAll() {
        return new ResponseEntity<>(patientService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/patientId/{id}")
    public ResponseEntity<Patient> findById(@PathVariable Long id) {
        return patientService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/doctorId/{id}")
    public ResponseEntity<List<Patient>> findByDoctor(@PathVariable Long id) {
        return new ResponseEntity<>(patientService.findByIdDoctor(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Patient> save(@RequestBody Patient patient) throws URISyntaxException {
        Patient savedPatient = patientService.save(patient);

        URI location = new URI("/api/patient" + savedPatient.getId());

        return ResponseEntity.created(location).body(savedPatient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Patient requestPatient) throws URISyntaxException {
        Patient foundPatient = patientService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));

        Optional.ofNullable(requestPatient)
                .map(Patient::getName)
                .ifPresent(foundPatient::setName);

        Optional.ofNullable(requestPatient)
                .map(Patient::getAge)
                .ifPresent(foundPatient::setAge);

        Optional.ofNullable(requestPatient)
                .map(Patient::getGender)
                .ifPresent(foundPatient::setGender);

        Optional.ofNullable(requestPatient)
                .map(Patient::getIdDoctor)
                .ifPresent(foundPatient::setIdDoctor);

        URI location = new URI("/api/patient" + foundPatient.getId());

        return ResponseEntity.ok().location(location).body(foundPatient);
    }

}
