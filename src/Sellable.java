public interface Sellable {
    void sell(int qty) throws OutOfStockExceptions;
}