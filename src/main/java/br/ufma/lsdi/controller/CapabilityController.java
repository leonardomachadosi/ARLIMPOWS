package br.ufma.lsdi.controller;

import br.ufma.lsdi.model.domain.auxiliar.CapabilityAuxiliar;
import br.ufma.lsdi.model.domain.interscity.Capability;
import br.ufma.lsdi.service.interscity.CapabilityClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CapabilityController {

    private CapabilityClient capabilityClient;

    public CapabilityController(CapabilityClient capabilityClient) {
        this.capabilityClient = capabilityClient;
    }

    @RequestMapping(value = "/getCapabilities", method = RequestMethod.GET)
    public ResponseEntity<List<Capability>> getResources() {
        CapabilityAuxiliar auxiliar = capabilityClient.getCapability();
        List<Capability> resources = auxiliar.getCapabilities();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @RequestMapping(value = "/saveCapability", method = RequestMethod.POST)
    public ResponseEntity<Capability> saveResource(@RequestBody Capability capa) {
        Capability capability = capabilityClient.saveCapability(capa);
        return new ResponseEntity<>(capability, HttpStatus.OK);
    }

}
