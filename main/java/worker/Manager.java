package worker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager {

    private final Scanner sc = new Scanner(System.in);
    private ArrayList<Worker> lw = new ArrayList<>();
    private ArrayList<SalaryHistory> ls = new ArrayList();
    public void run(){
        while (true){
            Manager mn = new Manager();
            mn.showMenu();
            int choice = mn.menuChoice();
            switch (choice){
                case 1:
                    mn.createWorker(lw);
                    break;
                case 2:
                    mn.increaseWorker(lw, ls);
                    break;
                case 3:
                    mn.decreaseWorker(lw, ls);
                    break;
                case 4:
                    mn.displaySalaryHistory(ls);
                    break;
                case 5:
                    return;
            }
        }
    }
    public void showMenu() {
        System.out.println("Display the menu:");
        System.out.println("1. Add a Worker.");
        System.out.println("2. Increase salary for worker.");
        System.out.println("3. Decrease for worker.");
        System.out.println("4. Show adjusted salary worker information.");
        System.out.println("5. Exit.");
    }

    public int menuChoice() {
        int choice = checkIntLimit(1, 5);
        return choice;
    }

    public int checkIntLimit(int min, int max) {
        while (true) {
            try {
                String result = getString("Enter choice: ");
                int choice;
                if (checkInteger(result)) {
                    choice = Integer.parseInt(result);
                    if (choice < min || choice > max) {
                        throw new NumberFormatException();
                    }
                    return choice;
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                System.err.println("Error! Enter again!");
            }
        }
    }

    public int getInt(String mess) {
        while (true) {
            try {
                String result = getString(mess);
                int choice;
                if (checkInteger(result)) {
                    choice = Integer.parseInt(result);

                    return choice;
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                System.out.println("Error! Enter again!");
            }
        }
    }

    public boolean checkInteger(String str) {
        Pattern pattern = Pattern.compile("\\d*");
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public String getString(String mess) {
        System.out.println(mess);
        return sc.nextLine().trim();
    }

    public void createWorker(ArrayList<Worker> lw) {
        int id = generateID(lw);
        String name = getString("Enter name:");
        int age = getInt("Enter age:");
        int salary = getInt("Enter salary:");
        String worklocation = getString("Enter worklocation");
        lw.add(new Worker(id, name, age, salary, worklocation));
        System.err.println("Add successful.");
    }

    public int generateID(ArrayList<Worker> lw) {
        int id = 0;
        if (lw.size() == 0) {
            return 1;
        } else {

            for (Worker s : lw) {
                if (s.getId() == lw.size()) {
                    id = s.getId() + 1;
                }
            }
        }
        return id;
    }

    public void increaseWorker(ArrayList<Worker> lw, ArrayList<SalaryHistory> ls) {
        int code = getInt("Enter code(id):");
        Worker w = getWorkerByCode(lw, code);
        if (w != null) {
            int salary = getInt("Enter salary:");
            if (salary > 0) {
                int salaryCurrent = w.getSalary();
                int totalSalary = salaryCurrent + salary;
                ls.add(new SalaryHistory(w.getId(), w.getName(), w.getAge(), salary, w.getWorkLocation(), "Up", getCurrentDate()));
                w.setSalary(totalSalary);
                System.err.println("Must be greater than zero!");
            }
        } else {
            System.err.println("Not found code!");
        }
    }
    
    public void decreaseWorker(ArrayList<Worker> lw, ArrayList<SalaryHistory> ls) {
        int code = getInt("Enter code(id):");
        Worker w = getWorkerByCode(lw, code);
        if (w != null) {
            int salary = getInt("Enter salary:");
            if (salary > 0) {
                int salaryCurrent = w.getSalary();
                if(salaryCurrent > salary){
                int totalSalary = salaryCurrent - salary;
                ls.add(new SalaryHistory(w.getId(), w.getName(), w.getAge(), salary, w.getWorkLocation(), "Down", getCurrentDate()));
                w.setSalary(totalSalary);
                }
                System.err.println("Must be greater than salary current!");
            } else{
                    System.err.println("Must be greater than zero!");
                }
        } else {
            System.err.println("Not found code!");
        }
    }

    private Worker getWorkerByCode(ArrayList<Worker> lw, int code) {
        for (Worker w : lw) {
            if (code == w.getId()) {
                return w;
            }
        }
        return null;
    }

    public String getCurrentDate() {
        DateFormat date = new SimpleDateFormat("dd/mm/yyyy HH:mm");
        Calendar calendar = Calendar.getInstance();
        return date.format(calendar.getTime());
    }
    
    public void displaySalaryHistory(ArrayList<SalaryHistory> ls){
        if (ls.isEmpty()){
            System.err.println("List Salary History is empty");
        } else {
            Collections.sort(ls);
            for (SalaryHistory s: ls){
                System.out.println("Id:" + s.getId()+ "-Name:" + s.getName() + "-Age:"+s.getAge()+"-Salary:"+s.getSalary()
                        +"-Status:" +s.getStatus()+"-Date:"+s.getDate());
            }
        }
    }
}
