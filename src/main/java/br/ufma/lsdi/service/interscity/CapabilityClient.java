package br.ufma.lsdi.service.interscity;

import br.ufma.lsdi.model.domain.auxiliar.CapabilityAuxiliar;
import br.ufma.lsdi.model.domain.interscity.Capability;
import br.ufma.lsdi.service.client.CapabilityClientFallback;
import br.ufma.lsdi.service.client.FeignInmateConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
        name = "capabilityClient",
        url = "http://cidadesinteligentes.lsdi.ufma.br",
        fallback = CapabilityClientFallback.class,
        configuration = FeignInmateConfiguration.class
)
public interface CapabilityClient {

    @RequestMapping(method = RequestMethod.GET, value = "/catalog/capabilities")
    CapabilityAuxiliar getCapability();

    @RequestMapping(method = RequestMethod.POST, value = "/catalog/capabilities")
    Capability saveCapability(@RequestBody Capability capability);

}
