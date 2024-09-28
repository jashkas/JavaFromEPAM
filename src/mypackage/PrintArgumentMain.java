package mypackage;

public class PrintArgumentMain {
    public void printArguments(String[] args) {
        for (String str: args) {
            System.out.printf("Argument--> %s%n", str);
        }
    }
}
