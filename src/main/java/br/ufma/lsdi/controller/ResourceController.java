package br.ufma.lsdi.controller;

import br.ufma.lsdi.model.domain.auxiliar.ResourceAuxiliar;
import br.ufma.lsdi.model.domain.auxiliar.ResourceDataAuxiliar;
import br.ufma.lsdi.model.domain.interscity.Resource;
import br.ufma.lsdi.service.interscity.ResourceClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ResourceController {


    private ResourceClient resourceClient;

    public ResourceController(ResourceClient resourceClient) {
        this.resourceClient = resourceClient;
    }

    @RequestMapping(value = "/getResources", method = RequestMethod.GET)
    public ResponseEntity<List<Resource>> getResources() {
        ResourceAuxiliar auxiliar = resourceClient.getResource();
        List<Resource> resources = auxiliar.getResources();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @RequestMapping(value = "/getResourceByUuid/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<Resource> getResourceByUuid(@PathVariable String uuid) {
        ResourceDataAuxiliar auxiliar = resourceClient.getResourceByUuid(uuid);
        Resource resource = auxiliar.getData();
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }


    @RequestMapping(value = "/saveResource", method = RequestMethod.POST)
    public ResponseEntity<Resource> saveResource(@RequestBody ResourceDataAuxiliar aux) {
        ResourceDataAuxiliar auxiliar2 = resourceClient.saveResource(aux);
        Resource resource = auxiliar2.getData();
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

}
