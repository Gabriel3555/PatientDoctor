package com.coporation.doctor_ms.controller;

import com.coporation.doctor_ms.domain.Doctor;
import com.coporation.doctor_ms.interfaces.IDoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    private final IDoctorService doctorService;

    public DoctorController(IDoctorService doctorService) {
        this.doctorService = doctorService;
    }

    public ResponseEntity<List<Doctor>> findAllDoctors() {
        return new ResponseEntity<>(doctorService.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Doctor> findDoctorById(Long id) {
        return doctorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> findPatientsByDoctorId(Long id) {
        return ResponseEntity.ok(doctorService.findPatientsByIdDoctor(id));
    }

    public ResponseEntity<Doctor> saveDoctor(Doctor doctor) throws URISyntaxException {
        Doctor savedDoctor = doctorService.save(doctor);

        URI location = new URI("/api/doctor/" + savedDoctor.getId());

        return ResponseEntity.created(location).body(savedDoctor);
    }

    public ResponseEntity<Void> deleteDoctorById(Long id) {
        doctorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, Doctor doctor) throws URISyntaxException {
        Doctor foundDoctor = doctorService.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));

        Optional.ofNullable(doctor)
                .map(Doctor::getName)
                .ifPresent(foundDoctor::setName);

        Optional.ofNullable(doctor)
                .map(Doctor::getEmail)
                .ifPresent(foundDoctor::setEmail);

        Optional.ofNullable(doctor)
                .map(Doctor::getPhone)
                .ifPresent(foundDoctor::setPhone);

        URI location = new URI("/api/doctor/" + id);

        return ResponseEntity.created(location).body(foundDoctor);
    }
}
