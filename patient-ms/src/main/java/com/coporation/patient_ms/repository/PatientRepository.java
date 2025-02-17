package com.coporation.patient_ms.repository;

import com.coporation.patient_ms.domain.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {

    List<Patient> findPatientsByIdDoctor(Long id);

}
