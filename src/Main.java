import java.util.Scanner;
import service.SkillGapService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SkillGapService service = new SkillGapService();

        int choice;

        do {

            System.out.println("\n===== Student Skill Gap Analyzer =====");
            System.out.println("1. Add Student");
            System.out.println("2. Add Skill");
            System.out.println("3. Add Company");
            System.out.println("4. Analyze Skill Gap");
            System.out.println("5. View Match Percentage");
            System.out.println("6. Get Learning Recommendations");
            System.out.println("7. Exit");

            System.out.print("Enter Choice: ");

            choice = sc.nextInt();

            switch(choice) {

                case 1:
                    service.addStudent();
                    break;

                case 2:
                    service.addSkill();
                    break;

                case 3:
                    service.addCompany();
                    break;

                case 4:
                    service.analyzeSkillGap();
                    break;

                case 5:
                    service.viewMatchPercentage();
                    break;

                case 6:
                    service.getRecommendations();
                    break;

                case 7:
                    System.out.println("Thank You");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while(choice != 7);

        sc.close();
    }
}
