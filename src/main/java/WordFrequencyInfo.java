public class WordFrequencyInfo {
    private String value;
    private int count;

    public WordFrequencyInfo(String initialWord, int wordCount) {
        this.value = initialWord;
        this.count = wordCount;
    }


    public String getValue() {
        return this.value;
    }

    public int getWord() {
        return this.count;
    }


}
