package bank;
import java.util.Scanner;

public class Menu {
  private Scanner scanner;

  public static void main(String[] args) {
    System.out.println("Welcome to Kerimbout Bank International!");

    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);

    Customer customer = menu.authenticateUser();

    if(customer != null){
      Account account = DataSource.getAccount(customer.getAccountId());
      menu.showMenu(customer, account);
    }
    menu.scanner.close();
  }

  private Customer authenticateUser(){
    System.out.print("Please Enter your Username :");
    String username = scanner.next();

    System.out.println("Please Enter your Password :");
    String password = scanner.next();

    Customer customer = null;
    try {
      customer = Authenticator.login(username, password);
    } catch (Exception e) {
      System.out.println("There was an error: " + e.getMessage());
    }

    return customer;
  }
}
