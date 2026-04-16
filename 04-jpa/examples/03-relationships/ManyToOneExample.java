@Entity
public class Order {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}