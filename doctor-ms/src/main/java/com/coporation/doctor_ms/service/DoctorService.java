package com.coporation.doctor_ms.service;

import com.coporation.doctor_ms.client.PatientClient;
import com.coporation.doctor_ms.domain.Doctor;
import com.coporation.doctor_ms.dto.PatientDTO;
import com.coporation.doctor_ms.http.response.PatientByDoctorResponse;
import com.coporation.doctor_ms.interfaces.IDoctorService;
import com.coporation.doctor_ms.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService implements IDoctorService {

    private final DoctorRepository doctorRepository;
    private final PatientClient patientClient;

    public DoctorService(DoctorRepository doctorRepository, PatientClient patientClient) {
        this.doctorRepository = doctorRepository;
        this.patientClient = patientClient;
    }

    @Override
    public List<Doctor> findAll() {
        return (List<Doctor>) doctorRepository.findAll();
    }

    @Override
    public Optional<Doctor> findById(Long id) {
        return doctorRepository.findById(id);
    }

    @Override
    public PatientByDoctorResponse findPatientsByIdDoctor(Long id) {

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        List<PatientDTO> listPatients = patientClient.findPatientsByDoctorId(doctor.getId());

        return PatientByDoctorResponse.builder()
                .nameDoctor(doctor.getName())
                .patients(listPatients)
                .build();
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteById(Long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public Doctor update(Doctor doctor) {
        return doctorRepository.save(doctor);
    }
}
