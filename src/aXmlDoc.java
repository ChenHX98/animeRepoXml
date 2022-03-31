import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.ObjectOutput;
import java.util.Scanner;

public class aXmlDoc {
    aXmlDoc() throws ParserConfigurationException, TransformerException {
        Scanner scan=new Scanner(System.in);
        System.out.println("请输入下一个动漫名");
        String dirName=scan.nextLine();
        File dir=new File(dirName);
        dir.mkdir();
        getxml();
    }

    private void getxml() throws ParserConfigurationException, TransformerException {
        Scanner scan=new Scanner(System.in);

        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder =documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        Element animes=document.createElement("animes");
        boolean corn;
        do{
            addAkind(animes,document);
            System.out.println("还要继续吗？（y/n）（0/1）");
            String c=scan.nextLine();
            switch(c){
                case "y", "0" ->corn=true;
                case "n","1"->corn=false;
                default -> corn=false;
            }
        }while(corn);
        document.appendChild(animes);

        TransformerFactory transformerFactory=TransformerFactory.newInstance();
        Transformer transformer=transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        transformer.transform(new DOMSource(document),new StreamResult(new File("animeInfo.xml")));

    }

    private void addAkind(Element animesNode, Document document) {
        Scanner scan=new Scanner(System.in);

        Element kindNode=document.createElement("kind");

        System.out.println("这是什么类型呢？");
        System.out.println("1：动画\n2：轻小说\n3：漫画");

        String kindname=scan.nextLine();
        boolean leag=false;
        while(!leag){
            leag=true;
            if(kindname.matches("[0-9]*")){
                switch (kindname){
                    case "1"->kindname="动画";
                    case "2"->kindname="轻小说";
                    case "3"->kindname="漫画";
                    default -> {
                        System.out.println("超出界限，请重新输入");
                        leag=false;
                    }
                }
            }
        }
        Element kindNameNode=document.createElement(kindname);
        kindNode.appendChild(kindNameNode);

        kindNode.appendChild(seasonNode);
        kindNode.appendChild(cnNameNode);
        kindNode.appendChild(enNameNode);
        kindNode.appendChild(jpNameNode);
        kindNode.appendChild(startTimeNode);
        kindNode.appendChild(endTimeNode);
        addSubFile(kindNode,document);
        animesNode.appendChild(kindNode);
    }
}
