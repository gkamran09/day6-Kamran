import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String SPACE_DELIMITER = "\\s+";
    public static final String NEW_LINE_DELIMITER = "\n";
    public static final String SPACE_CHAR = " ";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String SET_NUMBER_ONE = " 1";

    public String getResult(String inputString) {

        String[] words = inputString.split(SPACE_DELIMITER);

        //TODO: this can be removed because this is not anymore at used
        if (words.length == 1) {
            return inputString + SET_NUMBER_ONE;
        }
        try {
            List<WordFrequencyInfo> wordFrequencyInfoList = createWordFrequencyInfoList(words);
            Map<String, List<WordFrequencyInfo>> wordFrequencyMap = initializeListMap(wordFrequencyInfoList);
            wordFrequencyInfoList = extractWordFrequencyInfo(wordFrequencyMap);

            List<WordFrequencyInfo> sortedFrequencyInfoList = sortFrequencyInfoList(wordFrequencyInfoList);
            return generatePrintLines(sortedFrequencyInfoList);

        } catch (Exception e) {
            return CALCULATE_ERROR;
        }

    }
    private List<WordFrequencyInfo> sortFrequencyInfoList(List<WordFrequencyInfo> wordFrequencyInfoList) {
        return wordFrequencyInfoList.stream()
                .sorted(Comparator.comparingInt(WordFrequencyInfo::getWord).reversed())
                .collect(Collectors.toList());
    }

    private List<WordFrequencyInfo> extractWordFrequencyInfo(Map<String, List<WordFrequencyInfo>> wordFrequencyMap) {
        return wordFrequencyMap.entrySet().stream()
                .map(entry -> new WordFrequencyInfo(entry.getKey(), entry.getValue().size()))
                .collect(Collectors.toList());
    }

    //TODO: suggestion: can remove this and use Collectors.groupingBy in extractWordFrequencyInfo to get the size of the words with same value Collectors.counting
    private List<WordFrequencyInfo> createWordFrequencyInfoList(String[] words) {
        return Arrays.stream(words)
                .map(word -> new WordFrequencyInfo(word, 1))
                .collect(Collectors.toList());
    }

    //TODO: can use String.format to avoid creating a constant
    private static String generatePrintLines(List<WordFrequencyInfo> wordFrequencyInfoList) {
        return wordFrequencyInfoList.stream()
                .map(word -> word.getValue() + SPACE_CHAR + word.getWord())
                .collect(Collectors.joining(NEW_LINE_DELIMITER));
    }

    //TODO: rename this method what does this initialize?
    private Map<String, List<WordFrequencyInfo>> initializeListMap(List<WordFrequencyInfo> wordFrequencyInfoList) {
        return wordFrequencyInfoList.stream()
                .collect(Collectors.groupingBy(WordFrequencyInfo::getValue, Collectors.toList()));
    }

}
