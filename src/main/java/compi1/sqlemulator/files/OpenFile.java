package compi1.sqlemulator.files;

import com.sun.source.tree.BreakTree;
import java.io.File;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author yenni
 */
@Getter
@Setter
@AllArgsConstructor
public class OpenFile implements Comparable<OpenFile> {

    private File file;
    private String openContent;

    @Override
    public int compareTo(OpenFile o) {
         return this.file.getName().compareTo(o.getFile().getName());
    }
}
