package compi1.sqlemulator;

/**
 *
 * @author yenni
 */
import compi1.sqlemulator.exceptions.DirectoryException;
import compi1.sqlemulator.exceptions.FileException;
import compi1.sqlemulator.exceptions.FileOpenException;
import compi1.sqlemulator.exceptions.ProjectOpenException;
import compi1.sqlemulator.files.AdmiFiles;
import compi1.sqlemulator.util.NumberLine;
import java.awt.Dimension;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Fronted extends javax.swing.JFrame {

    //FIELDS
    private NumberLine numConsole, numDisplayFile;
    private AdmiFiles admiFiles;

    /**
     * Creates new form Fronted
     */
    public Fronted() {
        initComponents();
        this.setLocationRelativeTo(null);
        initNumeracion();
        initVariables();
    }

    private void initVariables() {
        admiFiles = new AdmiFiles(treeDisplay, openFilesPanel);
    }

    private void resizeComponents() {
        treeDirectory.setPreferredSize(new Dimension((int) (0.15 * this.getWidth()), this.getHeight()));
        interfazPanel.setPreferredSize(new Dimension((int) 0.85 * this.getWidth(), this.getHeight()));
    }

    private void initNumeracion() {
        numDisplayFile = new NumberLine(display);
        displayScroll.setRowHeaderView(numDisplayFile);
        numConsole = new NumberLine(console);
        consoleScroll.setRowHeaderView(numConsole);
    }

    private void closeProject() {
        try {
            admiFiles.closeProject(display, fileNameDisplay);
            display.setText("");
        } catch (DirectoryException ex) {
            System.out.println("Excepcion de directorio controlada");
        }
    }

    private void closeFile() {
        try {
            admiFiles.closeFile(display, fileNameDisplay);
        } catch (FileException ex1) {
            showInesperatedError();
        }
    }

    private void openProject() {
        try {
            admiFiles.OpenProject();
        } catch (ProjectOpenException ex) {
            if (JOptionPane.showConfirmDialog(null, "Deseas cerar el proyecto actual?",
                    "Cerrar proyecto", JOptionPane.YES_NO_OPTION) == 0) {
                closeProject();
                this.openProject();
            }
        } catch (DirectoryException ex) {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado un proyecto valido",
                    "Error", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException ex) {
            System.out.println("Excepcion controlada");
        } catch (FileOpenException ex) {
            if (JOptionPane.showConfirmDialog(null, "Hay un archivo abierto, deseas cerrarlo?",
                    "Cerrar archivo", JOptionPane.YES_NO_OPTION) == 0) {
                closeFile();
            }
        }
    }

    private void showInesperatedError() {
        JOptionPane.showMessageDialog(null, "Ocurrio un error inesperado",
                "Error", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        treeDirectory = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        treeDisplay = new javax.swing.JTree();
        interfazPanel = new javax.swing.JPanel();
        displayScroll = new javax.swing.JScrollPane();
        display = new javax.swing.JTextPane();
        consoleScroll = new javax.swing.JScrollPane();
        console = new javax.swing.JTextPane();
        openFilesPanel = new javax.swing.JPanel();
        ClearBtn = new javax.swing.JButton();
        archivoTxt = new javax.swing.JLabel();
        fileNameDisplay = new javax.swing.JLabel();
        menu = new javax.swing.JMenuBar();
        projectMenu = new javax.swing.JMenu();
        openDirectoryOp = new javax.swing.JMenuItem();
        createProyectOp = new javax.swing.JMenuItem();
        closeProjectOp = new javax.swing.JMenuItem();
        fileMenu = new javax.swing.JMenu();
        openFileOp = new javax.swing.JMenuItem();
        newFileOp = new javax.swing.JMenuItem();
        saveOp = new javax.swing.JMenuItem();
        saveAsOp = new javax.swing.JMenuItem();
        CloseFileOp = new javax.swing.JMenuItem();
        Information = new javax.swing.JMenu();
        helpOp = new javax.swing.JMenuItem();
        creditsOp = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });

        treeDirectory.setBackground(new java.awt.Color(0, 0, 0));

        treeDisplay.setBackground(new java.awt.Color(51, 51, 51));
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        treeDisplay.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        treeDisplay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                treeDisplayMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(treeDisplay);

        javax.swing.GroupLayout treeDirectoryLayout = new javax.swing.GroupLayout(treeDirectory);
        treeDirectory.setLayout(treeDirectoryLayout);
        treeDirectoryLayout.setHorizontalGroup(
            treeDirectoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
        );
        treeDirectoryLayout.setVerticalGroup(
            treeDirectoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
        );

        interfazPanel.setBackground(new java.awt.Color(20, 20, 20));
        interfazPanel.setForeground(new java.awt.Color(13, 13, 13));

        display.setBackground(new java.awt.Color(0, 0, 43));
        display.setForeground(new java.awt.Color(234, 234, 234));
        display.setCaretColor(new java.awt.Color(255, 255, 255));
        displayScroll.setViewportView(display);

        console.setBackground(new java.awt.Color(0, 0, 43));
        console.setForeground(new java.awt.Color(234, 234, 234));
        console.setCaretColor(new java.awt.Color(255, 255, 255));
        consoleScroll.setViewportView(console);

        openFilesPanel.setBackground(new java.awt.Color(0, 0, 0));

        ClearBtn.setBackground(new java.awt.Color(0, 0, 102));
        ClearBtn.setForeground(new java.awt.Color(204, 204, 204));
        ClearBtn.setText("Limpiar");
        ClearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearBtnActionPerformed(evt);
            }
        });

        archivoTxt.setForeground(new java.awt.Color(255, 255, 255));
        archivoTxt.setText("Archivo actual: ");

        fileNameDisplay.setForeground(new java.awt.Color(255, 255, 255));
        fileNameDisplay.setText("[none]");

        javax.swing.GroupLayout interfazPanelLayout = new javax.swing.GroupLayout(interfazPanel);
        interfazPanel.setLayout(interfazPanelLayout);
        interfazPanelLayout.setHorizontalGroup(
            interfazPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(openFilesPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(interfazPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(interfazPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(displayScroll)
                    .addComponent(consoleScroll)
                    .addGroup(interfazPanelLayout.createSequentialGroup()
                        .addComponent(archivoTxt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fileNameDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 400, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, interfazPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(ClearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        interfazPanelLayout.setVerticalGroup(
            interfazPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(interfazPanelLayout.createSequentialGroup()
                .addComponent(openFilesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(interfazPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fileNameDisplay)
                    .addComponent(archivoTxt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(displayScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ClearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(consoleScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        projectMenu.setText("Proyecto");

        openDirectoryOp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        openDirectoryOp.setText("Abrir carpeta");
        openDirectoryOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openDirectoryOpActionPerformed(evt);
            }
        });
        projectMenu.add(openDirectoryOp);

        createProyectOp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        createProyectOp.setText("Crear proyecto");
        projectMenu.add(createProyectOp);

        closeProjectOp.setText("Cerrar proyecto");
        closeProjectOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeProjectOpActionPerformed(evt);
            }
        });
        projectMenu.add(closeProjectOp);

        menu.add(projectMenu);

        fileMenu.setText("Archivo");

        openFileOp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        openFileOp.setText("Abrir archivo");
        openFileOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileOpActionPerformed(evt);
            }
        });
        fileMenu.add(openFileOp);

        newFileOp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        newFileOp.setText("Nuevo archivo");
        fileMenu.add(newFileOp);

        saveOp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        saveOp.setText("Guardar");
        saveOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveOpActionPerformed(evt);
            }
        });
        fileMenu.add(saveOp);

        saveAsOp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        saveAsOp.setText("Guardar como");
        saveAsOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsOpActionPerformed(evt);
            }
        });
        fileMenu.add(saveAsOp);

        CloseFileOp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        CloseFileOp.setText("Cerrar archivo");
        CloseFileOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseFileOpActionPerformed(evt);
            }
        });
        fileMenu.add(CloseFileOp);

        menu.add(fileMenu);

        Information.setText("Informacion");

        helpOp.setText("Ayuda");
        helpOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpOpActionPerformed(evt);
            }
        });
        Information.add(helpOp);

        creditsOp.setText("Creditos");
        creditsOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditsOpActionPerformed(evt);
            }
        });
        Information.add(creditsOp);

        menu.add(Information);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(treeDirectory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(interfazPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(interfazPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(treeDirectory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openDirectoryOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openDirectoryOpActionPerformed
        openProject();
    }//GEN-LAST:event_openDirectoryOpActionPerformed

    private void openFileOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileOpActionPerformed
        try {
            admiFiles.openFile(display, fileNameDisplay);
        } catch (ProjectOpenException ex) {
            JOptionPane.showMessageDialog(null, 
                    "Hay un archivo o proyecto abierto cierralo y vuelve a intentarlo");
        } catch (IOException ex) {
            System.out.println("Excepcion controlada");
        }
        
    }//GEN-LAST:event_openFileOpActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        resizeComponents();
    }//GEN-LAST:event_formComponentResized

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        if (evt.getNewState() == MAXIMIZED_BOTH || evt.getNewState() == MAXIMIZED_HORIZ
                || evt.getNewState() == MAXIMIZED_VERT || evt.getNewState() == 0) {
            this.resizeComponents();
        }
    }//GEN-LAST:event_formWindowStateChanged

    private void ClearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearBtnActionPerformed
        console.setText("");
    }//GEN-LAST:event_ClearBtnActionPerformed

    private void helpOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpOpActionPerformed
        JOptionPane.showMessageDialog(null,
                "Puedes consultar el manual de usuario en el repositorio de github",
                "Ayuda", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_helpOpActionPerformed

    private void creditsOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditsOpActionPerformed
        JOptionPane.showMessageDialog(null,
                "Proyecto creado con un poquito (demasiado) de desesperacion...\n    --Yennifer",
                "Ayuda", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_creditsOpActionPerformed

    private void treeDisplayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treeDisplayMouseClicked
        if (evt.getClickCount() == 2) {
            try {
                admiFiles.openFileFromProject(display, fileNameDisplay);
            } catch (IOException ex) {
                showInesperatedError();
            } catch (FileOpenException ex) {
                JOptionPane.showMessageDialog(null, "El archivo ya esta abierto");
            } catch (FileException ex){
                System.out.println("Excepcion controlada");
            }
        }
    }//GEN-LAST:event_treeDisplayMouseClicked

    private void saveOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveOpActionPerformed
        try {
            admiFiles.saveFile(display);
        } catch (FileException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar el archivo :/");
        }
    }//GEN-LAST:event_saveOpActionPerformed

    private void saveAsOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsOpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveAsOpActionPerformed

    private void CloseFileOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseFileOpActionPerformed
        this.closeFile();
    }//GEN-LAST:event_CloseFileOpActionPerformed

    private void closeProjectOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeProjectOpActionPerformed
        closeProject();
    }//GEN-LAST:event_closeProjectOpActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ClearBtn;
    private javax.swing.JMenuItem CloseFileOp;
    private javax.swing.JMenu Information;
    private javax.swing.JLabel archivoTxt;
    private javax.swing.JMenuItem closeProjectOp;
    private javax.swing.JTextPane console;
    private javax.swing.JScrollPane consoleScroll;
    private javax.swing.JMenuItem createProyectOp;
    private javax.swing.JMenuItem creditsOp;
    private javax.swing.JTextPane display;
    private javax.swing.JScrollPane displayScroll;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JLabel fileNameDisplay;
    private javax.swing.JMenuItem helpOp;
    private javax.swing.JPanel interfazPanel;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenuItem newFileOp;
    private javax.swing.JMenuItem openDirectoryOp;
    private javax.swing.JMenuItem openFileOp;
    private javax.swing.JPanel openFilesPanel;
    private javax.swing.JMenu projectMenu;
    private javax.swing.JMenuItem saveAsOp;
    private javax.swing.JMenuItem saveOp;
    private javax.swing.JPanel treeDirectory;
    private javax.swing.JTree treeDisplay;
    // End of variables declaration//GEN-END:variables
}
