import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Scanner;

public class DocGenerater {
    String motherDir="dmhyrss\\20220106";

    DocGenerater() throws ParserConfigurationException, TransformerException {
        Scanner scan=new Scanner(System.in);
        File output=new File("output");
        if(!output.exists()){
            output.mkdir();
        }
        while(true) {
            System.out.println("请输入下一个动漫名，输入1退出");
            String dirName = scan.nextLine();
            if (!dirName.equals("1")) {
                File dir = new File(output, dirName);
                dir.mkdir();
                getxml(dir);
            }else{
                break;
            }
        }
    }

    private void getxml(File dir) throws ParserConfigurationException, TransformerException {
        Scanner scan=new Scanner(System.in);

        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder =documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        Element animes=document.createElement("animes");

        addAurl(animes,document);

        boolean corn;
        do{
            addAkind(animes,document);
            System.out.println("还要继续增加一个kind吗？（y/n）（1/0）");
            String c=scan.nextLine();
            switch(c){
                case "y", "1" ->corn=true;
                case "n","0",""->corn=false;
                default -> corn=true;
            }
        }while(corn);
        document.appendChild(animes);

        TransformerFactory transformerFactory=TransformerFactory.newInstance();
        Transformer transformer=transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        transformer.transform(new DOMSource(document),new StreamResult(new File(dir,"animeInfo.xml")));

    }

    private void addAurl(Element animes, Document document) {
        Scanner scan=new Scanner(System.in);
        System.out.println("请输入资料来源,回车退出");
        String url=scan.nextLine();
        while(!url.equals("")){
            Element urlNode=document.createElement("url");
            urlNode.setTextContent(url);
            animes.appendChild(urlNode);
            System.out.println("请输入资料来源,回车退出");
            url=scan.nextLine();
        }
    }

    private void addAkind(Element animesNode, Document document) {
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

        animesNode.appendChild(kindNode);
    }

    private void addKindName(Element kindNode, Document document) {
        System.out.println("这是什么类型呢？");
        System.out.println("0：退出\n1：动画\n2：轻小说\n3：漫画\n4：音乐\n5：电影");
        Scanner scan=new Scanner(System.in);
        String kindname=scan.nextLine();

        boolean leag=false;
        while(!leag){
            leag=true;
            {
                switch (kindname){
                    case "0" -> {
                        return;
                    }
                    case "1"->kindname="动画";
                    case "2"->kindname="轻小说";
                    case "3"->kindname="漫画";
                    case "4"->kindname="音乐";
                    case "5"->kindname="电影";
                    default -> {
                        System.out.println("超出界限，请重新输入");
                        leag=false;
                        kindname=scan.nextLine();
                    }
                }
            }
        }
        Element kindNameNode=document.createElement("kind_name");
        kindNameNode.setTextContent(kindname);
        kindNode.appendChild(kindNameNode);
    }

    private void addSeason(Element kindNode, Document document) {
        System.out.println("请输入这是第几季");
        Scanner scan=new Scanner(System.in);
        String season=scan.nextLine();
        if(season.equals("")){
            season="1";
        }
        Element seasonNode=document.createElement("season");
        seasonNode.setTextContent(season);
        kindNode.appendChild(seasonNode);
    }

    private void addCnName(Element kindNode, Document document) {
        Scanner scan=new Scanner(System.in);
        outer:while(true){
            System.out.println("这部动漫有中文名吗？（y=1，n=0，name）");
            String tnn=scan.nextLine();
            switch(tnn){
                case "y", "1" -> {
                    System.out.println("请输入这部动漫的中文名");
                    tnn=scan.nextLine();
                }
                case "n", "0", "" -> {
                    break outer;
                }
                default -> {
                    ;
                }
            }
            Element cnNameNode=document.createElement("cn_name");
            cnNameNode.setTextContent(tnn);
            kindNode.appendChild(cnNameNode);
        }

    }

