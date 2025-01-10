class BankTransferException extends Exception
{
    public BankTransferException(String message)
    {
        super(message);
    }
}

class Account
{
    private String owner;
    private int balance;
    private String accountNumber;

    //konstruktor do inicjalizacji konta
    public Account(String owner, int balance, String accountNumber)
    {
        this.owner = owner;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    //metoda zwracająca aktualne saldo
    public int getBalance()
    {
        return balance;
    }

    //metoda do wykonania przelewu
    public void transfer(int amount) throws BankTransferException
    {
        validateAmount(amount);
        balance -= amount;
        System.out.println("Przelew wykonany na kwotę: " + amount);
    }

    //metoda sprawdzająca, czy przelew jest możliwy
    private void validateAmount(int amount) throws BankTransferException
    {
        if (amount <= 0)
        {
            throw new BankTransferException("Kwota przelewu musi być większa od zera: " + amount);
        }
        if (amount > balance)
        {
            throw new BankTransferException("Brak wystarczających środków na koncie: " + owner);
        }
    }
}

public class Main
{
    public static void main(String[] args)
    {
        //inicjalizowanie konta użytkownika
        Account account = new Account("Adam Lukasiewicz", 123, "8888 4444 1111 2222");

        performTransfer(account, 6000);
    }

    //wykonanie przelewu
    private static void performTransfer(Account account, int amount)
    {
        try
        {
            account.transfer(amount);
        }
        catch (BankTransferException e)
        {
            System.out.println("Nie udało się wykonać przelewu: " + e.getMessage());
        }
        finally
        {
            System.out.println("Aktualne saldo konta: " + account.getBalance());
        }
    }
}