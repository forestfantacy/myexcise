package utinities;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2018-11-17 22:19
 */
public class TableExample {

    @Test
    public void test() {
        HashBasedTable<String, String, String> table = HashBasedTable.create();
        table.put("Language", "java", "1.8");
        table.put("Language", "scale", "2.3");
        table.put("Database", "oracle", "12c");
        table.put("Database", "mysql", "7.0");

        assertThat(table.row("Language").containsKey("java"), is(true));
        assertThat(table.row("Language").get("java"),equalTo("1.8"));

        Map<String, String> result = table.column("java");
        System.out.println(result);

        Set<Table.Cell<String, String, String>> cells = table.cellSet();
        System.out.println(cells);
    }

}
