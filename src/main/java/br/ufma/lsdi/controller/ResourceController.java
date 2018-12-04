package br.ufma.lsdi.controller;

import br.ufma.lsdi.model.domain.auxiliar.CapabilityDataAuxiliar;
import br.ufma.lsdi.model.domain.auxiliar.Data;
import br.ufma.lsdi.model.domain.auxiliar.ResourceAuxiliar;
import br.ufma.lsdi.model.domain.auxiliar.ResourceDataAuxiliar;
import br.ufma.lsdi.model.domain.interscity.Resource;
import br.ufma.lsdi.service.CapabiltyDataService;
import br.ufma.lsdi.service.interscity.CapabilityClient;
import br.ufma.lsdi.service.interscity.ResourceClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ResourceController {


    private ResourceClient resourceClient;
    private ResourceDataAuxiliar resourceAux;
    private Resource resource;

    //private CapabiltyDataService capabiltyDataService = new CapabiltyDataService();

    private CapabilityClient capabilityClient;

    public ResourceController(ResourceClient resourceClient, CapabilityClient capabilityClient) {
        this.resourceClient = resourceClient;
        this.capabilityClient = capabilityClient;
    }

    @RequestMapping(value = "/saveDataCapability/{uuid}/{capabilty}/{ano}", method = RequestMethod.GET)
    public ResponseEntity<Resource> saveDataByCapability(@PathVariable(value = "uuid") String uuid,
                                                         @PathVariable(value = "capabilty") String capability,
                                                         @PathVariable(value = "ano") String ano) {
        resourceAux = resourceClient.getResourceByUuid(uuid);


        CapabiltyDataService capabiltyDataService = new CapabiltyDataService();
        List<CapabilityDataAuxiliar> listData = new ArrayList<>();
        listData = capabiltyDataService.getDataByCapability(resourceAux.getData(),capability,ano);
        Data data = new Data();
        data.setData(listData);

        capabilityClient.saveCapabilityData(data, uuid, capability);

        return new ResponseEntity<>(resourceAux.getData(), HttpStatus.OK);
    }

/*
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

    @RequestMapping(value = "/saveResource", method = RequestMethod.POST)
    public ResponseEntity<Resource> saveDataCapability(@RequestBody ResourceDataAuxiliar aux) {
        ResourceDataAuxiliar auxiliar2 = resourceClient.saveResource(aux);
        Resource resource = auxiliar2.getData();
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
    */

    private static Resource getResource(){
        Resource resource = new Resource();
        resource.setDescription("ESTAÇÃO PRAIA PONTA DA AREA");
        resource.setUuid("70b8f4fe-3f17-4dcb-beff-cfb586fb344f");

        return resource;
    }

}
