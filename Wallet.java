public class Wallet {
    private final static double creditLimit = 10000;

    private double debit = 0;
    private double _credit = 0;

    boolean deposit(double q) {
        if (q <= 0) return false;

        if (_credit < 0) {
            if (-_credit >= q) {
                _credit += q;
            } else {
                debit += q + _credit;
                _credit = 0;
            }
        } else {
            debit += q;
        }

        return true;
    }

    boolean withdraw(double q) {
        if (q <= 0) return false;
        if (_credit < 0) {
            if (_credit-q < -creditLimit) {
                return false;
            }
            _credit -= q;
        } else {
            double left = debit - q;
            if (left >= 0) {
                debit -= q;
                return true;
            } else {
                if (left < -creditLimit) {
                    return false;
                }
                debit = 0;
                _credit += left;
            }
        }

        return true;
    }

    double balance() {
        return debit;
    }

    double credit() {
        return _credit;
    }

    double fullBalance() {
        return debit + (creditLimit + _credit);
    }

    public Wallet() {}
    public Wallet(double d, double c) {debit = d; _credit = c;}
}
