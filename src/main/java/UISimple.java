import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UISimple {

    private Controller controller;

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userInter() throws SQLException, ClassNotFoundException {
        setController(new Controller(this));
        try {
            getController().weatherReq(sityInput(), periodInput());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private String periodInput() {
        Scanner scanner = new Scanner(System.in);
        String period = "";
        do {
            System.out.print("������� ������ ��������: ���� ���� - ������� 1   ��  5 ���� ������� 5  " +
                    "�� ��������� �������� �� ���� ������� 9 " +
                    "�����  ��  ���������   ������� 0    ");
            period = scanner.next();
            periodInputCheck(period);

        } while (!periodInputCheck(period));
        return period;
    }


    private boolean periodInputCheck(String period) {

        switch (period) {
            case "0":
                System.exit(0);
                break;
            case "1":
                return true;
            case "5":
                return true;
            case "9":
                return true;
            default:
                System.out.printf("������� ���������� ������. ������� %s �� �������� \n", period);
        }
        return false;
    }

    private String sityInput() {
        Scanner scanner = new Scanner(System.in);
        String sity = "";
        for (; sity.isEmpty(); ) {
            System.out.print("������� ����� ��  ����������  �����. �����  �����  ��  ���������   ������� 0    ");
            sity = scanner.next();
            if (sity.length() > 20) {
                System.out.println("� �������� �� ������ ���� ����� 20  ��������");
                sity = "";
                continue;
            }
            if (sity.equals("0")) {
                System.exit(0);
            }
            if (haveDigit(sity)) {
                System.out.println("� �������� �� ������� ���� ����");
                sity = "";
            }
        }
        return sity;
    }

    boolean haveDigit(String sity) {

        for (int i = 0; sity.length() > i; i++) {
            if (Character.isDigit(sity.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public void printWeather(String s) {
        System.out.println(s);
    }
}
