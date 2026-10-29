# Spring Boot Annotations — எளிய தமிழில், ஆழமான புரிதல்

Java மற்றும் Spring Boot interview-க்கு நீங்கள் தெரிந்து வைத்திருக்க வேண்டிய annotations பற்றிய practical வழிகாட்டி.

> **முக்கிய கருத்து:** Annotation என்பது ஒரு லேபிள் (label) போன்றது. இது Java, Spring அல்லது Spring MVC-க்கு அறிவுறுத்தல்களை (instructions) அல்லது கூடுதல் தகவலை (metadata) கொடுக்கும். Framework அந்த லேபிள்களைப் படித்து, அதற்கு ஏற்றபடி செயல்படும்.
>
> எடுத்துக்காட்டு: `@RestController` என்பது Spring-க்கு இப்படிச் சொல்கிறது: “இந்த class-ஐ ஒரு web controller ஆக எடுத்துக்கொள். இதன் methods பொதுவாக response data-வைத் திருப்பித் தரும்.”

---

## 1. முதலில், Spring எப்படி வேலை செய்கிறது என்று புரிந்துகொள்வோம்

நீங்கள் ஒரு banking application உருவாக்குகிறீர்கள் என்று வைத்துக்கொள்ளுங்கள். அதில் accounts, customers, payments, APIs ஆகியவற்றுக்கான classes இருக்கும்.

Spring இல்லாமல், objects-ஐ நாமே கையால் உருவாக்க வேண்டும்:

```java
AccountRepository repository = new AccountRepository();
AccountService service = new AccountService(repository);
AccountController controller = new AccountController(service);
```

Application பெரிதாக வளர வளர, ஒவ்வொரு object-ஐயும் கையால் உருவாக்கி, ஒன்றோடு ஒன்று இணைப்பது மிகவும் கடினமாகிவிடும்.

Spring உங்களுக்காக objects-ஐ உருவாக்கி, நிர்வகிக்கும் (manage செய்யும்). Spring நிர்வகிக்கும் object-ஐ **Spring bean** என்று அழைக்கிறோம்.

```text
Application தொடங்குகிறது
       |
       v
@SpringBootApplication
       |
       v
Spring, components மற்றும் configuration-ஐத் தேடுகிறது (scan)
       |
       v
Spring, beans-ஐ உருவாக்கி configure செய்கிறது
       |
       v
Dependencies மற்ற beans-க்குள் inject செய்யப்படுகின்றன
       |
       v
Application, requests-ஐக் கையாளத் தயார்
```

இது எளிமைப்படுத்தப்பட்ட பார்வை மட்டுமே. உண்மையில் bean creation மற்றும் dependency injection இன்னும் விரிவான lifecycle-இல் நடக்கும்.

### Dependency Injection (DI) என்றால் என்ன?

`AccountService`-க்கு ஒரு `AccountRepository` தேவை என்று வைத்துக்கொள்ளுங்கள்.

`AccountService` தனக்குத் தேவையான repository-ஐத் தானே உருவாக்குவதற்குப் பதிலாக, Spring அந்த repository object-ஐ அதற்குக் கொடுத்துவிடும். இதுதான் **dependency injection**.

```text
AccountController
       |
       v
AccountService
       |
       v
AccountRepository
```

**நினைவில் வைத்துக்கொள்ளுங்கள்:**
- **IoC (Inversion of Control):** Objects-ஐ உருவாக்கி நிர்வகிக்கும் பொறுப்பை Spring எடுத்துக்கொள்கிறது.
- **DI (Dependency Injection):** ஒரு class-க்குத் தேவையான objects-ஐ Spring வழங்குகிறது.
- **Bean:** Spring நிர்வகிக்கும் object.
- **ApplicationContext:** Beans-ஐயும் மற்ற application configuration-ஐயும் நிர்வகிக்கும் Spring container.

---

# 2. Bean மற்றும் dependency தொடர்பான annotations

## `@Component`

**எளிய விளக்கம்:** “Spring, இந்த class-ஐக் கண்டுபிடி, இதன் object-ஐ நிர்வகி.”

```java
import org.springframework.stereotype.Component;

@Component
public class NotificationHelper {
    public void send() {
        System.out.println("Notification sent");
    }
}
```

Component scanning இந்த class-ஐக் கண்டுபிடிக்கும்போது, Spring இதை ஒரு bean ஆகப் பதிவு (register) செய்யும்.

