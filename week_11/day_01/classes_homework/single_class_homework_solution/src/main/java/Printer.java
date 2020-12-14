public class Printer {
    private int paper;
    private int tonerVolume;

    public Printer(int paper, int tonerVolume) {
        this.paper = paper;
        this.tonerVolume = tonerVolume;
    }

    public int getPaper() {
        return paper;
    }

    public void print(int pages, int copies){
        int totalPages = pages * copies;

        if (paper > totalPages){
            paper -= totalPages;
            tonerVolume -= totalPages;
        }
    }

    public int getToner() {
        return tonerVolume;
    }
}
