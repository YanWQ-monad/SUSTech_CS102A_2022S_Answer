import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Player {
    private final String account;
    private Mail mail;
    private PhoneNumber phoneNumber;
    private String password;
    ArrayList<Pokemon> pokemons;

    public Player(Mail mail, PhoneNumber phoneNumber, String password) {
        this.account = this.generateAccount();
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.pokemons = new ArrayList<>();
    }

    public Player(Mail mail, String password) {
        this(mail, null, password);
    }

    public Player(PhoneNumber phoneNumber, String password) {
        this(null, phoneNumber, password);
    }

    public String generateAccount() {
        Random rng = new Random();
        return String.valueOf(rng.nextInt(1000000, 10000000));
    }

    public boolean checkIdentity(Mail mail, String password) {
        return mail.equals(this.mail) && this.password.equals(password);
    }

    public boolean checkIdentity(PhoneNumber phoneNumber, String password) {
        return phoneNumber.equals(this.phoneNumber) && password.equals(this.password);
    }

    public boolean changePassword(PhoneNumber phoneNumber, String oldPassword, String newPassword) {
        if (!checkIdentity(phoneNumber, oldPassword))
            return false;
        this.password = newPassword;
        return true;
    }

    public boolean changePassword(Mail mail, String oldPassword, String newPassword) {
        if (!checkIdentity(mail, oldPassword))
            return false;
        this.password = newPassword;
        return true;
    }

    public boolean setMail(PhoneNumber phoneNumber, String password, Mail mail) {
        if (!checkIdentity(phoneNumber, password))
            return false;
        this.mail = mail;
        return true;
    }

    public boolean setPhoneNumber(Mail mail, String password, PhoneNumber phoneNumber) {
        if (!checkIdentity(mail, password))
            return false;
        this.phoneNumber = phoneNumber;
        return true;
    }

    public void addPokemon(Pokemon pokemon) {
        this.pokemons.add(pokemon);
    }

    public String getAccount() {
        return this.account;
    }

    public Mail getMail() {
        return this.mail;
    }

    public PhoneNumber getPhoneNumber() {
        return this.phoneNumber;
    }

    public ArrayList<Pokemon> getPokemons() {
        return this.pokemons;
    }

    @Override  // TODO: Remove debug `equals`
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(this.mail, player.mail);
    }
}

class Mail extends Object {
    String mail;

    public Mail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mail that = (Mail) o;
        return Objects.equals(this.mail, that.mail);
    }
}

class PhoneNumber {
    String phoneNumber;

    public PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(this.phoneNumber, that.phoneNumber);
    }
}
