package br.ufma.lsdi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TesteController {




//    @ApiOperation(
//            value = "Busca o Preso de acordo com os parâmetros informados",
//            response = Preso.class,
//            notes = "")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    code = 200,
//                    message = "Retorna um ResponseEntity com uma mensagem de sucesso",
//                    response = Preso.class
//            ),
//            @ApiResponse(
//                    code = 500,
//                    message = "Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
//                    response = Preso.class
//            )
//
//    })

    @RequestMapping(value = "/teste", method = RequestMethod.GET)
    public ResponseEntity<String> listarPresos() {

        return new ResponseEntity<>("Leonardo", HttpStatus.OK);
    }



}