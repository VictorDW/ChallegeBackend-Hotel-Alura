package controller;

import DAO.NationalityDAO;
import DAO.impl.NationalityDAOImpl;
import DTO.NationalityRequestDTO;
import service.NationalityService;
import service.impl.NationalityServiceImpl;
import util.JPAUtils;

import java.util.List;

public class NationalityController {

    private final NationalityService nationalityService;

    public NationalityController() {
        NationalityDAO nationalityDAO = new NationalityDAOImpl(JPAUtils.getEntityManager());
        this.nationalityService = new NationalityServiceImpl(nationalityDAO);
    }

    public void createNationality(NationalityRequestDTO nationalityRequestDTO) {
        this.nationalityService.createNationality(nationalityRequestDTO);
    }

    public List<NationalityRequestDTO> getAllNationality() {
        return nationalityService.getAllNationality();
    }
}
