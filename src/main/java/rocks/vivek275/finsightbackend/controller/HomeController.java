package rocks.vivek275.finsightbackend.controller;


import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public ResponseEntity<String> home() {
        return new ResponseEntity<>("Hello Welcome to Backend Service of FinSight", HttpStatus.OK);
    }
}
