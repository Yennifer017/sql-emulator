
package compi1.sqlemulator.files.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author yenni
 */
@Getter @Setter @AllArgsConstructor
public class ColumnsCSV implements Comparable<ColumnsCSV>{
    int realIndex;
    String nameColumn;

    @Override
    public int compareTo(ColumnsCSV o) {
        return this.nameColumn.compareTo(o.nameColumn);
    }
}
