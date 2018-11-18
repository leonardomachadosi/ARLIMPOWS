package br.ufma.lsdi.service.interscity;

import br.ufma.lsdi.model.domain.auxiliar.ResourceAuxiliar;
import br.ufma.lsdi.model.domain.auxiliar.ResourceDataAuxiliar;
import br.ufma.lsdi.service.client.FeignInmateConfiguration;
import br.ufma.lsdi.service.client.ResourceClientFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "resourceClient",
        url = "http://cidadesinteligentes.lsdi.ufma.br",
        fallback = ResourceClientFallback.class,
        configuration = FeignInmateConfiguration.class
)
public interface ResourceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/catalog/resources")
    ResourceAuxiliar getResource();

    @RequestMapping(method = RequestMethod.POST, value = "/catalog/resources")
    ResourceDataAuxiliar saveResource(@RequestBody ResourceDataAuxiliar data);

    @RequestMapping(method = RequestMethod.GET, value = "/catalog/resources/{uuid}")
    ResourceDataAuxiliar getResourceByUuid(@PathVariable("uuid") String uuid);

}
