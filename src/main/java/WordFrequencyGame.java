import java.util.Arrays;
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
        if (words.length == 1) {
            return inputString + SET_NUMBER_ONE;
        }
            try {
                List<WordFrequencyInfo> wordFrequencyInfoList = createWordFrequencyInfoList(words);
                Map<String, List<WordFrequencyInfo>> wordFrequencyMap = initializeListMap(wordFrequencyInfoList);
                wordFrequencyInfoList = extractWordFrequencyInfo(wordFrequencyMap);
                wordFrequencyInfoList.sort((firstWord, secondWord) -> secondWord.getWord() - firstWord.getWord());

                return generatePrintLines(wordFrequencyInfoList);

            } catch (Exception e) {
                return CALCULATE_ERROR;
            }

    }
    private List<WordFrequencyInfo> extractWordFrequencyInfo(Map<String, List<WordFrequencyInfo>> wordFrequencyMap) {
        return wordFrequencyMap.entrySet().stream()
                .map(entry -> new WordFrequencyInfo(entry.getKey(), entry.getValue().size()))
                .collect(Collectors.toList());
    }
    private List<WordFrequencyInfo>createWordFrequencyInfoList(String[] words) {
        return Arrays.stream(words)
                .map(word -> new WordFrequencyInfo(word, 1))
                .collect(Collectors.toList());
    }
    private static String generatePrintLines(List<WordFrequencyInfo> wordFrequencyInfoList) {
        return wordFrequencyInfoList.stream()
                .map(word -> word.getValue() + SPACE_CHAR + word.getWord())
                .collect(Collectors.joining(NEW_LINE_DELIMITER));
    }
    private Map<String, List<WordFrequencyInfo>> initializeListMap(List<WordFrequencyInfo> wordFrequencyInfoList) {
        return wordFrequencyInfoList.stream()
                .collect(Collectors.groupingBy(WordFrequencyInfo::getValue, Collectors.toList()));
    }

}
