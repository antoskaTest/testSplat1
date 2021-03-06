/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsplatsearch;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author tosha
 */
public class SearchFrame extends javax.swing.JFrame {

    /**
     * Creates new form SearchFrame
     */
    private File file;
    private Thread threadTree;
    private int numFileInTextPane = -1;    //номер открытого файла
    private ArrayList<Long> listCountChar;      //количество символов
    private ArrayList<Integer> listCountPage;   //количество страниц
    private ArrayList<Integer> listCount;       //количество строк
    private ArrayList<Integer> listIndex;       //индекс подстроки
    private ArrayList<Long> listFilePointer; //индексы страниц
    private int prevNext = -1;
    private int prevNextPage = 0;   //номер открытой страницы
    private String openedFile;      //имя открытого файла
    private int numPage = 0;        //номер страницы
    private int numPageLight = -1;   //номер страницы для выделения
    private int strCount = 0;
    private long num = 0;
    private boolean flagAllLight = false;
    
    
    public SearchFrame() {
        listCount = new ArrayList<>();
        listIndex = new ArrayList<>();
        listFilePointer = new ArrayList<>();
        listCountPage = new ArrayList<>();
        listCountChar = new ArrayList<>();
         initComponents();
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        butOpen = new javax.swing.JButton();
        lablePath = new javax.swing.JLabel();
        butSearch = new javax.swing.JButton();
        textFieldSearch = new javax.swing.JTextField();
        textFieldEx = new javax.swing.JTextField();
        jScrollPaneText = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPaneTree = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        butAll = new javax.swing.JButton();
        butPrev = new javax.swing.JButton();
        butNext = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        butNextPage = new javax.swing.JButton();
        butPrevPage = new javax.swing.JButton();
        lblNumPage = new javax.swing.JLabel();
        lblNumPageMax = new javax.swing.JLabel();
        butGoTo = new javax.swing.JButton();
        jTextFieldGoTo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Поиск текста в файлах");
        setLocation(new java.awt.Point(0, 0));
        setLocationByPlatform(true);

        butOpen.setText("Открыть");
        butOpen.setPreferredSize(new java.awt.Dimension(80, 25));
        butOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butOpenActionPerformed(evt);
            }
        });

        lablePath.setText("Выберите каталог");
        lablePath.setToolTipText("re");

        butSearch.setText("Поиск");
        butSearch.setPreferredSize(new java.awt.Dimension(80, 25));
        butSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSearchActionPerformed(evt);
            }
        });

        textFieldSearch.setText("Hello World");

        textFieldEx.setText("log");
        textFieldEx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldExActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPaneText.setViewportView(jTextArea1);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.setRootVisible(false);
        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTree1MousePressed(evt);
            }
        });
        jScrollPaneTree.setViewportView(jTree1);

        butAll.setText("Выделить все");
        butAll.setPreferredSize(new java.awt.Dimension(110, 25));
        butAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAllActionPerformed(evt);
            }
        });

        butPrev.setText("Назад");
        butPrev.setPreferredSize(new java.awt.Dimension(75, 25));
        butPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butPrevActionPerformed(evt);
            }
        });

        butNext.setText("Вперед");
        butNext.setPreferredSize(new java.awt.Dimension(75, 25));
        butNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butNextActionPerformed(evt);
            }
        });

        jLabel1.setText("Укажите расширение файла");

        jLabel2.setText("Укажите текст для поиска");

        butNextPage.setText(">>След. страница");
        butNextPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butNextPageActionPerformed(evt);
            }
        });

        butPrevPage.setText("Пред. страница <<");
        butPrevPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butPrevPageActionPerformed(evt);
            }
        });

        lblNumPage.setText("0");

        lblNumPageMax.setText("/ 0");

        butGoTo.setText("Переход к стр");
        butGoTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butGoToActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPaneTree, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(butPrevPage)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNumPage)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNumPageMax)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(butNextPage)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(butGoTo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldGoTo, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
                            .addComponent(jScrollPaneText)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textFieldEx, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(butSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(butAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(butOpen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lablePath, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(butPrev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(butNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butOpen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lablePath))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldEx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butPrev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneText)
                    .addComponent(jScrollPaneTree, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butPrevPage)
                    .addComponent(butNextPage)
                    .addComponent(lblNumPage)
                    .addComponent(lblNumPageMax)
                    .addComponent(butGoTo)
                    .addComponent(jTextFieldGoTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butGoToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butGoToActionPerformed
        // TODO add your handling code here:
        try{
            int input = Integer.parseInt(jTextFieldGoTo.getText());
            if(input < 0 || input >= listFilePointer.size()){
                JOptionPane.showMessageDialog(jPanel1, "Не правильно указана страница");
            }
            else{
                prevNextPage = input;
                showText(prevNextPage);
                lblNumPage.setText(String.valueOf(prevNextPage));
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(jPanel1, "Введите номер страницы");
        }
    }//GEN-LAST:event_butGoToActionPerformed

    private void butPrevPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butPrevPageActionPerformed
        if(openedFile != null){
            prevNextPage--;
            if(prevNextPage < 0)
            prevNextPage = listFilePointer.size() - 1;
            showText(prevNextPage);
            lblNumPage.setText(String.valueOf(prevNextPage));
        }
    }//GEN-LAST:event_butPrevPageActionPerformed

    private void butNextPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butNextPageActionPerformed
        // TODO add your handling code here:
        if(openedFile != null){
            prevNextPage++;
            if(prevNextPage >= listFilePointer.size())
            prevNextPage = 0;
            showText(prevNextPage);
            lblNumPage.setText(String.valueOf(prevNextPage));
        }
    }//GEN-LAST:event_butNextPageActionPerformed

    private void butNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butNextActionPerformed
        // TODO add your handling code here:
        if(openedFile != null){
                numPageLight++;
                if(numPageLight == listCountPage.size())
                numPageLight = 0;
                if(listCountPage.get(numPageLight) != prevNextPage){
                    prevNextPage = listCountPage.get(numPageLight);
                    showText(prevNextPage);
                    System.out.println("вызов showText");
                }
                if(listIndex.isEmpty()){
                    searchIndex();
                    System.out.println("listIndex = " + listIndex.size());
                }
                Highlighter hilite = jTextArea1.getHighlighter();
                hilite.removeAllHighlights();
                prevNext++;
                if(prevNext == listIndex.size())
                prevNext = 0;
                try {
                    hilite.addHighlight(listIndex.get(prevNext),
                        listIndex.get(prevNext) + textFieldSearch.getText().trim().length(),
                        new DefaultHighlightPainter(Color.red));
                    jTextArea1.setCaretPosition(listIndex.get(prevNext));
                } catch (BadLocationException ex) {
                    Logger.getLogger(SearchFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
    }//GEN-LAST:event_butNextActionPerformed

    private void butPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butPrevActionPerformed
        // TODO add your handling code here:
        
        if(openedFile != null){
                numPageLight--;
                if(numPageLight < 0)
                numPageLight =  listCountPage.size() - 1;
                if(listCountPage.get(numPageLight) != prevNextPage){
                    prevNextPage = listCountPage.get(numPageLight);
                    showText(prevNextPage);
                    System.out.println("вызов showText");
                }
                if(listIndex.isEmpty()){
                    searchIndex();
                    System.out.println("listIndex = " + listIndex.size());
                }
                Highlighter hilite = jTextArea1.getHighlighter();
                hilite.removeAllHighlights();
                prevNext--;
                if(prevNext < 0)
                prevNext = listIndex.size() - 1;
                try {
                    hilite.addHighlight(listIndex.get(prevNext),
                        listIndex.get(prevNext) + textFieldSearch.getText().trim().length(),
                        new DefaultHighlightPainter(Color.red));
                    jTextArea1.setCaretPosition(listIndex.get(prevNext));
                } catch (BadLocationException ex) {
                    Logger.getLogger(SearchFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
    }//GEN-LAST:event_butPrevActionPerformed

    private void butAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAllActionPerformed
        flagAllLight = !flagAllLight;
        if(openedFile != null && flagAllLight){
                lightAllWords();
            }else{
                jTextArea1.getHighlighter().removeAllHighlights();
            }
    }//GEN-LAST:event_butAllActionPerformed

    private void jTree1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MousePressed
        // TODO add your handling code here:
        int selRow = jTree1.getRowForLocation(evt.getX(), evt.getY());
        TreePath selPath = jTree1.getPathForLocation(evt.getX(), evt.getY());
        if(selRow != -1) {
            if(evt.getClickCount() == 1) {
                mySingleClick(selRow, selPath);
            }
            else if(evt.getClickCount() == 2) {
                myDoubleClick(selRow, selPath);
            }
        }
    }//GEN-LAST:event_jTree1MousePressed

    private void textFieldExActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldExActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldExActionPerformed

    private void butSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSearchActionPerformed

        if(threadTree != null){
            if(!threadTree.isAlive()){
                startThreadSearch();
            }
            else{
                JOptionPane.showMessageDialog(this, "Поиск еще выполняется");
            }
        }
        else{
            startThreadSearch();
        }
    }//GEN-LAST:event_butSearchActionPerformed

    private void butOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butOpenActionPerformed
        // TODO add your handling code here:
        JFileChooser dialog = new JFileChooser();
        dialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        dialog.showOpenDialog(jPanel1);
        if(dialog.getSelectedFile() != null){
            lablePath.setText(dialog.getSelectedFile().getPath());
            lablePath.setToolTipText(dialog.getSelectedFile().getPath());
            file = dialog.getSelectedFile();
        }
    }//GEN-LAST:event_butOpenActionPerformed

    private void lightAllWords(){
        searchIndex();
         Highlighter hilite = jTextArea1.getHighlighter();
        for(int i = 0; i < listIndex.size(); i++ ){
            try {
                hilite.addHighlight(listIndex.get(i), listIndex.get(i) + textFieldSearch.getText().trim().length(),
                    new DefaultHighlightPainter(Color.red));
            } catch (BadLocationException ex) {
                Logger.getLogger(SearchFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }   
    private void searchNumString(){
        listCount.clear();
        listCountPage.clear();
        int count = 0;
        int countPage = 0;
        long countChar = 0L;
        Data data = Data.getInstance();
        String str;
       
       try(BufferedReader raf = new BufferedReader( new FileReader(data.get(numFileInTextPane)));){
           
           while((str = raf.readLine()) != null){
               if(count >= 100){
                   count = 0;
                   countPage++;
                   listFilePointer.add(countChar);
               }
               int index = str.indexOf(textFieldSearch.getText().trim());
                while(index >= 0){
                    listCount.add(count);
                    listCountPage.add(countPage);
                    index = str.indexOf(textFieldSearch.getText().trim(), index + 1);
                }
               
                count++;
                countChar += (long)str.length() + 2;
           }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SearchFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SearchFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void searchIndex(){
        prevNext = -1;
        String str = jTextArea1.getText();
        listIndex.clear();
        int index = str.indexOf(textFieldSearch.getText().trim());
        while(index >= 0){
            listIndex.add(index);
            index = str.indexOf(textFieldSearch.getText().trim(), index + 1);
        }
       
    }
        
    private void mySingleClick(int selRow, TreePath selPath) {
        
    }

    private void myDoubleClick(int selRow, TreePath selPath) {
        String fileName = selPath.getLastPathComponent().toString();
        if(!fileName.equals(openedFile)){
            openedFile = null;
            listCount.clear();
            listIndex.clear();
            listFilePointer.clear();
            prevNext = -1;
            prevNextPage = 0;
            numPageLight = -1;
            Highlighter hilite = jTextArea1.getHighlighter();
            hilite.removeAllHighlights();
            Data data = Data.getInstance();
            //jTextArea1.setText("Загрузка файла");
            for(int i = 0; i < data.size(); i++){
                if(fileName.equals(data.get(i).getName())){
                    listFilePointer.add(0L);                    
                    numFileInTextPane = i;
                    JOptionPane.showMessageDialog(this, "Загрузка файла может занять некоторое время,\n нажмите ОК для начала");
                    searchNumString();
                    jTextArea1.setText("");
                    lblNumPageMax.setText("/ " + (listFilePointer.size() - 1));
                    jTextArea1.setText("");
                    numFileInTextPane = i; 
                    showText(prevNextPage);
                    openedFile = fileName;
                }
            }
            
        }
    }
    
    private void showText(int seek){
        listIndex.clear();
        prevNext = -1;
       try(RandomAccessFile raf = new RandomAccessFile(Data.getInstance().get(numFileInTextPane), "r")){  
            String str;
            int count = 0;
            jTextArea1.setText("");
            lblNumPage.setText(String.valueOf(prevNextPage));
            raf.seek(listFilePointer.get(seek));
            
            while((str = raf.readLine()) != null && count < 100){
                jTextArea1.setText(jTextArea1.getText() + str + "\n");
                count++;
       }
            jTextArea1.setCaretPosition(0);
       } catch (IOException ex) {
            Logger.getLogger(SearchFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
       if(flagAllLight){
           lightAllWords();
       }
    }
    
    private void startThreadSearch(){
       
       if(file == null){
           JOptionPane.showMessageDialog(jPanel1, "Выберите каталог");
       }
       else if(textFieldSearch.getText().trim().equals("")){
           JOptionPane.showMessageDialog(jPanel1, "Укажите текст для поиска");
       }
       else if(textFieldEx.getText().trim().equals("")){
           JOptionPane.showMessageDialog(jPanel1, "Укажите расширение файла");
       }
       else{
            openedFile = null;
            listCount.clear();
            listIndex.clear();
            Data.getInstance().clear();
            threadTree = new Thread("Главный поток поиска"){
        public void run(){
             SearchFile sFile = new SearchFile(file, textFieldSearch.getText().trim(), "." + textFieldEx.getText().trim() );
             Thread threadSearch = new Thread(sFile, "Поток поиска");
             threadSearch.start();
            try {
                //threadSearch.join();
                jTextArea1.setText("Идет поиск");
                while(threadSearch.isAlive()){
                    jTextArea1.setText(jTextArea1.getText() + ".");
                    if(jTextArea1.getText().length() > 15){ 
                        jTextArea1.setText("Идет поиск");
                    }
                    sleep(500);
                }
                jTextArea1.setText("Поиск завершен");
            } catch (Exception ex) {
                Logger.getLogger(SearchFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
               if(Data.getInstance().isEmpty()){
                   JOptionPane.showMessageDialog(jPanel1, "Ничего не найдено");
                }
               else{
                createTree();
                JOptionPane.showMessageDialog(jPanel1, "Поиск завершен");
               }
            }
        };
       
       threadTree.start();
       }
    }
    
    private void createTree(){
        ArrayList<TreePath> arrPath = new ArrayList<>();
        Data data = Data.getInstance();
        for(int i = 0; i < data.size(); i++){
            arrPath.add(new TreePath(data.get(i).getPath().split("\\\\")));
        }
      
        TreeModel model = new TreePathsTreeModel(arrPath.get(0).getPath()[0].toString(), arrPath );
        jTree1.setModel(model);
        if(!jTree1.isRootVisible()) {
           jTree1.setRootVisible(true);
        }     
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SearchFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butAll;
    private javax.swing.JButton butGoTo;
    private javax.swing.JButton butNext;
    private javax.swing.JButton butNextPage;
    private javax.swing.JButton butOpen;
    private javax.swing.JButton butPrev;
    private javax.swing.JButton butPrevPage;
    private javax.swing.JButton butSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPaneText;
    private javax.swing.JScrollPane jScrollPaneTree;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextFieldGoTo;
    private javax.swing.JTree jTree1;
    private javax.swing.JLabel lablePath;
    private javax.swing.JLabel lblNumPage;
    private javax.swing.JLabel lblNumPageMax;
    private javax.swing.JTextField textFieldEx;
    private javax.swing.JTextField textFieldSearch;
    // End of variables declaration//GEN-END:variables

   
}
