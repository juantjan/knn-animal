package ac.id.ukdw.juan.knnanimal.controller;

import ac.id.ukdw.juan.knnanimal.entity.AnimalAttributes;
import ac.id.ukdw.juan.knnanimal.service.KNNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Juan on 5/18/18.
 */
@RestController
@CrossOrigin(origins = "*")
public class KNNAnimalController {

    @Autowired
    KNNService knnService;

    @RequestMapping(method = RequestMethod.POST, value = "/animalTest", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> tryString(@RequestBody AnimalAttributes animalAttributes) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("result", knnService.test(animalAttributes));

        return map;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/newTraining", produces = MediaType.APPLICATION_JSON_VALUE)
    public String newTraining(@RequestHeader Integer k) throws Exception {
        knnService.training(k);
        return knnService.eval();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sayHi", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> tryHi() {
        Map<String, String> map = new HashMap<>();
        map.put("result", "Hi");

        return map;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sayAdsis", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> adsisWoi() {
        Map<String, String> map = new HashMap<>();
        map.put("result", "HALO SAYA ADSIS");

        return map;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/eval", produces = MediaType.APPLICATION_JSON_VALUE)
    public String eval() throws Exception {
//        Map<String, String> map = new HashMap<>();
//        map.put("result", knnService.eval());

        return knnService.eval();
    }
}
