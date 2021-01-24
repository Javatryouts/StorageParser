package ru.alfabank.testtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.alfabank.testtask.model.Box;
import ru.alfabank.testtask.model.Item;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;

@Service("ParserService")
public class ParserService {

    private TableService tableService;

    @Autowired
    public void setTableService(TableService tableService) {
        this.tableService = tableService;
    }

    public void parseXml(InputStream inputStream) {
        Document document = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(inputStream);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        NodeList nodeList = document.getChildNodes().item(0).getChildNodes();
        Box rootBox = new Box();
        getElements(nodeList, rootBox);
        saveData(rootBox);
    }

    public void getElements(NodeList nodeList, Box rootBox) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeName().equals("Item")) {
                Item item = Item.of(Long.parseLong(nodeList.item(i).getAttributes().getNamedItem("id").getNodeValue()),
                        rootBox.getId(),
                        nodeList.item(i).getAttributes().getNamedItem("color") == null ? null : nodeList.item(i).getAttributes().getNamedItem("color").getNodeValue());
                rootBox.getItems().add(item);

            } else if (nodeList.item(i).getNodeName().equals("Box")) {
                Box box = Box.of(Long.parseLong(nodeList.item(i).getAttributes().getNamedItem("id").getNodeValue()),
                        rootBox.getId(),
                        new HashSet<>(),
                        new HashSet<>());
                box.setContainedIn(rootBox.getId());
                rootBox.getBoxes().add(box);
                getElements(nodeList.item(i).getChildNodes(), box);
            }
        }
    }

    public void saveData(Box rootBox) {
        rootBox.getBoxes().stream().forEach(box -> tableService.saveBox(box));
        rootBox.getItems().stream().forEach(item -> tableService.saveItem(item));
    }
}

