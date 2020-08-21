import java.util.Scanner;

public class Hello{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input your last grade");
        int lastgrade = scanner.nextInt();
        System.out.println("Input your current grade");
        int curgrade = scanner.nextInt();
        System.out.printf("percentage: %.2f%%", (float)(curgrade-lastgrade)/lastgrade * 100);
        
    }
}