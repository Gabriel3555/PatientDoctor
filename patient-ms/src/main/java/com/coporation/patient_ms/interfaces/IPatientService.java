package com.coporation.patient_ms.interfaces;

import com.coporation.patient_ms.domain.Patient;

import java.util.List;
import java.util.Optional;

public interface IPatientService {

    List<Patient> findAll();

    Optional<Patient> findById(Long id);

    List<Patient> findByIdDoctor(Long id);

    Patient save(Patient patient);

    void delete(Long id);

    Patient update(Patient patient);
}
