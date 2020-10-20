package student.stusys.controller;
import java.io.*;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import student.stusys.logic.LStudent;
import student.stusys.logic.LTeacher;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class CUpload {
    @RequestMapping("/toupload")
    public String showUpload(){
        return "upload";
    }
    @RequestMapping("/download")
    public void downLoad(HttpServletResponse response){
        String fileName = "线稿2.jpg";
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        String pathName ="D:/Googledownload/download/" + fileName;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(
                    new File(pathName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("export file finish");
    }
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public @ResponseBody String upload(MultipartFile file, HttpSession session){
        String  homework_stu_code = session.getAttribute("homework_stu_code").toString();
        System.out.println(homework_stu_code);
        try {
            String pathName ="D:/Googledownload/download/" + file.getOriginalFilename();
            System.out.println(pathName);
            FileUtils.writeByteArrayToFile(new File(pathName), file.getBytes());
            return  "upload";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }
    @RequestMapping(value = "stu_upload_homework", method = RequestMethod.POST)
    public @ResponseBody String stu_upload_homework(MultipartFile file, HttpSession session){
        String  homework_stu_code = session.getAttribute("homework_stu_code").toString();
        System.out.println(homework_stu_code);
        try {
            String pathName ="D:/Googledownload/download/" + file.getOriginalFilename();
            System.out.println(pathName);
            FileUtils.writeByteArrayToFile(new File(pathName), file.getBytes());
            String sqlTxt = "update homework_student " +
                    "set homework_txt = '"
                    +pathName+"' ," +
                    " homework_filename= '"
                    + file.getOriginalFilename()+
                    "' where homework_stu_code = '"
                    +homework_stu_code+ "'";
            System.out.println(sqlTxt);
            LStudent stuUphomework =new LStudent();
            stuUphomework.updateHomeworkUrl(sqlTxt);
            return  "upload";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }


    @RequestMapping("/teacher_download_homework")
    public void teacher_download_homework(HttpServletResponse response, HttpServletRequest request){
        String homeworkPath=request.getParameter("homework_txt");
        LTeacher A=new LTeacher();
        String fname="空文件";
        fname= request.getParameter("homework_filename");

        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + fname);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        String pathName =homeworkPath;//"D:/WSHOP/download/" + fileName;
        // System.out.println(pathName);
        // System.out.println(hwPath);
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(
                    new File(pathName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("export file finish");
    }
}
