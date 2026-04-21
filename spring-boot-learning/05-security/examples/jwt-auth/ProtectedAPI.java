

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello secured API";
    }
}