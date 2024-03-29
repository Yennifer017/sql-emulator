package compi1.sqlemulator.files;

import compi1.sqlemulator.files.models.FileProject;
import compi1.sqlemulator.exceptions.DirectoryException;
import compi1.sqlemulator.exceptions.InvalidDataException;
import java.util.List;
import java.nio.file.Files;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public void createDirectory(String rootPath, String name) throws IOException, InvalidDataException {
        File directory = new File(rootPath + getCarpetSeparator() + name);
        if (!directory.exists()) {
            directory.mkdir();
        } else {
            throw new InvalidDataException("El nombre del proyecto ya existe");
        }
    }

    public String copyFilesToPath(String rootPath, File[] files) {
        String mss = "";
        for (int i = 0; i < files.length; i++) {
            try {
                Path source = Paths.get(files[i].getAbsolutePath());
                Path out = Paths.get(rootPath, getCarpetSeparator() + files[i].getName());// Create a new path for each file
                Files.copy(source, out);
            } catch (Exception e) {
                mss += "No se pudo copiar el archivo: " + files[i].getName() + "\n";
            }
        }
        return mss;
    }

    public String getCarpetSeparator() {
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().contains("windows")) {
            return "\\";
        } else {
            return "/";
        }
    }
    
    public static String getCarpetSeparatorStatic() {
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().contains("windows")) {
            return "\\";
        } else {
            return "/";
        }
    }
}
