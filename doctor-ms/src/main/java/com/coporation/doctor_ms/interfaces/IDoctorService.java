package com.coporation.doctor_ms.interfaces;

import com.coporation.doctor_ms.domain.Doctor;
import com.coporation.doctor_ms.http.response.PatientByDoctorResponse;

import java.util.List;
import java.util.Optional;

public interface IDoctorService {

    List<Doctor> findAll();

    Optional<Doctor> findById(Long id);

    PatientByDoctorResponse findPatientsByIdDoctor(Long id);

    Doctor save(Doctor doctor);

    void deleteById(Long id);

    Doctor update(Doctor doctor);

}
