package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import mypackage.AndroidType;
import mypackage.CristianJavierType;
import mypackage.JavaType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;


public class Controller {

    //region Controladores
    public TreeView treeView;
    public TextArea txtDefinicion;
    public TextArea txtCodigo;
    public Button btAdd;
    public Button btSave;
    public Button btDelete;
    public TextField txtTitulo;
    public TextField txtRama;
//endregion

    TreeItem<String> root;
    File f = new File("teoria.xml");


    public void initialize() throws IOException, SAXException, ParserConfigurationException {
        tree();
        // newTree();
        txtTitulo.setText("");
        txtDefinicion.setText("");
        txtCodigo.setText("");
    }

    private void tree() {

        try {
            //SAX y DOM (Factoria) en este caso se llama context
            JAXBContext context = JAXBContext.newInstance(CristianJavierType.class);
            //Unmarshaller es para "desunir"  el ficher xml
            // "deusnir" significa separar la logica xml y pasarla
            // a listado de clases JAVA
            Unmarshaller um = context.createUnmarshaller();

            CristianJavierType cj = (CristianJavierType) um.unmarshal(f);

            //para que funcione bien en el archivoTYPE, poner el rootelement correcto del xml
            root = new TreeItem<>("Teoria");
            root.setExpanded(true);

            TreeItem<String> nodoAndroid = new TreeItem<>("Android");
            nodoAndroid.setExpanded(true);
            root.getChildren().add(nodoAndroid);
            TreeItem<String> nodoJava = new TreeItem<>("Java");
            nodoJava.setExpanded(true);
            root.getChildren().add(nodoJava);

            for (int i = 0; i < cj.getAndroid().size(); i++) {
                System.out.println("Android --> Titulo : " + cj.getAndroid().get(i).getTitulo() + ", con la descripcion : " + cj.getAndroid().get(i).getDescripcion());
                TreeItem<String> nodo = new TreeItem<>(cj.getAndroid().get(i).getTitulo());
                nodo.setExpanded(true);
                nodoAndroid.getChildren().add(nodo);
            }
            for (int i = 0; i < cj.getJava().size(); i++) {
                System.out.println("Java --> Titulo : " + cj.getJava().get(i).getTitulo() + ", con la descripcion : " + cj.getJava().get(i).getDescripcion());
                TreeItem<String> nodo = new TreeItem<>(cj.getJava().get(i).getTitulo());
                nodo.setExpanded(true);
                nodoJava.getChildren().add(nodo);
            }
            treeView.setRoot(root);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


    public void newTree() throws ParserConfigurationException, IOException, SAXException {
        File inputFile = new File("teoria.xml");

        //factoria de constructor de documentos
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();

        //documento
        Document doc = dbBuilder.parse(inputFile);
        root = new TreeItem<>(doc.getDocumentElement().getNodeName());
        root.setExpanded(true);

        NodeList nList = doc.getDocumentElement().getChildNodes();

        for (int i = 0; i < nList.getLength(); i++) {
            if (!nList.item(i).getNodeName().contains("#")) {
                TreeItem<String> item = new TreeItem<>(nList.item(i).getNodeName());
                item.setExpanded(true);
                root.getChildren().add(item);
                //System.out.println("-------->"+nList.item(i).getNodeName());

                NodeList nList2 = nList.item(i).getChildNodes();
                for (int j = 0; j < nList2.getLength(); j++) {
                    //System.out.println("--->"+nList2.item(j).getNodeName());
                    if (nList2.item(j).getNodeName().equalsIgnoreCase("Titulo")) {
                        //System.out.println(nList2.item(j).getTextContent());
                        TreeItem<String> item2 = new TreeItem<>(nList2.item(j).getTextContent());
                        item.getChildren().add(item2);
                    }
                }
            }
        }
        treeView.setRoot(root);
    }


    public void MouseClicked(MouseEvent mouseEvent) throws JAXBException {
        int nodo = treeView.getSelectionModel().getSelectedIndex();
        String nodoBuscar = treeView.getTreeItem(nodo).getValue().toString();

        if (!nodoBuscar.equalsIgnoreCase("Teoria")) {
            String padre = treeView.getTreeItem(nodo).getParent().getValue().toString();

            JAXBContext context = JAXBContext.newInstance(CristianJavierType.class);
            Unmarshaller um = context.createUnmarshaller();
            CristianJavierType cj = (CristianJavierType) um.unmarshal(f);
            //System.out.println(padre + "-" + nodoBuscar);
            txtTitulo.setText(treeView.getTreeItem(nodo).getValue().toString());


            if (padre.equalsIgnoreCase("Android")) {
                for (int i = 0; i < cj.getJava().size(); i++) {
                    if (cj.getAndroid().get(i).getTitulo().equalsIgnoreCase(nodoBuscar)) {
                        txtDefinicion.setText(cj.getAndroid().get(i).getDescripcion());
                        txtCodigo.setText(cj.getAndroid().get(i).getCodigo());
                        txtRama.setText("Android");
                    }
                }
            } else if (padre.equalsIgnoreCase("Java")) {
                for (int i = 0; i < cj.getJava().size(); i++) {
                    if (cj.getJava().get(i).getTitulo().equalsIgnoreCase(nodoBuscar)) {
                        txtDefinicion.setText(cj.getJava().get(i).getDescripcion());
                        txtCodigo.setText(cj.getJava().get(i).getCodigo());
                        txtRama.setText("Java");
                    }
                }
            }
        }
    }


    public void Save(ActionEvent actionEvent) throws JAXBException {
        String nombreNodo = txtTitulo.getText();
        JAXBContext context = JAXBContext.newInstance(CristianJavierType.class);
        Unmarshaller um = context.createUnmarshaller();
        CristianJavierType cj = (CristianJavierType) um.unmarshal(f);
        boolean exist = false;
        if (!txtRama.getText().isEmpty()) {
            if (txtRama.getText().equalsIgnoreCase("Java")) {
                for (int i = 0; i < cj.getJava().size(); i++) {
                    if (cj.getJava().get(i).getTitulo().equalsIgnoreCase(nombreNodo)) {
                        cj.getJava().get(i).setCodigo(txtCodigo.getText());
                        cj.getJava().get(i).setDescripcion(txtDefinicion.getText());
                        exist = true;
                    }
                }
            } else if (txtRama.getText().equalsIgnoreCase("Android")) {
                for (int i = 0; i < cj.getAndroid().size(); i++) {
                    if (cj.getAndroid().get(i).getTitulo().equalsIgnoreCase(nombreNodo)) {
                        cj.getAndroid().get(i).setCodigo(txtCodigo.getText());
                        cj.getAndroid().get(i).setDescripcion(txtDefinicion.getText());
                        exist = true;
                    }
                }
            }

            if (exist) {
                guardar(cj);
            } else {
                if (txtRama.getText().equalsIgnoreCase("Java")) {
                    JavaType jt = new JavaType();
                    jt.setTitulo(txtTitulo.getText());
                    jt.setDescripcion(txtDefinicion.getText());
                    jt.setCodigo(txtCodigo.getText());
                    cj.getJava().add(jt);
                } else if (txtRama.getText().equalsIgnoreCase("Android")) {
                    AndroidType at = new AndroidType();
                    at.setTitulo(txtTitulo.getText());
                    at.setDescripcion(txtDefinicion.getText());
                    at.setCodigo(txtCodigo.getText());
                    cj.getAndroid().add(at);
                }
                guardar(cj);
            }
        }
        else
        {
            JavaType jt = new JavaType();
            jt.setTitulo(txtTitulo.getText());
            jt.setDescripcion(txtDefinicion.getText());
            jt.setCodigo(txtCodigo.getText());
            cj.getJava().add(jt);
            guardar(cj);
        }

    }

    private void guardar(CristianJavierType pkdex) {
        try {
            JAXBContext context = JAXBContext.newInstance(CristianJavierType.class);

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(pkdex, f);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void Add(ActionEvent actionEvent) throws JAXBException {
        int nodo = treeView.getSelectionModel().getSelectedIndex();
        String nodoBuscar = treeView.getTreeItem(nodo).getValue().toString();

        if (!nodoBuscar.equalsIgnoreCase("Teoria")) {
            String padre = treeView.getTreeItem(nodo).getParent().getValue().toString();

            txtTitulo.setText("");
            txtDefinicion.setText("");
            txtCodigo.setText("");
            txtRama.setText("");
        }



    }

    public void Delete(ActionEvent actionEvent) {

    }


    //infiormacion sobre tree view
    //http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/tree-view.htm#BABDEADA
}
