package com.coporation.doctor_ms.client;

import com.coporation.doctor_ms.dto.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-patient", url = "localhost:8080/api/patient")
public interface PatientClient {
    @GetMapping("/doctorId/{id}")
    public List<PatientDTO> findPatientsByDoctorId(@PathVariable Long id);
}
