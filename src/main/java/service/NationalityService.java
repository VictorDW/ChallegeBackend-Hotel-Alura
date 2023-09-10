package service;

import DTO.NationalityRequestDTO;
import model.Nationality;

import java.util.List;

public interface NationalityService {

    void createNationality(NationalityRequestDTO nationalityRequestDTO);
    List<NationalityRequestDTO> getAllNationality();
    Nationality getByIdNationality(NationalityRequestDTO nationalityRequestDTO);

}
