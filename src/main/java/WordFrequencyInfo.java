public class WordFrequencyInfo {
    private String value;
    private int count;

    public WordFrequencyInfo(String initialWord, int i) {
        this.value = initialWord;
        this.count = i;
    }


    public String getValue() {
        return this.value;
    }

    public int getWord() {
        return this.count;
    }


}
