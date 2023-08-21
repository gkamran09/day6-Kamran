import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String SPACE_DELIMITER = "\\s+";
    public static final String NEW_LINE_DELIMITER = "\n";
    public static final String SPACE_CHAR = " ";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String SET_NUMBER_ONE = " 1";

    public String getResult(String inputStr) {

        String[] words = inputStr.split(SPACE_DELIMITER);
        if (words.length == 1) {
            return inputStr + SET_NUMBER_ONE;
        } else {
            try {
                List<WordFrequencyInfo> wordFrequencyInfoList = new ArrayList<>();
                extracted(words, wordFrequencyInfoList);
                Map<String, List<WordFrequencyInfo>> wordFrequencyMap = getListMap(wordFrequencyInfoList);

                List<WordFrequencyInfo> frequencyInfo = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequencyInfo>> entry : wordFrequencyMap.entrySet()) {
                    WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(entry.getKey(), entry.getValue().size());
                    frequencyInfo.add(wordFrequencyInfo);
                }
                wordFrequencyInfoList = frequencyInfo;

                wordFrequencyInfoList.sort((firstWord, secondWord) -> secondWord.getWord() - firstWord.getWord());

                return generatePrintLines(wordFrequencyInfoList);
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private static void extracted(String[] words, List<WordFrequencyInfo> wordFrequencyInfoList) {
        for (String word : words) {
            WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(word, 1);
            wordFrequencyInfoList.add(wordFrequencyInfo);
        }
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
