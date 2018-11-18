package br.ufma.lsdi.controller;

import br.ufma.lsdi.model.domain.auxiliar.CapabilityAuxiliar;
import br.ufma.lsdi.model.domain.auxiliar.CapabilityDataAuxiliar;
import br.ufma.lsdi.model.domain.auxiliar.Data;
import br.ufma.lsdi.model.domain.interscity.Capability;
import br.ufma.lsdi.service.interscity.CapabilityClient;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CapabilityController {

    private CapabilityClient capabilityClient;

    public CapabilityController(CapabilityClient capabilityClient) {
        this.capabilityClient = capabilityClient;
    }

    @RequestMapping(value = "/getCapabilities", method = RequestMethod.GET)
    public ResponseEntity<List<Capability>> getCapabilities() {
        CapabilityAuxiliar auxiliar = capabilityClient.getCapability();
        List<Capability> resources = auxiliar.getCapabilities();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @RequestMapping(value = "/saveCapability", method = RequestMethod.POST)
    public ResponseEntity<Capability> saveCapability(@RequestBody Capability capa) {
        Capability capability = capabilityClient.saveCapability(capa);
        return new ResponseEntity<>(capability, HttpStatus.OK);
    }

    @RequestMapping(value = "/getCapabilityByName/{name}", method = RequestMethod.GET)
    public ResponseEntity<Capability> getCapabilityByName(@PathVariable String name) {
        Capability capability = capabilityClient.getCapabilityByName(name.trim());
        return new ResponseEntity<>(capability, HttpStatus.OK);
    }

    @RequestMapping(value = "/sendDataToCapability/{uuid}/data/{capability}", method = RequestMethod.POST)
    public ResponseEntity<Data> sendDataToCapability(@PathVariable String uuid,
                                                     @PathVariable String capability,
                                                     @RequestBody Data data1) {
        Data data = capabilityClient.saveCapabilityData(data1,uuid,capability);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }


}