இங்கே என்ன நடக்கும்?
- Spring இந்த class-ஐக் கண்டுபிடிக்கும்.
- அதன் object-ஐ create செய்யும்.
- அந்த object-ஐ manage செய்யும்.

**எப்போது பயன்படுத்துவது:** Service, repository, controller என்று குறிப்பிட்டுச் சொல்ல முடியாத, பொதுவான helper அல்லது class-க்கு.

**நினைவில் வைத்துக்கொள்ளுங்கள்:** `@Component` என்பது ஒரு பொதுவான (general-purpose) stereotype annotation. இது **class-க்கு** போடுவது.

## `@Autowired`

**எளிய விளக்கம்:** “Spring, எனக்குத் தேவையான dependency-ஐ inject செய்.”

Constructor injection பொதுவாகப் பரிந்துரைக்கப்படும் முறை:

```java
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final NotificationHelper notificationHelper;

    public AccountService(NotificationHelper notificationHelper) {
        this.notificationHelper = notificationHelper;
    }
}
```

இந்த class-இல் ஒரே ஒரு constructor மட்டுமே இருப்பதால், `@Autowired` இல்லாமலேயே Spring அதை injection-க்குப் பயன்படுத்திக்கொள்ளும்.

நீங்கள் இப்படியும் எழுதலாம்:

```java
@Autowired
public AccountService(NotificationHelper notificationHelper) {
    this.notificationHelper = notificationHelper;
}
```

**Constructor injection-ஐ விரும்புங்கள்.** ஏனென்றால்: தேவையான dependencies தெளிவாகத் தெரியும், fields-ஐ `final` ஆக வைக்கலாம், class-ஐ test செய்வதும் எளிது.

**முக்கியம்:** `@Autowired`, இல்லாத (missing) implementation-ஐ மாயமாக உருவாக்காது. Inject செய்வதற்குப் பொருத்தமான bean ஒன்றை Spring கண்டுபிடிக்க முடிய வேண்டும்.

## `@Qualifier`

**எளிய விளக்கம்:** “ஒன்றுக்கு மேற்பட்ட beans பொருந்தினால், இந்தப் பெயருடைய (qualifier) bean-ஐ inject செய்.”

உங்களிடம் இரண்டு payment implementations இருக்கின்றன என்று வைத்துக்கொள்ளுங்கள்:

```java
@Component("upiPayment")
class UpiPayment implements Payment {
}

@Component("cardPayment")
class CardPayment implements Payment {
}
```

தேவையானதை inject செய்யுங்கள்:

```java
@Service
class CheckoutService {
    private final Payment payment;

    CheckoutService(@Qualifier("upiPayment") Payment payment) {
        this.payment = payment;
    }
}
```

**எப்போது பயன்படுத்துவது:** ஒரே type-க்கு ஒன்றுக்கு மேற்பட்ட beans பொருந்தும்போது, எதை inject செய்ய வேண்டும் என்று குறிப்பிட.

## `@Primary`

**எளிய விளக்கம்:** “பல beans பொருந்தும்போது, இந்த bean-ஐ default ஆகப் பயன்படுத்து.”

```java
@Component
@Primary
class UpiPayment implements Payment {
}

@Component
class CardPayment implements Payment {
}
```

இப்போது qualifier இல்லாமல் `Payment`-ஐ inject செய்தால், பொதுவாக `UpiPayment` தேர்ந்தெடுக்கப்படும்.

**`@Primary` vs `@Qualifier`:**
- `@Primary`: விரும்பத்தக்க default-ஐ நிர்ணயிக்கிறது.
- `@Qualifier`: Injection நடக்கும் இடத்தில் தேர்வைக் குறுக்குகிறது (narrow செய்கிறது).
- பொருந்தும் `@Qualifier` இருந்தால், primary default-க்குப் பதிலாக குறிப்பிட்ட bean தேர்ந்தெடுக்கப்படும்.

## `@Bean`

**எளிய விளக்கம்:** “Spring, இந்த method return செய்யும் object-ஐ ஒரு bean ஆகப் பதிவு செய்.”

```java
@Configuration
public class AppConfig {
    @Bean
    public java.time.Clock applicationClock() {
        return java.time.Clock.systemUTC();
    }
}
```

Bean உருவாக்கும்போது Spring இந்த method-ஐ call செய்து, அது திருப்பித் தரும் object-ஐ நிர்வகிக்கும்.

