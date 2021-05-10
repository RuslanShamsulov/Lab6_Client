package FormingObjects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FinderNumbers {
    static int foundedId = 0;

    /**
     *
     * @param text строка с пользовательским вводом
     * @return найденная строка
     */
    public int find(String text) {

        String regex = "(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()){
            String foundedId = matcher.group(0);
            this.foundedId = Integer.parseInt(foundedId);
        }
        return foundedId;
    }

}