    private void addEnName(Element kindNode, Document document) {
        Scanner scan=new Scanner(System.in);

        outer:while(true){
            System.out.println("这部动漫有英文名吗？（y=1，n=0，name）");
            String tnn=scan.nextLine();
            switch(tnn){
                case "y", "1" -> {
                    System.out.println("请输入这部动漫的英文名");
                    tnn=scan.nextLine();
                }
                case "n", "0", "" -> {
                    break outer;
                }
                default -> {
                    ;
                }
            }
            Element enNameNode=document.createElement("en_name");
            enNameNode.setTextContent(tnn);
            kindNode.appendChild(enNameNode);
        }
    }

    private void addjpName(Element kindNode, Document document) {
        Scanner scan=new Scanner(System.in);


        outer:while(true){
            System.out.println("这部动漫有日文名吗？（y=1，n=0，name）");
            String tnn=scan.nextLine();
            switch(tnn){
                case "y", "1" -> {
                    System.out.println("请输入这部动漫的日文名");
                    tnn=scan.nextLine();
                }
                case "n", "0", "" -> {
                    break outer;
                }
                default -> {
                    ;
                }
            }
            Element jpNameNode=document.createElement("jp_name");
            jpNameNode.setTextContent(tnn);
            kindNode.appendChild(jpNameNode);
        }
    }

    private void addStartTime(Element kindNode, Document document) {
        Scanner scan=new Scanner(System.in);
        System.out.println("请输入这部动漫的开始时间（yyyy.mm.dd）");
        String startTime=scan.nextLine();
        Element startTimeNode=document.createElement("start_time");
        startTimeNode.setTextContent(startTime);
        kindNode.appendChild(startTimeNode);
    }

    private void addEndTime(Element kindNode, Document document) {
        Scanner scan=new Scanner(System.in);
        System.out.println("请输入这部动漫的结束时间（yyyy.mm.dd）");
        String endTime=scan.nextLine();
        Element endTimeNode=document.createElement("end_time");
        endTimeNode.setTextContent(endTime);
        kindNode.appendChild(endTimeNode);
    }

    private void addSubFiles(Element kindNode, Document document) {
        Scanner scan=new Scanner(System.in);
        boolean corn;
        do{
            addASubFile(kindNode,document);
            System.out.println("还要继续添加sub_file吗？（y=1，n=0）");
            String c=scan.nextLine();
            switch(c){
                case "y", "1" ->corn=true;
                case "n","0"->corn=false;
                default -> corn=false;
            }
        }while(corn);
    }

    private void addASubFile(Element kindNode, Document document) {
        Element subFileNode=document.createElement("sub_file");

        addSubFileKindName(subFileNode,document);

        kindNode.appendChild(subFileNode);
    }

    private void addSubFileKindName(Element subFileNode, Document document) {
        Scanner scan=new Scanner(System.in);
        System.out.println("这是什么类型呢？");
        System.out.println("0:退出\n1：动画\n2：oped\n3：整体预告(pv)\n4：分级预告\n5：特典\n6：ova\n7：menu\n8：动画宣传\n9：字幕\n10：ost\n11：相关图片\n12：音乐\n13：演唱会\n14：Extra Clip from DVD（额外片段）\n15：其他");
        String kindname=scan.nextLine();




        boolean leag=false;
        while(!leag){
            leag=true;
            {
                switch (kindname){
                    case "0" -> {
                        return;
                    }
                    case "1"-> {
                        kindname = "动画";
                        donghua(subFileNode, document);
                    }
                    case "2"-> {
                        kindname = "oped";
                        oped(subFileNode, document);
                    }
                    case "3"-> {
                        kindname = "整体预告";
                        yugao(subFileNode, document);
                    }
                    case "4"-> {
                        kindname = "分级预告";
                        yugao(subFileNode, document);
                    }
                    case "5"-> {
                        kindname = "特典";
                        yugao(subFileNode, document);
                    }
                    case "6"-> {
                        kindname = "ova";
                        yugao(subFileNode, document);
                    }
                    case "7"-> {
                        kindname = "menu";
                        yugao(subFileNode, document);
                    }
                    case "8"-> {
                        kindname = "动画宣传";
                        yugao(subFileNode, document);
                    }
                    case "9"-> {
                        kindname = "字幕";
                        zimu(subFileNode, document);
                    }
                    case "10"-> {
                        kindname = "ost";
                        ost(subFileNode, document);
                    }
                    case "11"-> {
                        kindname = "相关图片";
                        xuanchuantu(subFileNode, document);
                    }
                    case "12"-> {
                        kindname = "音乐";
                        yinyue(subFileNode, document);
                    }
                    case "13"-> {
                        kindname = "演唱会";
                        donghua(subFileNode, document);
                    }
                    case "14"-> {
                        kindname = "额外片段";
                        donghua(subFileNode, document);
                    }
                    case "15"-> {
                        kindname = "其他";
                        qita(subFileNode, document);
                    }
                    default -> {
                        System.out.println("超出界限，请重新输入");
                        kindname=scan.nextLine();
                        leag=false;
                    }
                }
            }
        }
        System.out.println("请输入文件名？");
        String dirName=scan.nextLine();
        Element kindNameNode=document.createElement("kind_name");
        kindNameNode.setTextContent(kindname);
        subFileNode.appendChild(kindNameNode);

        Element dirNameNode=document.createElement("name");
        dirNameNode.setTextContent(dirName);
        subFileNode.appendChild(dirNameNode);
    }