**எப்போது பயன்படுத்துவது:** நீங்கள் எழுதாத class-ஐ (எ.கா. third-party library class) configure செய்ய வேண்டியிருக்கும்போது இது மிகவும் பயனுள்ளது.

**நினைவில் வைத்துக்கொள்ளுங்கள்:** `@Bean` → **Method-க்கு** போடுவது.

### `@Component` vs `@Bean`

| `@Component` | `@Bean` |
|---|---|
| பொதுவாக class மேல் போடுவோம் | Method மேல் போடுவோம் |
| Component scanning மூலம் Spring class-ஐக் கண்டுபிடிக்கும் | Configuration method, object-ஐ வெளிப்படையாக (explicit) வழங்கும் |
| நாம் எழுதிய classes-க்குப் பயன்படும் | Third-party objects அல்லது தனிப்பட்ட setup-க்குப் பயன்படும் |

## `@Scope`

**எளிய விளக்கம்:** “இந்த bean-ஐ Spring எப்படி உருவாக்க வேண்டும், எப்படி மீண்டும் பயன்படுத்த வேண்டும் என்பதை நிர்ணயி.”

பொதுவான scopes:

| Scope | பொருள் |
|---|---|
| `singleton` | ஒரு Spring container-க்கு ஒரே ஒரு bean instance. இதுவே default |
| `prototype` | Container-இடம் அந்த bean கேட்கப்படும் ஒவ்வொரு முறையும் Spring புதிய instance உருவாக்கும் |
| `request` | Web application-இல் ஒவ்வொரு HTTP request-க்கும் ஒரு instance |
| `session` | Web application-இல் ஒவ்வொரு HTTP session-க்கும் ஒரு instance |

எடுத்துக்காட்டு:

```java
@Component
@Scope("prototype")
public class ReportBuilder {
}
```

**முக்கியம்:** `singleton` என்றால் உலகம் முழுவதும் ஒரே instance என்றோ, ஒரு JVM-க்கு ஒன்று என்றோ அர்த்தம் இல்லை. அதன் அர்த்தம்: அந்த Spring container-இல், ஒரு bean definition-க்கு ஒரே ஒரு instance. மேலும், ஒரு prototype bean-ஐ singleton-க்குள் inject செய்தால், ஒவ்வொரு method call-க்கும் தானாகப் புதிய prototype உருவாகாது.

## `@Lazy`

**எளிய விளக்கம்:** “இந்த bean முதல் முறையாகத் தேவைப்படும் வரை அதை உருவாக்குவதைத் தாமதப்படுத்து.” (பொதுவான பயன்பாட்டில்)

```java
@Component
@Lazy
public class HeavyReportService {
    public HeavyReportService() {
        System.out.println("HeavyReportService created");
    }
}
```

Lazy bean பொதுவாக startup-இல் உடனே உருவாகாமல், முதல் முறை கேட்கப்படும்போது உருவாக்கப்படும். எப்படிக் கேட்கப்படுகிறது, சுற்றியுள்ள configuration என்ன என்பதைப் பொறுத்து சரியான நடத்தை மாறலாம்.

**எப்போது பயன்படுத்துவது:** Initialization செய்ய அதிக நேரம்/வளம் தேவைப்பட்டு, அதைத் தாமதப்படுத்துவது பயனுள்ளதாக இருக்கும்போது.

**எச்சரிக்கை:** Initialization-இல் உள்ள பிரச்சனை வெளிப்படும் நேரத்தையும் இது தாமதப்படுத்தலாம் (startup-இல் தெரியாமல், பிறகு தெரியும்). இது எல்லா performance பிரச்சனைகளுக்கும் தீர்வு இல்லை.

## `@Value`

**எளிய விளக்கம்:** “Configuration-இலிருந்து ஒரு மதிப்பைப் படித்து, இங்கே போடு.”

`application.properties`:

```properties
app.name=BankingApp
app.max-retries=3
```

Java:

```java
@Component
public class AppInfo {
    @Value("${app.name}")
    private String appName;

    @Value("${app.max-retries:5}")
    private int maxRetries;
}
```

- `${app.name}` → configure செய்யப்பட்ட property-ஐப் படிக்கும்.
- `${app.max-retries:5}` → அந்த property கிடைக்கவில்லை என்றால் `5` பயன்படுத்தும்.

தொடர்புடைய configuration properties நிறைய இருந்தால், பல `@Value` fields எழுதுவதை விட `@ConfigurationProperties` பராமரிக்க (maintain) எளிதாக இருக்கும்.

