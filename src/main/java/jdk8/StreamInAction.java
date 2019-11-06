package jdk8;

import jdk8.pojo.Trader;
import jdk8.pojo.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2018-12-04 23:27
 */
public class StreamInAction {


    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader maric = new Trader("Maric", "Mila");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(maric, 2012, 710),
                new Transaction(maric, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        List<Transaction> tr2011 = transactions.stream().filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .collect(toList());

        System.out.println(tr2011);

        List<String> allCities = transactions.stream().map(t -> t.getTrader().getCity()).distinct().collect(toList());
        System.out.println(allCities);

        transactions.stream().map(Transaction::getTrader).filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);

        String reduce = transactions.stream().map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (name1, name2) -> name1 + name2);
        System.out.println(reduce);

        boolean mila = transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("Mila"));
        System.out.println(mila);

        transactions.stream().filter(t -> t.getTrader().getCity().equals("Mila")).map(Transaction::getValue).forEach(System.out::println);

        Integer maxValue = transactions.stream().map(Transaction::getValue).reduce((i, j) -> i > j ? i : j).get();
        System.out.println(maxValue);

        Integer minValue = transactions.stream().map(Transaction::getValue).reduce(Integer::min).get();
        System.out.println(minValue);
    }
}
