import java.util.ArrayList;
import java.util.Scanner;

public class Main 
{
    private ArrayList<ArrayList<Integer>> studentMarks;
    private int numSubjects;
    private int numStudents;
   
    public Main(int numSubjects, int numStudents) 
    {
        this.numSubjects = numSubjects;
        this.numStudents = numStudents;
        studentMarks = new ArrayList<>();

        
        for (int i = 0; i < numStudents; i++) 
        {
            studentMarks.add(new ArrayList<>());
        }
    }

    public void addMarks(int studentIndex, int marks)
    {
        studentMarks.get(studentIndex).add(marks);
    }

    public double calculateStudentAverage(int studentIndex) 
    {
        ArrayList<Integer> marks = studentMarks.get(studentIndex);
        int total = 0;
        for (int mark : marks) 
        {
            total += mark;
        }
        return marks.isEmpty() ? 0 : (double) total / marks.size();
    }

    public double calculateClassTotalMarks() 
    {
        double totalMarks = 0;
        for (int i = 0; i < numStudents; i++)
        {
            totalMarks += calculateStudentAverage(i) * numSubjects; 
        }
        return totalMarks;
    }

    public double calculateClassAverage() 
    {
        double totalMarks = calculateClassTotalMarks();
        return totalMarks / (numStudents * numSubjects); 
    }

    public String calculateGrade(double average) 
    {
        if (average >= 95) 
        return "A+";
        else if (average >= 90) 
        return "A";
        else if (average >= 85) 
        return "B+";
        else if (average >= 80) 
        return "B";
        else if (average >= 70) 
        return "C+";
        else if (average >= 60) 
        return "C";
        else if (average >= 50) 
        return "D+";
        else if (average >= 40) 
        return "D";
        else return "F";
    }

    public void displayTopPerformer() 
    {
        double highestAvg = 0;
        int topStudentIndex = -1;
        for (int i = 0; i < numStudents; i++)
        {
            double avg = calculateStudentAverage(i);
            if (avg > highestAvg)
            {
                highestAvg = avg;
                topStudentIndex = i;
            }
        }
        if (topStudentIndex != -1) 
        {
            System.out.println("Top Performer: Student " + (topStudentIndex + 1));
            System.out.println("Average Marks: " + highestAvg);
            System.out.println("Grade: " + calculateGrade(highestAvg));
        }
    }

    public void displayStudentResults() 
    {
        System.out.println("\nStudent-wise Averages and Grades:");
        for (int i = 0; i < numStudents; i++) 
        {
            double average = calculateStudentAverage(i);
            System.out.printf("Student %d - Average Marks: %.2f, Grade: %s\n", (i + 1), average, calculateGrade(average));
        }
    }

    public void displayClassResult() 
    {
        double classAverage = calculateClassAverage();
        System.out.println("\nClass Average Marks: " + classAverage);
        System.out.println("Class Grade: " + calculateGrade(classAverage));
    }

    public static int getValidIntInput(Scanner scanner, String prompt, int min, int max) 
    {
        int input;
        while (true)
        {
            System.out.print(prompt);
            if (scanner.hasNextInt()) 
            {
                input = scanner.nextInt();
                if (input >= min && input <= max) 
                {
                    break;
                }
                else 
                {
                    System.out.println("Please enter a valid number between " + min + " and " + max + ".");
                }
            }
            else
            {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.next(); 
            }
        }
        return input;
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Student Grade Tracker System!");
        int numSubjects = getValidIntInput(scanner, "Enter the number of subjects: ", 1, 10);
        int numStudents = getValidIntInput(scanner, "Enter the number of students: ", 1, 50);
        Main tracker = new Main(numSubjects, numStudents);

        for (int i = 0; i < numStudents; i++) 
        {
            System.out.println("\nEntering marks for Student " + (i + 1) + ":");
            for (int j = 0; j < numSubjects; j++) 
            {
                int marks = getValidIntInput(scanner, "Enter marks for Subject " + (j + 1) + " (0-100): ", 0, 100);
                tracker.addMarks(i, marks);
            }
        }

        boolean continueMenu = true;
        while (continueMenu) 
        {
            System.out.println("\nChoose an option:");
            System.out.println("1. View student results");
            System.out.println("2. View class results");
            System.out.println("3. View top performer");
            System.out.println("4. Exit\n");
            int choice = getValidIntInput(scanner, "Enter your choice (1-4): ", 1, 4);

            switch (choice)
            {
                case 1:
                    
                    System.out.println();
                    tracker.displayStudentResults();
                    break;
                case 2:
                    tracker.displayClassResult();
                    break;
                case 3:
                    tracker.displayTopPerformer();
                    break;
                case 4:
                    System.out.println("Exiting the program...");
                    continueMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        }

        scanner.close();
    }
}