## `@PropertySource`

**எளிய விளக்கம்:** “இந்தக் கூடுதல் properties file-இலிருந்து properties-ஐ load செய்.”

```java
@Configuration
@PropertySource("classpath:custom.properties")
public class CustomPropertiesConfig {
}
```

`custom.properties` file `src/main/resources`-இல் இருந்தால், Spring அதன் properties-ஐ Environment-க்குள் load செய்யும்.

**எப்போது பயன்படுத்துவது:** கூடுதலாக ஒரு properties file-ஐ load செய்ய வேண்டியிருக்கும்போது. Spring Boot-இல் முக்கிய configuration முறை பொதுவாக `application.properties` அல்லது `application.yml` தான்.

## `@PropertySources`

**எளிய விளக்கம்:** “பல `@PropertySource` annotations-ஐ ஒன்றாக அறிவி.”

```java
@Configuration
@PropertySources({
    @PropertySource("classpath:database.properties"),
    @PropertySource("classpath:feature.properties")
})
public class ExtraPropertiesConfig {
}
```

இது ஒரு container annotation. நவீன Java-வில் repeatable annotations இருப்பதால், `@PropertySource`-ஐ நேரடியாகப் பலமுறை எழுதவும் முடியும். ஆனால் `@PropertySources` இன்னும் செல்லுபடியாகும் (valid).

---

# 3. பொதுவான class-role annotations

## `@Controller`

**எளிய விளக்கம்:** “இந்த class web requests-ஐக் கையாளும்.”

```java
@Controller
public class PageController {
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
}
```

வழக்கமான Spring MVC application-இல், இது போன்ற controller-இல் `"welcome"` என்று return செய்தால், அது ஒரு view name (எ.கா. template) என்று எடுத்துக்கொள்ளப்படும். Response body-யில் `welcome` என்ற வார்த்தை அப்படியே வரும் என்று தானாக அர்த்தம் இல்லை.

**எப்போது பயன்படுத்துவது:** வழக்கமான MVC pages/views-க்கு. இருந்தாலும், சில methods-இல் `@ResponseBody` சேர்த்தும் controller-ஐப் பயன்படுத்தலாம்.

## `@RestController`

**எளிய விளக்கம்:** “இந்த class web requests-ஐக் கையாளும்; return மதிப்புகளை default ஆக response body-யில் எழுதும்.”

```java
@RestController
public class GreetingController {
    @GetMapping("/greeting")
    public String greeting() {
        return "Hello";
    }
}
```

Response body `Hello` ஆக இருக்கும். ஒரு object-ஐ return செய்தால், பொருத்தமான message converter configure செய்யப்பட்டிருக்கும்போது, Spring பொதுவாக அதை JSON ஆக மாற்றும் (serialize செய்யும்).

**முக்கிய உண்மை:** `@RestController` என்பது சுருக்கமாக `@Controller` + `@ResponseBody`.

## `@Service`

**எளிய விளக்கம்:** “இந்த class-இல் application/business logic உள்ளது.”

```java
@Service
public class TransferService {
    public String transfer() {
        return "Transfer processed";
    }
}
```

`@Service` என்பது `@Component`-இன் சிறப்பு வடிவம். இந்த class-இன் பங்கை (role) தெளிவாகக் காட்டுகிறது.

**வழக்கமான flow:** Controller → Service → Repository.

## `@Repository`

**எளிய விளக்கம்:** “இந்த class data access-க்குப் பொறுப்பு (database வேலைகள்).”

```java
@Repository
public class AccountRepository {
    public String findAccount() {
        return "Account data";
    }
}
```

`@Repository` என்பது persistence/data-access classes-க்கான சிறப்பு component annotation. Spring-இல், சில persistence exceptions-ஐ Spring-இன் data-access exception hierarchy-ஆக மாற்றுவதிலும் (translate செய்வதிலும்) இது பங்கு வகிக்கும்.

Spring Data JPA-வில், repository interfaces-ஐ Spring தானாகக் கண்டுபிடித்து implement செய்யும். அதனால் பெரும்பாலும் நீங்களே concrete repository class எழுத வேண்டியதில்லை.

### விரைவு ஒப்பீடு