    private void qita(Element subFileNode, Document document) {
        addSubFileOrignNames(subFileNode,document);
    }

    private void donghua(Element subFileNode, Document document) {
        Scanner scan=new Scanner(System.in);
        System.out.println("什么语言的动画？def=ja");
        String lang=scan.nextLine();
        if(lang.equals("")){
            lang="ja";
        }
        Element langNode=document.createElement("lang");
        langNode.setTextContent(lang);
        subFileNode.appendChild(langNode);

        outer:while (true){
            System.out.println("有字幕吗？(y=1,n=0,zh_cn=2,zh_tw=3,ja=4,en=5,def:zh_cn)");
            String subLang=scan.nextLine();
            switch (subLang){
                case ""-> subLang="zh_cn";
                case "n", "0" -> {
                    break outer;
                }
                case "y", "1"->{
                    System.out.println("请输入");
                    subLang=scan.nextLine();
                }
                case "2"->{
                    subLang="zh_cn";
                }
                case "3"->{
                    subLang="zh_tw";
                }
                case "4"->{
                    subLang="ja";
                }
                case "5"->{
                    subLang="en";
                }
                default -> {
                    ;
                }
            }
            Element subLangNode=document.createElement("sub_lang");
            subLangNode.setTextContent(subLang);
            subFileNode.appendChild(subLangNode);
        }

        addSubFileOrignNames(subFileNode,document);
    }

    private void zimu(Element subFileNode, Document document) {
        Scanner scan=new Scanner(System.in);
        outer:while (true){
            System.out.println("什么语言的字幕？(y=1,n=0,zh_cn=2,zh_tw=3,ja=4,en=5,def:zh_cn)");
            String subLang=scan.nextLine();
            switch (subLang){
                case ""-> subLang="zh_cn";
                case "n", "0" -> {
                    break outer;
                }
                case "y", "1"->{
                    System.out.println("请输入");
                    subLang=scan.nextLine();
                }
                case "2"->{
                    subLang="zh_cn";
                }
                case "3"->{
                    subLang="zh_tw";
                }
                case "4"->{
                    subLang="ja";
                }
                case "5"->{
                    subLang="en";
                }
                default -> {
                    ;
                }
            }
            Element subLangNode=document.createElement("sub_lang");
            subLangNode.setTextContent(subLang);
            subFileNode.appendChild(subLangNode);
        }

        addSubFileOrignNames(subFileNode,document);
    }

    private void oped(Element subFileNode, Document document) {
        donghua(subFileNode,document);
    }

    private void yugao(Element subFileNode, Document document) {
        donghua(subFileNode,document);
    }

