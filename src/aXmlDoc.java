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

        addKindName(kindNode,document);
        addSeason(kindNode,document);
        addCnName(kindNode,document);
        addEnName(kindNode,document);
        addjpName(kindNode,document);
        addStartTime(kindNode,document);
        addEndTime(kindNode,document);
        addSubFiles(kindNode,document);
        animesNode.appendChild(kindNode);
    }

    private void addKindName(Element kindNode, Document document) {
        System.out.println("这是什么类型呢？");
        System.out.println("1：动画\n2：轻小说\n3：漫画");
        Scanner scan=new Scanner(System.in);
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
    }

    private void addSeason(Element kindNode, Document document) {
    }

    private void addCnName(Element kindNode, Document document) {
    }

    private void addEnName(Element kindNode, Document document) {
    }

    private void addjpName(Element kindNode, Document document) {
    }

    private void addStartTime(Element kindNode, Document document) {
    }

    private void addEndTime(Element kindNode, Document document) {
    }

    private void addSubFiles(Element kindNode, Document document) {
        Scanner scan=new Scanner(System.in);
        boolean corn;
        do{
            addASubFile(kindNode,document);
            System.out.println("还要继续吗？（y/n）（0/1）");
            String c=scan.nextLine();
            switch(c){
                case "y", "0" ->corn=true;
                case "n","1"->corn=false;
                default -> corn=false;
            }
        }while(corn);
    }

    private void addASubFile(Element kindNode, Document document) {
        Element subFileNode=document.createElement("sub_file");

        addSubFileKindName(subFileNode,document);
        addSubFileName(subFileNode,document);
        addSubFilelang(subFileNode,document);
        addSubFileSubLang(subFileNode,document);
        addSubFileOrignNames(subFileNode,document);

        kindNode.appendChild(subFileNode);
    }

    private void addSubFileKindName(Element subFileNode, Document document) {
    }

    private void addSubFileName(Element subFileNode, Document document) {
    }

    private void addSubFilelang(Element subFileNode, Document document) {
    }

    private void addSubFileSubLang(Element subFileNode, Document document) {
    }

    private void addSubFileOrignNames(Element subFileNode, Document document) {
        Scanner scan=new Scanner(System.in);
        boolean corn;
        do{
            addSubFileOrignName(subFileNode,document);
            System.out.println("还要继续吗？（y/n）（0/1）");
            String c=scan.nextLine();
            switch(c){
                case "y", "0" ->corn=true;
                case "n","1"->corn=false;
                default -> corn=false;
            }
        }while(corn);
    }

    private void addSubFileOrignName(Element subFileNode, Document document) {
        Element originNameNode=document.createElement("origin_name");
        
        addOriginNameFrom(originNameNode,document);
        addOriginNameName(originNameNode,document);
        addOriginNameNowName(originNameNode,document);
    }

    private void addOriginNameFrom(Element originNameNode, Document document) {
    }

    private void addOriginNameName(Element originNameNode, Document document) {
    }

    private void addOriginNameNowName(Element originNameNode, Document document) {
    }


}