| Annotation | முக்கிய பங்கு | நினைவில் வைக்க எளிய வழி |
|---|---|---|
| `@Component` | பொதுவான Spring-managed component | எந்தப் பொதுவான bean-உம் |
| `@Controller` | Web/MVC request கையாளுதல் | பொதுவாக views/pages |
| `@RestController` | REST/API request கையாளுதல் | Response body/data |
| `@Service` | Business logic | விதிகளும் முடிவுகளும் |
| `@Repository` | Data access | Database operations |

---

# 4. Web/API தொடர்பான annotations

## `@RequestMapping`

**எளிய விளக்கம்:** “Web requests-ஐ இந்த class அல்லது method-உடன் இணை (map செய்).”

```java
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public String status() {
        return "Account API is running";
    }
}
```

Class-level mapping ஒரு பொதுவான path-ஐக் கொடுக்கிறது. Method அதனுடன் `/status` சேர்க்கிறது.

`@RequestMapping` மூலம் path, HTTP method, request parameters, headers, media types ஆகியவற்றை வரையறுக்கலாம்.

அன்றாட code-க்கு, கீழே உள்ள shortcut annotations பெரும்பாலும் தெளிவாக இருக்கும்.

## `@GetMapping`

**எளிய விளக்கம்:** “HTTP GET requests-ஐக் கையாளு.”

```java
@GetMapping("/{id}")
public String getAccount(@PathVariable long id) {
    return "Account ID: " + id;
}
```

பொதுவாக data-வைப் பெற (retrieve செய்ய) பயன்படும்.

## `@PostMapping`

**எளிய விளக்கம்:** “HTTP POST requests-ஐக் கையாளு.”

```java
@PostMapping
public String createAccount(@RequestBody AccountRequest request) {
    return "Account creation requested for " + request.name();
}
```

பொதுவாக ஒரு resource-ஐ உருவாக்க அல்லது data-வை process செய்வதற்காக submit செய்யப் பயன்படும்.

## `@PutMapping`

**எளிய விளக்கம்:** “HTTP PUT requests-ஐக் கையாளு.”

```java
@PutMapping("/{id}")
public String updateAccount(@PathVariable long id,
                            @RequestBody AccountRequest request) {
    return "Update requested for account " + id;
}
```

பொதுவாக ஒரு resource-ஐ மாற்றி அமைக்க/update செய்யப் பயன்படும். HTTP convention-படி, PUT பொதுவாக idempotent ஆக இருக்க வேண்டும்: அதே request-ஐ மீண்டும் மீண்டும் அனுப்பினாலும், ஒரு முறை அனுப்பியதற்கு இருந்த அதே விளைவே இருக்க வேண்டும்.

## `@DeleteMapping`

**எளிய விளக்கம்:** “HTTP DELETE requests-ஐக் கையாளு.”

```java
@DeleteMapping("/{id}")
public String deleteAccount(@PathVariable long id) {
    return "Delete requested for account " + id;
}
```

பொதுவாக ஒரு resource-ஐ நீக்கப் (delete செய்ய) பயன்படும்.

### Mapping cheat sheet

| Annotation | HTTP method | பொதுவான நோக்கம் |
|---|---|---|
| `@GetMapping` | GET | Data-வைப் படிக்க |
| `@PostMapping` | POST | Data-வை உருவாக்க/submit செய்ய |
| `@PutMapping` | PUT | Resource-ஐ மாற்றி அமைக்க/update செய்ய |
| `@DeleteMapping` | DELETE | Resource-ஐ நீக்க |
| `@RequestMapping` | Configure செய்யலாம் | பொதுவான mapping |

இவை பொதுவான வழக்கங்கள் (conventions) மட்டுமே. ஒவ்வொரு API-யும் இவற்றை இறுக்கமாகப் பின்பற்ற வேண்டும் என்ற கட்டாயம் இல்லை.

## `@PathVariable`

**எளிய விளக்கம்:** “URL path-இலிருந்து ஒரு மதிப்பைப் படி.”

```java
@GetMapping("/accounts/{id}")
public String getAccount(@PathVariable("id") long accountId) {
    return "Account ID: " + accountId;
}
```

Request: `GET /accounts/42`

இங்கே `id` = `42`.

**குறிப்பு (Clue):** மதிப்பு URL அமைப்பின் ஒரு பகுதியாக இருந்தால் (எ.கா. `/accounts/42`), `@PathVariable` என்று நினையுங்கள்.

## `@RequestParam`

**எளிய விளக்கம்:** “Query parameter-இலிருந்து (அல்லது configure செய்யப்பட்ட வேறு request parameter-இலிருந்து) ஒரு மதிப்பைப் படி.”

