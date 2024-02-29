
package compi1.sqlemulator.files;

import javax.swing.JFileChooser;

/**
 *
 * @author yenni
 */
public class DirectoriesUtil {
    public String getPathFolder(){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(null);
        return chooser.getSelectedFile().getAbsolutePath();
    }
}
