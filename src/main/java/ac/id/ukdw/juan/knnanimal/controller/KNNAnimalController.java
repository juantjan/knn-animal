package ac.id.ukdw.juan.knnanimal.controller;

import ac.id.ukdw.juan.knnanimal.entity.AnimalAttributes;
import ac.id.ukdw.juan.knnanimal.service.KNNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Juan on 5/18/18.
 */
@RestController
public class KNNAnimalController {

    @Autowired
    KNNService knnService;

    @RequestMapping(method = RequestMethod.POST, value = "/animalTest", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> tryString(@RequestBody AnimalAttributes animalAttributes) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("result", knnService.test(animalAttributes));

        return map;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sayHi", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> tryHi(@RequestBody AnimalAttributes animalAttributes) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("result", "Hi");

        return map;
    }
}