```java
@GetMapping("/accounts")
public String searchAccounts(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(required = false) String status) {
    return "Page: " + page + ", status: " + status;
}
```

Request: `GET /accounts?page=2&status=ACTIVE`

- `page`-க்கு `2` கிடைக்கும்.
- `status`-க்கு `ACTIVE` கிடைக்கும்.
- `required = false` → `status` விருப்பத்துக்குரியது (optional) ஆகிறது.
- `defaultValue = "0"` → `page` இல்லாதபோது (அல்லது காலியாக இருக்கும்போதும்) default மதிப்பு வழங்கும்.

**குறிப்பு (Clue):** மதிப்பு `?`-க்குப் பிறகு வந்தால், பொதுவாக `@RequestParam` என்று நினையுங்கள்.

## `@RequestBody`

**எளிய விளக்கம்:** “HTTP request body-ஐப் படித்து, அதை ஒரு Java object ஆக மாற்று.”

எடுத்துக்காட்டு JSON:

```json
{
  "name": "Venkatesh",
  "accountType": "SAVINGS"
}
```

Java record பயன்படுத்தும் DTO:

```java
public record AccountRequest(String name, String accountType) {
}
```

Controller:

```java
@PostMapping("/accounts")
public String createAccount(@RequestBody AccountRequest request) {
    return "Creating account for " + request.name();
}
```

Request body-ஐ DTO ஆக மாற்ற (deserialize செய்ய), Spring ஒரு HTTP message converter-ஐப் பயன்படுத்துகிறது (Spring Boot web applications-இல் JSON-க்கு பொதுவாக Jackson).

**நினைவில் வைத்துக்கொள்ளுங்கள்:** `@RequestBody` என்பது request body-க்கு. URL path மதிப்புக்கோ, query parameter-க்கோ இல்லை.

## `@ResponseBody`

**எளிய விளக்கம்:** “Method return செய்யும் மதிப்பை நேரடியாக HTTP response body-யில் எழுது.”

```java
@Controller
public class StatusController {
    @GetMapping("/status")
    @ResponseBody
    public String status() {
        return "UP";
    }
}
```

`@ResponseBody` இல்லாமல், வழக்கமான `@Controller` method ஒரு string-ஐ return செய்தால், அது view name ஆக எடுத்துக்கொள்ளப்படும். `@ResponseBody` இருந்தால், `UP` என்பது response content ஆக எழுதப்படும்.

`@RestController`, எல்லா handler methods-க்கும் இந்த நடத்தையை default ஆகப் பயன்படுத்துகிறது.

---

# 5. Application தொடக்க (startup) annotation

## `@SpringBootApplication`

**எளிய விளக்கம்:** “இதுதான் முக்கிய Spring Boot application configuration மற்றும் தொடக்கப் புள்ளி (startup point).”

```java
@SpringBootApplication
public class BankingApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankingApplication.class, args);
    }
}
```

இது மூன்று முக்கிய annotations-ஐ ஒருங்கிணைக்கிறது:

1. `@SpringBootConfiguration` — இது ஒரு Spring Boot configuration class என்று குறிக்கிறது.
2. `@EnableAutoConfiguration` — கண்டறிந்த dependencies மற்றும் configuration அடிப்படையில் beans-ஐ configure செய்யுமாறு Spring Boot-ஐக் கேட்கிறது.
3. `@ComponentScan` — default ஆக, இந்த class இருக்கும் package மற்றும் அதன் subpackages-இல் components-ஐத் தேடுகிறது.

**முக்கியம்:** Main application class-ஐப் பொருத்தமான top-level package-இல் வையுங்கள். அப்போதுதான் component scanning உங்கள் controllers, services மற்றும் பிற components-ஐக் கண்டுபிடிக்கும்.

**Startup-இல் என்ன நடக்கும்? (எளிமையாக)**

```text
main()
  |
  v
SpringApplication.run(...)
  |
  v
Spring ApplicationContext உருவாக்கம்/refresh
  |
  +--> Configuration மற்றும் auto-configuration படித்தல்
  |
  +--> Bean definitions-ஐ scan/register செய்தல்
  |
  +--> Eager singleton beans உருவாக்கி, dependencies inject செய்தல்
  |
  v
Application தயார்
  |
  v
உள்வரும் HTTP requests-ஐக் கையாளுதல்
```

