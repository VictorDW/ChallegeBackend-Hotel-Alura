package test;

import DTO.NationalityRequestDTO;
import controller.NationalityController;

import java.util.List;

public class TestNationality {
    public void initializeNationality() {

        NationalityController nationalityController = new NationalityController();

        if(nationalityController.nationalityExists())
            nationalities().forEach(nationalityController::createNationality);
    }

    public static List<NationalityRequestDTO> nationalities() {

        return List.of(
                (new NationalityRequestDTO("afgano","afgana")),
                (new NationalityRequestDTO("alemán","alemana")),
                (new NationalityRequestDTO("árabe", "árabe")),
                (new NationalityRequestDTO("argentino","argentina")),
                (new NationalityRequestDTO("australiano","australiana")),
                (new NationalityRequestDTO("belga","belga")),
                (new NationalityRequestDTO("boliviano","boliviana")),
                (new NationalityRequestDTO("brasileño","brasileña")),
                (new NationalityRequestDTO("camboyano","camboyana")),
                (new NationalityRequestDTO("canadiense","canadiense")),
                (new NationalityRequestDTO("chileno","chilena")),
                (new NationalityRequestDTO("chino","china")),
                (new NationalityRequestDTO("coreano","coreana")),
                (new NationalityRequestDTO("costarricense","costarricense")),
                (new NationalityRequestDTO("cubano","cubana")),
                (new NationalityRequestDTO("danés","danesa")),
                (new NationalityRequestDTO("ecuatoriano","ecuatoriana")),
                (new NationalityRequestDTO("egipcio","egipcia")),
                (new NationalityRequestDTO("salvadoreño","salvadoreña")),
                (new NationalityRequestDTO("escocés","escocesa")),
                (new NationalityRequestDTO("español","española")),
                (new NationalityRequestDTO("estadounidense","estadounidense")),
                (new NationalityRequestDTO("estonio","estonia")),
                (new NationalityRequestDTO("etiope","etiope")),
                (new NationalityRequestDTO("filipino","filipina")),
                (new NationalityRequestDTO("finlandés","finlandesa")),
                (new NationalityRequestDTO("francés","francesa")),
                (new NationalityRequestDTO("galés","galesa")),
                (new NationalityRequestDTO("griego","griega")),
                (new NationalityRequestDTO("guatemalteco","guatemalteca")),
                (new NationalityRequestDTO("haitiano","haitiana")),
                (new NationalityRequestDTO("holandés","holandesa")),
                (new NationalityRequestDTO("hondureño","hondureña")),
                (new NationalityRequestDTO("indonés","indonesa")),
                (new NationalityRequestDTO("inglés","inglesa")),
                (new NationalityRequestDTO("iraquí","iraquí")),
                (new NationalityRequestDTO("iraní","iraní")),
                (new NationalityRequestDTO("irlandés","irlandesa")),
                (new NationalityRequestDTO("israelí","israelí")),
                (new NationalityRequestDTO("italiano","italiana")),
                (new NationalityRequestDTO("japonés","japonesa")),
                (new NationalityRequestDTO("jordano","jordana")),
                (new NationalityRequestDTO("laosiano","laosiana")),
                (new NationalityRequestDTO("letón","letona")),
                (new NationalityRequestDTO("letonés","letonesa")),
                (new NationalityRequestDTO("malayo","malaya")),
                (new NationalityRequestDTO("marroquí","marroquí")),
                (new NationalityRequestDTO("mexicano","mexicana")),
                (new NationalityRequestDTO("nicaragüense","nicaragüense")),
                (new NationalityRequestDTO("noruego","noruega")),
                (new NationalityRequestDTO("neozelandés","neozelandesa")),
                (new NationalityRequestDTO("panameño","panameña")),
                (new NationalityRequestDTO("paraguayo","paraguaya")),
                (new NationalityRequestDTO("peruano","peruana")),
                (new NationalityRequestDTO("polaco","polaca")),
                (new NationalityRequestDTO("portugués","portuguesa")),
                (new NationalityRequestDTO("puertorriqueño","puertorriqueño")),
                (new NationalityRequestDTO("dominicano","dominicana")),
                (new NationalityRequestDTO("rumano","rumana")),
                (new NationalityRequestDTO("ruso","rusa")),
                (new NationalityRequestDTO("sueco","sueca")),
                (new NationalityRequestDTO("suizo","suiza")),
                (new NationalityRequestDTO("tailandés","tailandesa")),
                (new NationalityRequestDTO("taiwanes","taiwanesa")),
                (new NationalityRequestDTO("turco","turca")),
                (new NationalityRequestDTO("ucraniano","ucraniana")),
                (new NationalityRequestDTO("uruguayo","uruguaya")),
                (new NationalityRequestDTO("venezolano","venezolana")),
                (new NationalityRequestDTO("vietnamita","vietnamita"))
        );
    }
}
