package cn.xjtu.track.controller;

import cn.xjtu.track.common.GridBean;
import cn.xjtu.track.common.Result;
import cn.xjtu.track.common.Type;
import cn.xjtu.track.dao.DataDetailMapper;
import cn.xjtu.track.dao.DataItemMapper;
import cn.xjtu.track.entity.DataDetail;
import cn.xjtu.track.entity.DataItem;
import cn.xjtu.track.service.DataDetailService;
import cn.xjtu.track.service.DataItemService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

/**
 * Created by cartoonface on 2016/6/15.
 */
@Controller
@RequestMapping("/common/")
public class CommonController {

    @Autowired
    private DataItemService dataItemService;
    @Autowired
    private DataDetailService dataDetailService;

    @RequestMapping("resource_not_found")
    public String error404(){
        return "error/404";
    }

    @RequestMapping("method_ot_allowed")
    public String error405(){
        return "error/405";
    }

    @RequestMapping("write")
    public String write(){
        return "write";
    }

    @RequestMapping("test")
    @ResponseBody
    public JSONArray test(){
//        List<DataDetail> details = dataDetailService.getDataDetailList(116l);
        List<DataDetail> details = dataDetailService.getDataDetailList(111l);
        List<DataDetail> newDetails1 = dataDetailService.correctDataDetailList(details);
       // List<DataDetail> newDetails2 = dataDetailService.correctDataDetailList2(details);
        double longDiff = 0 ,laterDiff = 0 ;
        for(int i=0;i<newDetails1.size()-1;i++){
            longDiff =newDetails1.get(i+1).getLongitude()-newDetails1.get(i).getLongitude();
            laterDiff =newDetails1.get(i+1).getLatitude()-newDetails1.get(i).getLatitude();
            if(longDiff>0.00001||laterDiff>0.000005){
                System.out.println("new" + i);
                System.out.println(newDetails1.get(i+1).getLongitude()+"===="+newDetails1.get(i).getLongitude()+"=="+longDiff);
                System.out.println(details.get(i+1).getLatitude()+"===="+details.get(i).getLatitude()+"=="+laterDiff);
            }
        }
        JSONArray  ja = new JSONArray();
        for(int i=0;i<newDetails1.size();i++){
            JSONObject jo = new JSONObject();
            jo.put("longitude",newDetails1.get(i).getLongitude());
            jo.put("latitude",newDetails1.get(i).getLatitude());
            ja.add(jo);
        }
        return ja;
    }

    @RequestMapping("writeXML")
    @ResponseBody
    public Result writeXML() throws Exception{
        DataItem demo = new DataItem();
        demo.setOverallEval("A");
        demo.setType(Type.LocusType.TurnRight.ordinal());
        List<DataItem> items = dataItemService.getDataItem(demo);

        for(int i = 0 ;i<items.size();i++){
            demo = items.get(i);
            List<DataDetail> detailList = dataDetailService.getDataDetailList(demo.getId());
            List<DataDetail> details = dataDetailService.correctDataDetailList2(detailList);
            double longDiff = 0 ,laterDiff = 0 ;
            for(int j=0;j<details.size()-1;j++){
                longDiff =details.get(j+1).getLongitude()-details.get(j).getLongitude();
                laterDiff =details.get(j+1).getLatitude()-details.get(j).getLatitude();
                if(longDiff>0.00001||laterDiff>0.000005){
                    System.out.println("new" + j);
                    System.out.println(details.get(j+1).getLongitude()+"===="+details.get(j).getLongitude()+"=="+longDiff);
                    System.out.println(details.get(j+1).getLatitude()+"===="+details.get(j).getLatitude()+"=="+laterDiff);
                }
            }
            Document document = DocumentHelper.createDocument();

            //构造dom树
            Element root = document.addElement("VehicleBehavior");
            root.addElement("name").setText(demo.getFileName().replace(".txt",""));
            Element data = root.addElement("Data");
            for(int j=0;j<details.size();j++){
                Element dataDetail = data.addElement("detail");
                DataDetail detail = details.get(j);
                dataDetail.addElement("seqNum").setText(j+"");

                Element gps = dataDetail.addElement("gps");
                gps.addElement("longitude").setText(detail.getLongitude().toString());
                //.addAttribute("longitude",detail.getLongitude().toString());
                gps.addElement("latitude").setText(detail.getLatitude().toString());

                dataDetail.addElement("course").setText(detail.getCourse().toString());//航向角
                dataDetail.addElement("roll").setText(detail.getRoll().toString());//横滚角
                dataDetail.addElement("pitch").setText(detail.getPitch().toString());//俯仰角
                dataDetail.addElement("velE").setText(detail.getVelE().toString());//东向速度
                dataDetail.addElement("velN").setText(detail.getVelN().toString());//北向速度
                dataDetail.addElement("velU").setText(detail.getVelU().toString());//天向速度
                dataDetail.addElement("logAcce").setText(detail.getLogAcce().toString());//纵向加速度
                dataDetail.addElement("lateralAcce").setText(detail.getLateralAcce().toString());//横向加速度
                dataDetail.addElement("norAcce").setText(detail.getNorAcce().toString());//天向速度

            }
            //设置字符编码方式
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter writer = new XMLWriter(new FileWriter("D:\\data\\"+demo.getFileName().replace(".txt",".xml")),format);
            writer.write(document);
            writer.close();
        }

        return new Result(true,"文件写成功");
    }
}
