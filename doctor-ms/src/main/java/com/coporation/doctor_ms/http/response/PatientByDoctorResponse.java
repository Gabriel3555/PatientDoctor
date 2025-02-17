package com.coporation.doctor_ms.http.response;

import com.coporation.doctor_ms.dto.PatientDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientByDoctorResponse {

    private String nameDoctor;
    private List<PatientDTO> patients;

}
