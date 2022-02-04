package dyosim.forestfire.api.service;

import dyosim.forestfire.api.domain.ForestFire;
import dyosim.forestfire.api.dto.ForestFireResponse;
import dyosim.forestfire.api.repository.ForestFireRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ForestFireService {
    private final ForestFireRepository forestFireRepository;

    public ForestFireService(ForestFireRepository forestFireRepository) {
        this.forestFireRepository = forestFireRepository;
    }

    public ForestFireResponse readOne(int code){
        ForestFireResponse forestFireResponse = new ForestFireResponse();
        forestFireResponse.setData(forestFireRepository.findByCodeAndDateAfter(code, LocalDateTime.now()));
        System.out.println(forestFireResponse.getData().get(0)); // [ {2,2,}, {234, 4}]
        return forestFireResponse;
    }
}
