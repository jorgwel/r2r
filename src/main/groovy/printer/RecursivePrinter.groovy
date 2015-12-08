package printer

/**
 * Created by jorge.bautista on 7/12/15.
 */
abstract class RecursivePrinter<X, Y> {

    private String levelIndicator = ""
    private String id

    public RecursivePrinter() {
    }

    public RecursivePrinter(String idOfItemToPrint, String levelIndicator) {
        this.id = idOfItemToPrint
        this.levelIndicator = levelIndicator
    }

    void traverse(Map<X, Y> items) {

        if (items.size() == 0)
            return

        incrementLevelIndicator()
        printText id + "s: "
        def itemsIterator = items.iterator()

        while (itemsIterator.hasNext())
            printObject itemsIterator.next()

    }

    abstract void printObject(Map.Entry<X, Y> itemEntry);

    void printText(String text) {
        println getLevelIndicator() + text
    }

    String getLevelIndicator() {
        return levelIndicator
    }

    public void incrementLevelIndicator() {
        this.levelIndicator += "\t"
    }

    public void decrementLevelIndicator() {
        if (this.levelIndicator.length() >= 2) {
            this.levelIndicator = this.levelIndicator.substring(0, this.levelIndicator.length() - 1)
        }
    }

}