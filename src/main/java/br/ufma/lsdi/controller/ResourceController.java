package br.ufma.lsdi.controller;

import br.ufma.lsdi.model.domain.Catalog;
import br.ufma.lsdi.model.domain.auxiliar.CapabilityDataAuxiliar;
import br.ufma.lsdi.model.domain.auxiliar.Data;
import br.ufma.lsdi.model.domain.auxiliar.ResourceHelper;
import br.ufma.lsdi.model.domain.auxiliar.ResourceDataAuxiliar;
import br.ufma.lsdi.model.domain.interscity.Resource;
import br.ufma.lsdi.service.CapabiltyDataService;
import br.ufma.lsdi.service.interscity.CapabilityClient;
import br.ufma.lsdi.service.interscity.ResourceClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        try {
            listData = capabiltyDataService.getDataByCapability(resourceAux.getData(), capability, ano);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Data data = new Data();
        data.setData(listData);

        Data d = capabilityClient.saveCapabilityData(data, uuid, capability);

        return new ResponseEntity<>(resourceAux.getData(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllData", method = RequestMethod.POST)
    public ResponseEntity<ResourceHelper> getAllDataByCapability(@RequestBody Catalog catalog) {
        ResourceHelper resourceHelper = resourceClient.getAllData(catalog);
        return new ResponseEntity<>(resourceHelper, HttpStatus.OK);
    }
}
