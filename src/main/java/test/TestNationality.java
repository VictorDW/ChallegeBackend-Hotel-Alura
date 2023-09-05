package test;

import DTO.NationalityRequestDTO;
import controller.NationalityController;

import java.util.ArrayList;
import java.util.List;

public class TestNationality {
    public static void main(String[] args) {

          List<NationalityRequestDTO> nationality = new ArrayList<>(){{
              add( new NationalityRequestDTO("afgano","afgana"));
              add( new NationalityRequestDTO("alemán","alemana"));
              add(new NationalityRequestDTO("árabe", "árabe"));
              add(new NationalityRequestDTO("argentino","argentina"));
              add(new NationalityRequestDTO("australiano","australiana"));
              add(new NationalityRequestDTO("belga","belga"));
              add(new NationalityRequestDTO("boliviano","boliviana"));
              add(new NationalityRequestDTO("brasileño","brasileña"));
              add(new NationalityRequestDTO("camboyano","camboyana"));
              add(new NationalityRequestDTO("canadiense","canadiense"));
              add(new NationalityRequestDTO("chileno","chilena"));
              add(new NationalityRequestDTO("chino","china"));
              add(new NationalityRequestDTO("coreano","coreana"));
              add(new NationalityRequestDTO("costarricense","costarricense"));
              add(new NationalityRequestDTO("cubano","cubana"));
              add(new NationalityRequestDTO("danés","danesa"));
              add(new NationalityRequestDTO("ecuatoriano","ecuatoriana"));
              add(new NationalityRequestDTO("egipcio","egipcia"));
              add(new NationalityRequestDTO("salvadoreño","salvadoreña"));
              add(new NationalityRequestDTO("escocés","escocesa"));
              add(new NationalityRequestDTO("español","española"));
              add(new NationalityRequestDTO("estadounidense","estadounidense"));
              add(new NationalityRequestDTO("estonio","estonia"));
              add(new NationalityRequestDTO("etiope","etiope"));
              add(new NationalityRequestDTO("filipino","filipina"));
              add(new NationalityRequestDTO("finlandés","finlandesa"));
              add(new NationalityRequestDTO("francés","francesa"));
              add(new NationalityRequestDTO("galés","galesa"));
              add(new NationalityRequestDTO("griego","griega"));
              add(new NationalityRequestDTO("guatemalteco","guatemalteca"));
              add(new NationalityRequestDTO("haitiano","haitiana"));
              add(new NationalityRequestDTO("holandés","holandesa"));
              add(new NationalityRequestDTO("hondureño","hondureña"));
              add(new NationalityRequestDTO("indonés","indonesa"));
              add(new NationalityRequestDTO("inglés","inglesa"));
              add(new NationalityRequestDTO("iraquí","iraquí"));
              add(new NationalityRequestDTO("iraní","iraní"));
              add(new NationalityRequestDTO("irlandés","irlandesa"));
              add(new NationalityRequestDTO("israelí","israelí"));
              add(new NationalityRequestDTO("italiano","italiana"));
              add(new NationalityRequestDTO("japonés","japonesa"));
              add(new NationalityRequestDTO("jordano","jordana"));
              add(new NationalityRequestDTO("laosiano","laosiana"));
              add(new NationalityRequestDTO("letón","letona"));
              add(new NationalityRequestDTO("letonés","letonesa"));
              add(new NationalityRequestDTO("malayo","malaya"));
              add(new NationalityRequestDTO("marroquí","marroquí"));
              add(new NationalityRequestDTO("mexicano","mexicana"));
              add(new NationalityRequestDTO("nicaragüense","nicaragüense"));
              add(new NationalityRequestDTO("noruego","noruega"));
              add(new NationalityRequestDTO("neozelandés","neozelandesa"));
              add(new NationalityRequestDTO("panameño","panameña"));
              add(new NationalityRequestDTO("paraguayo","paraguaya"));
              add(new NationalityRequestDTO("peruano","peruana"));
              add(new NationalityRequestDTO("polaco","polaca"));
              add(new NationalityRequestDTO("portugués","portuguesa"));
              add(new NationalityRequestDTO("puertorriqueño","puertorriqueño"));
              add(new NationalityRequestDTO("dominicano","dominicana"));
              add(new NationalityRequestDTO("rumano","rumana"));
              add(new NationalityRequestDTO("ruso","rusa"));
              add(new NationalityRequestDTO("sueco","sueca"));
              add(new NationalityRequestDTO("suizo","suiza"));
              add(new NationalityRequestDTO("tailandés","tailandesa"));
              add(new NationalityRequestDTO("taiwanes","taiwanesa"));
              add(new NationalityRequestDTO("turco","turca"));
              add(new NationalityRequestDTO("ucraniano","ucraniana"));
              add(new NationalityRequestDTO("uruguayo","uruguaya"));
              add(new NationalityRequestDTO("venezolano","venezolana"));
              add(new NationalityRequestDTO("vietnamita","vietnamita"));
          }};

        NationalityController nationalityController = new NationalityController();

        //nationality.forEach(nationalityController::createNationality);

       // nationalityController.getAllNationality().forEach(System.out::println);

    }
}
