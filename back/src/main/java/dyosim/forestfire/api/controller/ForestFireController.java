package dyosim.forestfire.api.controller;

import dyosim.forestfire.api.domain.ForestFire;
import dyosim.forestfire.api.dto.ForestFireResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import dyosim.forestfire.api.service.ForestFireService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class ForestFireController {

    private final ForestFireService forestFireService;

    public ForestFireController(ForestFireService forestFireService) {
        this.forestFireService = forestFireService;
    }

    @GetMapping("/forestfire")
    public ForestFireResponse findForestFire(
        @RequestParam(value = "code")int code){
            return forestFireService.readOne(code);
    }

    @GetMapping("/forestfireall")
    public ForestFireResponse findForestFireAll(){
        return forestFireService.readAll();
    }
}
