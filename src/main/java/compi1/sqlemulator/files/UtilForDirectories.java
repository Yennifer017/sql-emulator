package compi1.sqlemulator.files;

import compi1.sqlemulator.files.models.FileProject;
import compi1.sqlemulator.exceptions.DirectoryException;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JFileChooser;

/**
 *
 * @author yenni
 */
public class UtilForDirectories {

    public String getPathFolder() throws IOException {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.showOpenDialog(null);
            return chooser.getSelectedFile().getAbsolutePath();
        } catch (NullPointerException e) {
            throw new IOException();
        }
    }

    public List<FileProject> openProject(String superPath) throws DirectoryException {
        List<FileProject> filesAtProject = new LinkedList<>();
        File carpet = new File(superPath);
        if (carpet.exists() && carpet.isDirectory()) {
            filesAtProject.add(new FileProject(carpet, 0));
            File[] subFiles = carpet.listFiles();
            for (File subFile : subFiles) {
                filesAtProject.add(new FileProject(subFile, 1));
                if (subFile.isDirectory()) {
                    openCarpet(filesAtProject, subFile, 2);
                }
            }
        } else {
            throw new DirectoryException();
        }
        return filesAtProject;
    }

    private void openCarpet(List<FileProject> list, File carpet, int identation) throws DirectoryException {
        if (carpet.exists() && carpet.isDirectory()) {
            File[] subFiles = carpet.listFiles();
            for (File subFile : subFiles) {
                list.add(new FileProject(subFile, identation));
                if (subFile.isDirectory()) {
                    openCarpet(list, subFile, identation + 1);
                }
            }
        } else {
            throw new DirectoryException();
        }
    }

    
    
    public void createCarpet() {
        //TODO: implement the function
    }

    public String getCarpetSeparator() {
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().contains("windows")) {
            return "\\";
        } else {
            return "/";
        }
    }
}
