

@RestController
public class HelloController {

    @GetMapping("/public")
    public String publicApi() {
        return "This is public";
    }

    @GetMapping("/private")
    public String privateApi() {
        return "This is private";
    }
}