package com.coporation.patient_ms.service;

import com.coporation.patient_ms.domain.Patient;
import com.coporation.patient_ms.interfaces.IPatientService;
import com.coporation.patient_ms.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IPatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> findAll() {
        return (List<Patient>) patientRepository.findAll();
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public List<Patient> findByIdDoctor(Long id) {
        return patientRepository.findPatientsByIdDoctor(id);
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void delete(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public Patient update(Patient patient) {
        return patientRepository.save(patient);
    }
}
