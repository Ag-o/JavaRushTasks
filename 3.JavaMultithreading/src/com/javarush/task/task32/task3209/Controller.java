package com.javarush.task.task32.task3209;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    public void resetDocument() {
        if (document != null) {
            document.removeUndoableEditListener(view.getUndoListener());
        }
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        document = (HTMLDocument) htmlEditorKit.createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }

    public void setPlainText(String text) {
        try {
            resetDocument();
            StringReader stringReader = new StringReader(text);
            new HTMLEditorKit().read(stringReader, document, 0);
            stringReader.close();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public String getPlainText() {
        StringWriter stringWriter = new StringWriter();
        try {
            HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
            htmlEditorKit.write(stringWriter, document, 0, document.getLength());
            stringWriter.flush();
            stringWriter.close();
            return stringWriter.toString();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
        return stringWriter.toString();
    }

    public HTMLDocument getDocument() {
        return document;
    }

    public void init() {
        createNewDocument();
    }

    public void createNewDocument() {
        resetDocument();
        view.selectHtmlTab();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile = null;

    }

    public void openDocument() {
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());
        jFileChooser.setDialogTitle("Open File");

        if (jFileChooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
            currentFile = jFileChooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            try {
                FileReader fileReader = new FileReader(currentFile);
                new HTMLEditorKit().read(fileReader, document, 0);
                fileReader.close();
                view.resetUndo();
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocument() {
        view.selectHtmlTab();
        if (currentFile != null) {
            try {
                FileWriter fileWriter = new FileWriter(currentFile);
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
        if (currentFile == null) {
            saveDocumentAs();
        }
    }

    public void saveDocumentAs() {
        //1. Переключать представление на html вкладку.
        view.selectHtmlTab();
        //2. Создавать новый объект для выбора файла JFileChooser.
        JFileChooser jFileChooser = new JFileChooser();
        //3. Устанавливать ему в качестве фильтра объект HTMLFileFilter.
        jFileChooser.setFileFilter(new HTMLFileFilter());
        //4. Показывать диалоговое окно "Save File" для выбора файла.
        jFileChooser.setDialogTitle("Save File");
        //5. Если пользователь подтвердит выбор файла:
        if (jFileChooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
            //5.1. Сохранять выбранный файл в поле currentFile.
            currentFile = jFileChooser.getSelectedFile();
            //5.2. Устанавливать имя файла в качестве заголовка окна представления.
            view.setTitle(currentFile.getName());
            try {
                //5.3. Создавать FileWriter на базе currentFile.
                FileWriter fileWriter = new FileWriter(currentFile);
                //5.4. Переписывать данные из документа document в объекта FileWriter-а
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        } else {
            jFileChooser = null;
            return;
        }
    }

    public void exit() {
        System.exit(0);
    }

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }
}