    private void ost(Element subFileNode, Document document) {
        Scanner scan=new Scanner(System.in);
        System.out.println("什么语言的音乐？");
        String lang=scan.nextLine();
        if(lang.equals("")){
            lang="ja";
        }
        Element langNode=document.createElement("lang");
        langNode.setTextContent(lang);
        subFileNode.appendChild(langNode);

        outer:while (true){
            System.out.println("有歌词吗？什么语言(y=1,n=0,zh_cn=2,zh_tw=3,ja=4,en=5,def:zh_cn");
            String subLang=scan.nextLine();
            switch (subLang){
                case ""-> subLang="zh_cn";
                case "n", "0" -> {
                    break outer;
                }
                case "y", "1"->{
                    System.out.println("请输入");
                    subLang=scan.nextLine();
                }
                case "2"->{
                    subLang="zh_cn";
                }
                case "3"->{
                    subLang="zh_tw";
                }
                case "4"->{
                    subLang="ja";
                }
                case "5"->{
                    subLang="en";
                }
                default -> {
                    ;
                }
            }
            Element subLangNode=document.createElement("sub_lang");
            subLangNode.setTextContent(subLang);
            subFileNode.appendChild(subLangNode);
        }
        addSubFileOrignNames(subFileNode,document);
    }

    private void xuanchuantu(Element subFileNode, Document document) {
        Scanner scan=new Scanner(System.in);
        System.out.println("什么语言的宣传图？");
        String lang=scan.nextLine();
        if(lang.equals("")){
            lang="ja";
        }
        Element langNode=document.createElement("lang");
        langNode.setTextContent(lang);
        subFileNode.appendChild(langNode);

        addSubFileOrignNames(subFileNode,document);
    }

    private void yinyue(Element subFileNode, Document document) {
        Scanner scan=new Scanner(System.in);
        System.out.println("什么语言的音乐？");
        String lang=scan.nextLine();
        if(lang.equals("")){
            lang="ja";
        }
        Element langNode=document.createElement("lang");
        langNode.setTextContent(lang);
        subFileNode.appendChild(langNode);

        outer:while (true){
            System.out.println("有歌词吗？什么语言(y=1,n=0,字幕def:zh_cn)");
            String subLang=scan.nextLine();
            switch (subLang){
                case ""-> subLang="zh_cn";
                case "n", "0" -> {
                    break outer;
                }
                case "y", "1"->{
                    System.out.println("请输入");
                    subLang=scan.nextLine();
                }
                default -> {
                    ;
                }
            }
            Element subLangNode=document.createElement("sub_lang");
            subLangNode.setTextContent(subLang);
            subFileNode.appendChild(subLangNode);
        }
        addSubFileOrignNames(subFileNode,document);
    }

    private void addSubFileOrignNames(Element subFileNode, Document document) {
        Scanner scan=new Scanner(System.in);
        boolean corn;
        do{
            addSubFileOrignName(subFileNode,document);
            System.out.println("还要增加原文件夹吗？（y=1，n=0）");
            String c=scan.nextLine();
            switch(c){
                case "y", "1" ->corn=true;
                case "n","0"->corn=false;
                default -> corn=false;
            }
        }while(corn);
    }

    private void addSubFileOrignName(Element subFileNode, Document document) {
        Element originNameNode=document.createElement("origin_name");

        addOriginNameFrom(originNameNode,document);
        //addOriginNameName(originNameNode,document);
        addOriginNameNowName(originNameNode,document);

        subFileNode.appendChild(originNameNode);
    }

    private void addOriginNameFrom(Element originNameNode, Document document) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入来自哪个文件夹（defalut：）"+motherDir);
        String dir=scanner.nextLine();
        if(dir.equals("")){
            dir=motherDir;
        }
        Element name=document.createElement("from");
        name.setTextContent(dir);
        originNameNode.appendChild(name);
    }

    private void addOriginNameName(Element originNameNode, Document document) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入原文件夹名（defalut：%[subfile_name]）");
        String dir=scanner.nextLine();
        if(dir.equals("")){
            dir="%[subfile_name]";
        }
        Element name=document.createElement("name");
        name.setTextContent(dir);
        originNameNode.appendChild(name);
    }

    private void addOriginNameNowName(Element originNameNode, Document document) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入文件名变化（基于原名变化）（defalut：%[nowname]）");
        String dir=scanner.nextLine();
        if(dir.equals("")){
            dir="%[nowname]";
        }
        Element name=document.createElement("file_name");
        name.setTextContent(dir);
        originNameNode.appendChild(name);
    }


}



