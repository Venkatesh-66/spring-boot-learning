@Entity
public class User {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
}