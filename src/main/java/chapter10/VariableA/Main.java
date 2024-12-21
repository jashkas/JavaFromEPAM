package chapter10.VariableA;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        TextProcessor processor = new TextProcessor();
        System.out.println(processor.capitalizeFirstLetterOfEachWord(FileHandler.readFromFile("src/main/java/chapter10/VariableA/resources/Владимир Короткевич. The Wild Hunt of King Stach.txt")));
    }
}
