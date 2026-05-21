public interface Financeable {
    public void printBalance();
    public double balance();
    public boolean hasEnoughMoney();
    public Main.FinanceStatus getFinanceStatus();
}
