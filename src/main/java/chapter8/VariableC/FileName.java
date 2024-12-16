package chapter8.VariableC;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class FileName {
    private String mask;
    private Pattern pattern;

    public FileName(String mask) {
        this.mask = mask;
        this.pattern = Pattern.compile(convertMaskToRegex(mask));
    }

    private String convertMaskToRegex(String mask) {
        StringBuilder regex = new StringBuilder();
        char[] chars = mask.toCharArray();

        for (char c : chars) {
            switch (c) {
                case '?':
                    regex.append('.');
                    break;
                case '*':
                    regex.append(".*");
                    break;
                default:
                    regex.append(Pattern.quote(String.valueOf(c)));
                    break;
            }
        }

        return regex.toString();
    }

    public boolean matches(String fileName) {
        Matcher matcher = pattern.matcher(fileName);
        return matcher.matches();
    }
}
