import java.util.Scanner;

public class User {
    private String account;
    private String password;
    private double money;

    public User() {
    }

    public User(String account, String password, double money) {
        this.account = account;
        this.password = password;
        this.money = money;
    }

    public void introduce() {
        System.out.printf("%s's account has a balance of %.2f dollar\n", this.account, this.money);
    }

    public void expense(double value, Scanner in) {
        if (value > money) {
            System.out.printf("Plan to expense %.2f dollar but no sufficient funds\n", value);;
        }
        else {
            System.out.printf("Plan to expense %.2f dollar\n", value);
            System.out.println("Please input your password:");
            String input_password = in.next();
            assert input_password.equals(password);
            this.money -= value;
            System.out.printf("Expense %.2f dollar and balance %.2f\n", value, this.money);
        }
    }

    public void income(double value) {
        this.money += value;
        System.out.printf("Got %.2f as income, balance is %.2f dollar\n", value, this.money);;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
