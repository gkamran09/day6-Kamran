import java.util.ArrayList;
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
                Map<String, List<WordFrequencyInfo>> wordFrequencyMap = getListMap(wordFrequencyInfoList);
                List<WordFrequencyInfo> frequencyInfo = new ArrayList<>();
                extractAndAddWordFrequencyInfo(wordFrequencyMap, frequencyInfo);
                wordFrequencyInfoList = frequencyInfo;
                wordFrequencyInfoList.sort((firstWord, secondWord) -> secondWord.getWord() - firstWord.getWord());

                return generatePrintLines(wordFrequencyInfoList);

            } catch (Exception e) {
                return CALCULATE_ERROR;
            }

    }

    private static void extractAndAddWordFrequencyInfo(Map<String, List<WordFrequencyInfo>> wordFrequencyMap, List<WordFrequencyInfo> frequencyInfo) {
        for (Map.Entry<String, List<WordFrequencyInfo>> entry : wordFrequencyMap.entrySet()) {
            WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(entry.getKey(), entry.getValue().size());
            frequencyInfo.add(wordFrequencyInfo);
        }
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
    private Map<String, List<WordFrequencyInfo>>getListMap(List<WordFrequencyInfo> wordFrequencyInfoList) {
        return wordFrequencyInfoList.stream()
                .collect(Collectors.groupingBy(WordFrequencyInfo::getValue, Collectors.toList()));
    }

}
