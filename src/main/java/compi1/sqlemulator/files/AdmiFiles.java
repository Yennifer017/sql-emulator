package compi1.sqlemulator.files;

import compi1.sqlemulator.exceptions.DirectoryException;
import compi1.sqlemulator.exceptions.ProjectOpenException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author yenni
 */
public class AdmiFiles {

    public static final String aceptedExtensions[] = {".txt", ".csv"};

    private List<FileProject> currentProject;
    private List<File> openFiles;
    private File currentFile;


    private FilesUtil filesU;
    private DirectoriesUtil directoryU;
    
    private JPanel treeDisplay, openFilesBar;
    
    public AdmiFiles(JPanel treeDirectory) {
        treeDisplay = treeDirectory;

        this.filesU = new FilesUtil();
        this.directoryU = new DirectoriesUtil();
    }

    public void OpenProject() throws ProjectOpenException, DirectoryException {
        if (currentProject != null) {
            throw new ProjectOpenException();
        } else {
            this.currentProject = directoryU.openProject(directoryU.getPathFolder());
            int y = 10, height = 20;
            for (int i = 0; i < currentProject.size(); i++) {
                FileProject fileProject = currentProject.get(i);
                fileProject.setBounds(fileProject.getIdentation() * 10, y,
                        treeDisplay.getWidth() - fileProject.getIdentation()*10 - 30, height);
                fileProject.setLabel(fileProject.getFile().getName());
                fileProject.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { //anadimos el evento de los botones
                        System.out.println("Se ha presionado para abrir un archivo: "
                                + fileProject.getFile().getName());
                    }
                });
                treeDisplay.add(fileProject);
                //treeDirectory.add(fileProject);
                y += height;
            }
            treeDisplay.revalidate();
            treeDisplay.repaint();
            /*treeDirectory.revalidate();
            treeDirectory.repaint();*/
        }
    }

    public void closeProject() throws DirectoryException {
        if (currentProject == null) {
            throw new DirectoryException();
        } else {
            this.currentProject = null;
            clearPanel(treeDisplay);
            clearPanel(openFilesBar);
            openFiles = null;
        }
    }

    public void resizeTreeDirectory() {
        if(currentProject != null){
            int y = 10, height = 20;
            for (int i = 0; i < currentProject.size(); i++) {
                FileProject fileProject = currentProject.get(i);
                fileProject.setBounds(fileProject.getIdentation()*10 - 10, y,
                        treeDisplay.getWidth() - fileProject.getIdentation() * 10, height);
                y += height;
            }
            treeDisplay.revalidate();
            treeDisplay.repaint();
        }
        
    }

    private void clearPanel(JPanel panel) {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }

}
