import java.util.*;
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
                List<WordFrequencyInfo> frequencyInfo = new ArrayList<>();
                addWordFrequencyInfoToList(wordFrequencyMap, frequencyInfo);
                wordFrequencyInfoList = frequencyInfo;
                List<WordFrequencyInfo> sortedFrequencyInfoList = extractAndSortWordFrequencyInfo(wordFrequencyMap);
                return generatePrintLines(wordFrequencyInfoList);

            } catch (Exception e) {
                return CALCULATE_ERROR;
            }

    }

    private List<WordFrequencyInfo> addWordFrequencyInfoToList(Map<String, List<WordFrequencyInfo>> wordFrequencyMap, List<WordFrequencyInfo> frequencyInfoList) {
        return wordFrequencyMap.entrySet().stream()
                .map(entry -> new WordFrequencyInfo(entry.getKey(), entry.getValue().size()))
                .collect(Collectors.toList());
    }

    private List<WordFrequencyInfo> extractAndSortWordFrequencyInfo(Map<String, List<WordFrequencyInfo>> wordFrequencyMap) {
        List<WordFrequencyInfo> frequencyInfoList = new ArrayList<>();
        addWordFrequencyInfoToList(wordFrequencyMap, frequencyInfoList);

        return frequencyInfoList.stream()
                .sorted(Comparator.comparingInt(WordFrequencyInfo::getWord).reversed())
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
