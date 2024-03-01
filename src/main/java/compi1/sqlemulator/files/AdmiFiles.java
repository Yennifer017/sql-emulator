package compi1.sqlemulator.files;

import compi1.sqlemulator.exceptions.DirectoryException;
import compi1.sqlemulator.exceptions.FileException;
import compi1.sqlemulator.exceptions.FileExtensionException;
import compi1.sqlemulator.exceptions.FileOpenException;
import compi1.sqlemulator.exceptions.ProjectOpenException;
import compi1.sqlemulator.util.BinarySearch;
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

    public static final String aceptedExtensions[] = {"txt", "csv"};
    private static final String EMPTY_NOTATION = "[none]";

    private List<FileProject> currentProject;
    private List<OpenFile> openFiles;
    private OpenFile currentFile;

    private UtilForFiles filesU;
    private UtilForDirectories directoryU;
    private CreatorFileIDE creatorFile;

    private JTree treeDisplay;
    private JPanel filesBar;

    public AdmiFiles(JTree treeDisplay, JPanel filesBar) {
        this.treeDisplay = treeDisplay;
        this.filesU = new UtilForFiles();
        this.directoryU = new UtilForDirectories();
        currentProject = new LinkedList<>();
        openFiles = new ArrayList<>();
        this.filesBar = filesBar;
        creatorFile = new CreatorFileIDE();
    }

    public void openProject() throws IOException, ProjectOpenException, DirectoryException, FileOpenException {
        openProject(directoryU.getPathFolder());
    }

    /**
     * Abre un proyecto que el usuario selecionara
     *
     * @param path
     * @throws compi1.sqlemulator.exceptions.ProjectOpenException
     * @throws compi1.sqlemulator.exceptions.DirectoryException
     * @throws java.io.IOException
     * @throws compi1.sqlemulator.exceptions.FileOpenException
     */
    public void openProject(String path)
            throws ProjectOpenException, DirectoryException, IOException, FileOpenException {
        if (!currentProject.isEmpty()) {
            throw new ProjectOpenException();
        }
        if (currentFile != null) {
            throw new FileOpenException();
        } else {
            //abrir el proyecto
            this.currentProject = directoryU.openProject(path);
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
            filesU.saveFile(creatorFile.generateIDEfile(treeDisplay),
                    currentProject.get(0).getFile().getAbsolutePath()
                    + directoryU.getCarpetSeparator() + "settings.ide");
        }
    }

    /**
     * Abre un archivo al tener seleccionado un elemento del arbol de trabajo
     *
     * @param display
     * @param labelForName
     * @throws java.io.IOException
     * @throws compi1.sqlemulator.exceptions.FileOpenException
     * @throws compi1.sqlemulator.exceptions.FileException
     * @throws compi1.sqlemulator.exceptions.FileExtensionException
     */
    public void openFileFromProject(JTextPane display, JLabel labelForName)
            throws IOException, FileOpenException, FileException, FileExtensionException {
        if (currentFile != null) {
            currentFile.setOpenContent(display.getText());
        }
        DefaultMutableTreeNode selectedNode
                = (DefaultMutableTreeNode) treeDisplay.getLastSelectedPathComponent();
        if (selectedNode != null) {
            if (!(selectedNode.getUserObject() instanceof FileProject)) {
                throw new FileException();
            }
            FileProject selectedFileProject = (FileProject) selectedNode.getUserObject();
            File file = selectedFileProject.getFile();
            boolean isAceptedExtension = filesU.hasAceptedPath(aceptedExtensions, file);
            if (file.isFile() // y el archivo no esta abierto aun
                    && BinarySearch.search(openFiles, file.getAbsolutePath()) == -1
                    && isAceptedExtension) {
                String content = filesU.readTextFile(file.getAbsolutePath());
                currentFile = new OpenFile(file, content);
                openFiles.add(currentFile);
                Collections.sort(openFiles);
                display.setText(filesU.readTextFile(file.getAbsolutePath()));
                labelForName.setText(file.getName());
                //anadir los botones
                currentFile.init(display, labelForName, this);
                filesBar.add(currentFile);
            } else if (file.isFile() && isAceptedExtension) { //cuando ya esta abierto
                throw new FileOpenException();
            } else if (!isAceptedExtension && !file.isDirectory()) {
                throw new FileExtensionException();
            }
        }
    }

    /**
     * Ciera el proyecto actual
     *
     * @param display
     * @param labelForName
     * @throws compi1.sqlemulator.exceptions.DirectoryException cuando no hay un
     * proyecto abierto
     */
    public void closeProject(JTextPane display, JLabel labelForName) throws DirectoryException {
        if (currentProject.isEmpty()) {
            throw new DirectoryException();
        } else {
            currentProject.clear();
            openFiles.clear();
            treeDisplay.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("root")));
            treeDisplay.revalidate();
            treeDisplay.repaint();
            closeOpenFiles(display, labelForName);
        }
    }

    private void closeFileInProject(JTextPane display, JLabel labelForName) throws FileException {
        int position = BinarySearch.search(openFiles, currentFile.getFile().getAbsolutePath());
        if (position != -1) {
            openFiles.remove(position);
            filesBar.remove(currentFile);
            filesBar.revalidate();
            filesBar.repaint();
            currentFile = null;
            display.setText("");
            labelForName.setText(EMPTY_NOTATION);
        } else {
            throw new FileException();
        }
    }

    public void closeFile(JTextPane display, JLabel labelForName) throws FileException {
        if (!currentProject.isEmpty()) {
            closeFileInProject(display, labelForName);
        } else if (currentFile != null) {
            closeOpenFiles(display, labelForName);
        }
    }

    public void closeOpenFiles(JTextPane display, JLabel labelForName) {
        filesBar.removeAll();
        filesBar.revalidate();
        filesBar.repaint();
        openFiles.clear();
        currentFile = null;
        display.setText("");
        labelForName.setText(EMPTY_NOTATION);
    }

    /**
     * Guarda el archivo actual
     *
     * @param displayContent donde se tiene escrito el texto
     * @throws compi1.sqlemulator.exceptions.FileException
     */
    public void saveFile(JTextPane displayContent) throws FileException {
        if (currentFile != null) {
            filesU.saveFile(displayContent.getText(), currentFile.getFile());
        } else {
            throw new FileException();
        }
    }

    /**
     * Abre un archivo cuando no hay un proyecto abierto
     *
     * @param display para setear el nombre del archivo
     * @param displayName
     * @throws compi1.sqlemulator.exceptions.ProjectOpenException cuando hay un
     * proyecto abierto
     * @throws java.io.IOException por cualquier excepcion extra
     * @throws compi1.sqlemulator.exceptions.FileExtensionException
     */
    public void openFile(JTextPane display, JLabel displayName)
            throws ProjectOpenException, IOException, FileExtensionException {
        if (!currentProject.isEmpty() || currentFile != null) {
            throw new ProjectOpenException();
        } else {
            File file = new File(filesU.getPath("Archivos svc o txt",
                    aceptedExtensions));
            if (!filesU.hasAceptedPath(aceptedExtensions, file)) {
                throw new FileExtensionException();
            }
            String content = filesU.readTextFile(file.getAbsolutePath());
            display.setText(content);
            displayName.setText(file.getName());
            currentFile = new OpenFile(file, content);
        }
    }

    public boolean exist(String pathWithDots) {
        try {
            String path = pathWithDots.replace(".", directoryU.getCarpetSeparator());
            DefaultTreeModel model = (DefaultTreeModel) treeDisplay.getModel();
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
            FileProject rootNode = (FileProject) root.getUserObject();
            String rootPath = rootNode.getFile().getAbsolutePath();
            rootPath = rootPath.substring(0, rootPath.length() - rootNode.getFile().getName().length());
            File searched = new File(rootPath + path + ".csv");
            return searched.exists() && searched.isFile();
        } catch (Exception e) {
            return false;
        }
    }

    protected void setCurrentFile(OpenFile currentFile) {
        this.currentFile = currentFile;
    }

    protected OpenFile getCurrentFile() {
        return this.currentFile;
    }

}
