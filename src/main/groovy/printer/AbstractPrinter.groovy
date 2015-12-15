package printer

/**
 * Created by jorge.bautista on 7/12/15.
 */
abstract class AbstractPrinter<X, Y> {
    def levelConstant = "    "
    def levelIndicator = ""
    def id

    public AbstractPrinter() {
    }

    public AbstractPrinter(String idOfItemToPrint, String levelIndicator) {
        this.id = idOfItemToPrint
        this.levelIndicator = levelIndicator
    }

    void traverse(Map<X, Y> items) {
        if(items == null)
            items = Collections.emptyMap()

        if (items.size() == 0)
            return

        printText id + "s: "
        def itemsIterator = items.iterator()
        incrementLevelIndicator()
        while (itemsIterator.hasNext())
            printObject itemsIterator.next()
        decrementLevelIndicator()

    }

    abstract void printObject(Map.Entry<X, Y> itemEntry);

    void printText(String text) {
        println getLevelIndicator() + text
    }

    String getLevelIndicator() {
        return levelIndicator
    }

    public void incrementLevelIndicator() {
        this.levelIndicator += this.levelConstant
    }

    public void decrementLevelIndicator() {
        if (this.levelIndicator.length() >= 4) {
            this.levelIndicator = this.levelIndicator.substring(0, this.levelIndicator.length() - 4)
        }
    }

}