package mypackage;
import java.io.IOException;

public class ReadChar {
    public void read(){
        int x;
        try {
            x = System.in.read();
            char c = (char)x;
            System.out.println("Character Code: " + c + " =" + x);
        }
        catch (IOException e) {
            System.err.println("i\\o error " + e);
        }
    }
}