இது எளிமைப்படுத்தப்பட்ட வரைபடம் மட்டுமே. முழுமையான உள் lifecycle வரிசை அல்ல.

---

# 6. முழுமையான mini-example: Account API

இந்த எடுத்துக்காட்டு, எல்லா annotations-ஐயும் ஒரே எளிய flow-வில் இணைக்கிறது. உண்மையான application-இல், repository பொதுவாக database-ஐ அணுகும்.

### DTO

```java
public record AccountRequest(String name, String accountType) {
}
```

### Repository

```java
@Repository
public class AccountRepository {
    public String save(String name) {
        // உண்மையான database persistence-ஆல் இதை மாற்றவும்.
        return "Saved account for " + name;
    }
}
```

### Service

```java
@Service
public class AccountService {
    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public String create(String name) {
        return repository.save(name);
    }
}
```

### Controller

```java
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public String create(@RequestBody AccountRequest request) {
        return service.create(request.name());
    }

    @GetMapping("/{id}")
    public String get(@PathVariable long id) {
        return "Requested account ID: " + id;
    }

    @GetMapping
    public String search(@RequestParam(defaultValue = "0") int page) {
        return "Requested page: " + page;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        return "Delete requested for account: " + id;
    }
}
```

### Request flow

```text
HTTP request
    |
    v
AccountController
    |
    v
AccountService
    |
    v
AccountRepository
    |
    v
Client-க்கு response திரும்புகிறது
```

Beans-ஐ Spring உருவாக்கி இணைக்கிறது (wire செய்கிறது). HTTP requests-ஐ பொருந்தும் controller method-க்கு Spring MVC அனுப்பி, request/response data-வை மாற்றுகிறது (convert செய்கிறது).

---

# 7. Interview-இல் சரியான annotation-ஐ எப்படிக் கண்டுபிடிப்பது

| கேள்வி இப்படிச் சொன்னால்… | இதை நினையுங்கள்… |
|---|---|
| “இந்த class-ஐ Spring நிர்வகிக்கட்டும்” | `@Component`, `@Service`, `@Repository` போன்றவை |
| “ஒரு dependency-ஐ inject செய்” | Constructor injection; விருப்பப்பட்டால் `@Autowired` |
| “இரண்டு implementations உள்ளன; ஒன்றைத் தேர்ந்தெடு” | `@Qualifier` அல்லது `@Primary` |
| “ஒரு method மூலம் bean உருவாக்கு” | `@Bean` |
| “Initialization-ஐத் தாமதப்படுத்து” | `@Lazy` |
| “Bean instance lifecycle-ஐத் தேர்ந்தெடு” | `@Scope` |
| “ஒரு configuration property-ஐப் படி” | `@Value` அல்லது `@ConfigurationProperties` |
| “வேறொரு properties file-ஐ load செய்” | `@PropertySource` / `@PropertySources` |
| “ஒரு web page/view-ஐக் கையாள்” | `@Controller` |
| “REST API உருவாக்கு” | `@RestController` |
| “பொதுவான route-ஐ map செய்” | `@RequestMapping` |
| “GET மூலம் data பெறு” | `@GetMapping` |
| “POST மூலம் submit/create செய்” | `@PostMapping` |
| “PUT மூலம் update செய்” | `@PutMapping` |
| “DELETE மூலம் நீக்கு” | `@DeleteMapping` |
| “`/accounts/42`-இலிருந்து ID எடு” | `@PathVariable` |
| “`?page=2`-இலிருந்து `page=2` எடு” | `@RequestParam` |
| “JSON body-ஐ DTO ஆக மாற்று” | `@RequestBody` |
| “View name-க்குப் பதிலாக data return செய்” | `@ResponseBody` அல்லது `@RestController` |
| “முக்கிய Spring Boot startup class” | `@SpringBootApplication` |

---

# 8. Interview-இல் வரும் பொதுவான பொறிகள் (traps)

