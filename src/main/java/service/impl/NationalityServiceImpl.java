package service.impl;

import DAO.NationalityDAO;
import DTO.NationalityRequestDTO;
import model.Nationality;
import service.NationalityService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NationalityServiceImpl implements NationalityService {

    private final NationalityDAO nationalityDAO;

    public NationalityServiceImpl(NationalityDAO nationalityDAO) {
        this.nationalityDAO = nationalityDAO;
    }

    @Override
    public void createNationality(NationalityRequestDTO nationalityRequestDTO) {

        Nationality nationality = new Nationality();
        nationality.setGentilicioMan(nationalityRequestDTO.getGentilicioMan());
        nationality.setGentilicioWoman(nationalityRequestDTO.getGentilicioWoman());

        nationalityDAO.update(nationality);
    }

    @Override
    public List<NationalityRequestDTO> getAllNationality() {

        List<Nationality> nationalityList = nationalityDAO.getAll();
        List<NationalityRequestDTO> list = new ArrayList<>();

        nationalityList.forEach(nationality ->
            list.add(
                    new NationalityRequestDTO(
                            nationality.getId(),
                            nationality.getGentilicioMan(),
                            nationality.getGentilicioWoman()
                    )
            )
        );

        return list;
    }

    @Override
    public Nationality getByIdNationality(NationalityRequestDTO nationalityRequestDTO) {

        return nationalityDAO.getById(nationalityRequestDTO.getId());
    }

    @Override
    public Boolean nationalityExists() {

        Optional<Nationality> nationalityList = nationalityDAO.getAll().stream().findFirst();

        return nationalityList.isEmpty();
    }


}
