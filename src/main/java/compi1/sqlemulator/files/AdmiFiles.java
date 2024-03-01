package compi1.sqlemulator.files;

import compi1.sqlemulator.exceptions.DirectoryException;
import compi1.sqlemulator.exceptions.ProjectOpenException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

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

    private JTree treeDisplay;

    public AdmiFiles(JTree treeDisplay) {
        this.treeDisplay = treeDisplay;
        this.filesU = new FilesUtil();
        this.directoryU = new DirectoriesUtil();
        
        currentProject = new LinkedList<>();
        openFiles = new LinkedList<>();
    }

    public void OpenProject() throws ProjectOpenException, DirectoryException, IOException {
        if (!currentProject.isEmpty()) {
            throw new ProjectOpenException();
        } else {
            //abrir el proyecto
            this.currentProject = directoryU.openProject(directoryU.getPathFolder());
            //inicializando el arbol
            DefaultMutableTreeNode firstNode = new DefaultMutableTreeNode(currentProject.get(0));
            DefaultTreeModel defaultTreeModel = new DefaultTreeModel(firstNode);
            treeDisplay.setModel(defaultTreeModel);
            
            DefaultMutableTreeNode currentParentModel = firstNode;
            //agregando archivos
            for (int i = 1; i < currentProject.size(); i++) {
                FileProject fileProject = currentProject.get(i);
                DefaultMutableTreeNode currentNode = new DefaultMutableTreeNode(fileProject);
                
                defaultTreeModel.insertNodeInto(currentNode, currentParentModel, 
                        currentParentModel.getChildCount());
                
                
                
                
                if(fileProject.getFile().isDirectory() 
                        && fileProject.getIdentation() == currentProject.get(i-1).getIdentation() 
                        && i!=1){
                    currentParentModel = currentNode;
                } else if(fileProject.getFile().isDirectory()
                        && fileProject.getIdentation() == currentProject.get(i-1).getIdentation()){
                    
                }
            }
            treeDisplay.revalidate();
            treeDisplay.repaint();
        }
    }

    public void closeProject() throws DirectoryException {
        if (currentProject.isEmpty()) {
            throw new DirectoryException();
        } else {
            this.currentProject.removeAll(openFiles);
            treeDisplay.removeAll();
        }
    }

}