1. **ஒரே constructor இருந்தால் `@Autowired` கட்டாயம் இல்லை.** அந்த ஒரே constructor-ஐ Spring தானாகப் பயன்படுத்தும்.
2. **`@Component`, `@Service`, `@Repository` ஆகிய மூன்றும் components-ஐப் பதிவு செய்யும்**, ஆனால் சிறப்பு annotations அந்த class-இன் பங்கைத் தெளிவாகக் காட்டும்.
3. **`@Controller`-ம் `@RestController`-ம் ஒன்றல்ல.** சாதாரண controller பொதுவாக view names-ஐ return செய்யும்; REST controller default ஆக response body-ஐ எழுதும்.
4. **`@Bean` method மேல் போடுவது; `@Component` பொதுவாக class மேல் போடுவது.**
5. **`@Primary`-ம் `@Qualifier`-ம் bean தேர்வுப் பிரச்சனையை வெவ்வேறு விதங்களில் தீர்க்கும்.**
6. **`@PathVariable`-ம் `@RequestParam`-ம் வெவ்வேறு இடங்களிலிருந்து மதிப்புகளைப் படிக்கும்.** `/accounts/42` vs `/accounts?id=42`.
7. **`@RequestBody` body-ஐப் படிக்கும்; query parameter-ஐப் படிக்காது.**
8. **Spring singleton என்பது ஒரு Spring container/bean definition-க்கு ஒரு instance**; ஒரு user-க்கு ஒன்று, அல்லது ஒரு HTTP request-க்கு ஒன்று என்று தானாக அர்த்தம் இல்லை.
9. **`@Lazy` initialization-ஐத் தாமதப்படுத்தும்; அந்த bean ஒருபோதும் உருவாக்கப்படாது என்று உறுதி அளிக்காது.**
10. **`@SpringBootApplication` என்பது configuration, auto-configuration, component scanning ஆகியவற்றின் கூட்டு.**

---

# 9. ஒரு நிமிட revision

- **Spring நிர்வகிக்கும் object:** bean
- **Spring objects-ஐ உருவாக்கி நிர்வகிப்பது:** IoC
- **Spring dependencies-ஐ வழங்குவது:** DI
- **பொதுவான component:** `@Component`
- **Business logic:** `@Service`
- **Data access:** `@Repository`
- **Web page/controller:** `@Controller`
- **REST API:** `@RestController`
- **Bean உருவாக்கும் method:** `@Bean`
- **Dependency inject செய்ய:** constructor injection / `@Autowired`
- **பல beans-இல் ஒன்றைத் தேர்ந்தெடுக்க:** `@Qualifier` / `@Primary`
- **Bean உருவாக்கும் நேரம்:** `@Lazy`
- **Bean lifecycle scope:** `@Scope`
- **ஒரு property-ஐப் படிக்க:** `@Value`
- **கூடுதல் property files load செய்ய:** `@PropertySource` / `@PropertySources`
- **பொதுவான route:** `@RequestMapping`
- **GET / POST / PUT / DELETE:** பொருந்தும் mapping annotation
- **URL path மதிப்பு:** `@PathVariable`
- **Query parameter:** `@RequestParam`
- **Request JSON/body:** `@RequestBody`
- **Return மதிப்பை response body-யில் எழுத:** `@ResponseBody`
- **Spring Boot நுழைவுப் புள்ளி (entry point):** `@SpringBootApplication`

## உங்கள் பட்டியலுக்கு ஒரு சிறிய திருத்தம்

நீங்கள் `@Laz` என்று எழுதியிருந்தீர்கள்; சரியான annotation பெயர் **`@Lazy`**.

---

## எப்படிப் பயிற்சி செய்வது

1. ஒரு சிறிய Spring Boot project உருவாக்குங்கள்.
2. ஒரு controller, ஒரு service, ஒரு repository சேருங்கள்.
3. அவற்றை இணைக்க constructor injection பயன்படுத்துங்கள்.
4. `@PathVariable` மற்றும் `@RequestParam` உடன் ஒரு GET endpoint-ஐச் சோதியுங்கள் (test செய்யுங்கள்).
5. `@RequestBody` உடன் ஒரு POST endpoint-ஐச் சோதியுங்கள்.
6. `application.properties`-இல் ஒரு property சேர்த்து, `@Value` மூலம் படியுங்கள்.
7. ஒவ்வொரு annotation-உம் என்ன செய்கிறது, நீங்கள் *ஏன்* அதைத் தேர்ந்தெடுத்தீர்கள் என்பதை விளக்கிப் பழகுங்கள்.

**தங்க விதி:** Annotation-இன் பெயரை மட்டும் மனப்பாடம் செய்யாதீர்கள். ஒவ்வொன்றுக்கும் மூன்று விஷயங்களைத் தெரிந்து கொள்ளுங்கள்: **அது Spring-க்கு என்ன செய்யச் சொல்கிறது, எங்கே பயன்படுத்துவீர்கள், எந்தப் பிரச்சனையைத் தீர்க்கிறது.**
