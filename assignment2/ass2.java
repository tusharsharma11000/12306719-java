// part 1
abstract class Student {
  private String name;
  private int rollno;
  private int semester;

  public Student(int rollno, String name, int semester) {
    this.rollno = rollno;
    this.name = name;
    this.semester = semester;
  }

  public String getname() {
    return name;
  }

  public int getrollno() {
    return rollno;
  }

  public int getsemester() {
    return semester;
  }

  public void setsemester(int semester) {
    if (semester > 0 && semester <= 8)
      this.semester = semester;
    else
      System.out.println("Invalid Semester");
  }

  // part 2
  abstract double calculateGrade();

  public void displaydetails() {
    System.out.println("rollno : " + getrollno());
    System.out.println("name : " + getname());
    System.out.println("semester : " + getsemester());
  }

  // part 3
  static String universityName;
  static {
    universityName = "LPU";
  }

  public static void disp_univ_name() {
    System.out.println("University Name: " + universityName);
  }

  // part 4
  final int maxsem = 8;

  final void Showrules() {
    System.out.println("Follow university rules");
  }
}

// part 5
class EngineeringStudent extends Student {
  private double internal_ass;
  private double external_ass;

  public EngineeringStudent(int rollno, String name, int semester,
  double internal_ass, double external_ass) {
    super(rollno, name, semester);
    this.internal_ass = internal_ass;
    this.external_ass = external_ass;
  }

  @Override
  double calculateGrade() {
  return (internal_ass * 0.3) + (external_ass * 0.7);
  }
}

class MedicalStudent extends Student {
  private double theory;
  private double practical;

  public MedicalStudent(int rollno, String name, int semester,
  double theory, double practical) {
    super(rollno, name, semester);
    this.theory = theory;
    this.practical = practical;
  }

  @Override
  double calculateGrade() {
    return (theory + practical) / 2;
  }
}

// part 6
interface SportsParticipant {
  void playSport();
}

interface CulturalParticipant {
  void performActivity();
}

// part 7
class AllRounderStudent extends Student
        implements SportsParticipant, CulturalParticipant {

  private double score;

  public AllRounderStudent(int rollno, String name, int semester, double score) {
    super(rollno, name, semester);
    this.score = score;
  }

  @Override
  double calculateGrade() {
    return score;
  }

  @Override
  public void playSport() {
    System.out.println(getname() + " plays sports");
  }

  @Override
  public void performActivity() {
    System.out.println(getname() + " performs cultural activities");
  }
}

// part 8
public class ass2 {
  public static void main(String[] args) {

    Student s;

    s = new EngineeringStudent(5, "Mohit", 6, 77, 87);
    System.out.println("Engineering Grade: " + s.calculateGrade());

    s = new AllRounderStudent(44, "Tushar", 6, 70.0);
    System.out.println("AllRounder Grade: " + s.calculateGrade());

    s = new MedicalStudent(2, "Rahul", 6, 30, 40);
    System.out.println("Medical Grade: " + s.calculateGrade());

    Student.disp_univ_name();
  }
}