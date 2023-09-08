package com.chilyfacts.com;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
public class FileUploadHandler extends jakarta.servlet.http.HttpServlet {
 private static final long serialVersionUID = 1 ;
 public void doGet(jakarta.servlet.http.HttpServlet request, jakarta.servlet.http.HttpServletResponse response) throws IOException {
 doPost(request, response);
 }
 public void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws IOException {
 String file_name = null;
 response.setContentType("text/html");
 PrintWriter out = response.getWriter();
 boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
 if (!isMultipartContent) {
 return;
 }
 FileItemFactory factory = new DiskFileItemFactory();
 ServletFileUpload upload = new ServletFileUpload(factory);
 try {
 List < FileItem > fields = upload.parseRequest(request);
 Iterator < FileItem > it = fields.iterator();
 if (!it.hasNext()) {
 return;
 }
 while (it.hasNext()) {
 FileItem fileItem = it.next();
 boolean isFormField = fileItem.isFormField();
 if (isFormField) {
 if (file_name == null) {
 if (fileItem.getFieldName().equals("file_name")) {
 file_name = fileItem.getString();
 }
 }
 } else {
 if (fileItem.getSize() > 0) {
 fileItem.write(new File("D:\\uploaded_files\\" + fileItem.getName()));
 }
 }
 }
 } catch (Exception e) {
 e.printStackTrace();
 } finally {
 out.println("<script type='text/javascript'>");
 out.println("window.location.href='index.jsp?filename="+file_name+"'");
 out.println("</script>");
 out.close();
 }
 }
}