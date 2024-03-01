package compi1.sqlemulator.files;

import compi1.sqlemulator.exceptions.DirectoryException;
import compi1.sqlemulator.exceptions.FileException;
import compi1.sqlemulator.exceptions.FileOpenException;
import compi1.sqlemulator.exceptions.ProjectOpenException;
import java.awt.Button;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
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
    private List<OpenFile> openFiles;
    private OpenFile currentFile;

    private FilesUtil filesU;
    private DirectoriesUtil directoryU;

    private JTree treeDisplay;
    private JPanel filesBar;

    public AdmiFiles(JTree treeDisplay, JPanel filesBar) {
        this.treeDisplay = treeDisplay;
        this.filesU = new FilesUtil();
        this.directoryU = new DirectoriesUtil();
        currentProject = new LinkedList<>();
        openFiles = new ArrayList<>();
        this.filesBar = filesBar;
    }

    public void OpenProject() 
            throws ProjectOpenException, DirectoryException, IOException, FileOpenException {
        if (!currentProject.isEmpty()) {
            throw new ProjectOpenException();
        } if(currentFile != null){
            throw new FileOpenException();
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

                FileProject parentFileObj = (FileProject) currentParentModel.getUserObject();
                while (fileProject.getIdentation() <= parentFileObj.getIdentation()) {
                    currentParentModel = (DefaultMutableTreeNode) currentParentModel.getParent();
                    parentFileObj = (FileProject) currentParentModel.getUserObject();
                }
                defaultTreeModel.insertNodeInto(currentNode, currentParentModel,
                        currentParentModel.getChildCount());

                if (fileProject.getFile().isDirectory()) {
                    currentParentModel = currentNode;
                }
            }
            treeDisplay.revalidate();
            treeDisplay.repaint();
        }
    }

    public void openFileFromProject(JTextPane display, JLabel labelForName) throws IOException {
        if (currentFile != null) {
            currentFile.setOpenContent(display.getText());
        }
        DefaultMutableTreeNode selectedNode
                = (DefaultMutableTreeNode) treeDisplay.getLastSelectedPathComponent();
        if (selectedNode != null) { // y el archivo no esta abierto aun
            FileProject selectedFile = (FileProject) selectedNode.getUserObject();
            if (selectedFile.getFile().isFile()) {
                File file = selectedFile.getFile();
                String content = filesU.readTextFile(file.getAbsolutePath());
                currentFile = new OpenFile(file, content);
                openFiles.add(currentFile);
                Collections.sort(openFiles);
                display.setText(filesU.readTextFile(selectedFile.getFile().getAbsolutePath()));
                labelForName.setText(file.getName());
                filesBar.add(new Button(file.getName()));
            }
        }
    }

    public void closeProject() throws DirectoryException {
        if (currentProject.isEmpty()) {
            throw new DirectoryException();
        } else {
            currentProject.clear();
            openFiles.clear();
            treeDisplay.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("root")));
            treeDisplay.revalidate();
            treeDisplay.repaint();
        }
    }
    
    public void closeFile() throws FileException, FileOpenException{
        //TODO: arreglar esto
    }

    public void saveFile(JTextPane displayContent) throws FileException {
        if (currentFile != null) {
            filesU.saveFile(displayContent.getText(), currentFile.getFile());
        } else {
            throw new FileException();
        }
    }

    public void openFile(JTextPane display) throws ProjectOpenException, IOException {
        if(!currentProject.isEmpty()){
            throw new ProjectOpenException();
        }else{
            File file = new File(filesU.getPath("Archivos svc o txt", 
                    aceptedExtensions));
            display.setText(filesU.readTextFile(file.getAbsolutePath()));
        }
    }

}
