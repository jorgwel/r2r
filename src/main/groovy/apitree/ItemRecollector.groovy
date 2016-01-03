package apitree

abstract class ItemRecollector<X, Y> {
    private String levelConstant = "    "
    private String levelIndicator = ""
    private String id

    public ItemRecollector() {
    }

    public ItemRecollector(String idOfItemToPrint, String levelIndicator) {
        this.id = idOfItemToPrint
        this.levelIndicator = levelIndicator
    }

    void recursiveRecollect(Map<X, Y> items) {
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