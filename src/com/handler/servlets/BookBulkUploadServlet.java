package com.handler.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import com.data.bean.BookBean;
import com.gen.exception.ServiceException;
import com.gen.util.AppLogger;
import com.gen.util.ApplicationConstants;
import com.gen.util.LoadProperties;
import com.logic.service.BookService;
import com.logic.service.Implementation.BookServiceImpl;

public class BookBulkUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Logger logger = null;
	private LoadProperties properties;
	
	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = ApplicationConstants.MAX_FILE_SIZE;
	private int maxMemSize = ApplicationConstants.MAX_MEM_SIZE;
	private File file ;
	
	private HttpSession session;
	
	private BookService bookService;
	
    public BookBulkUploadServlet() {
        super();
    }

    public void init() {
    	logger = AppLogger.getLogger();
    	properties = new LoadProperties();
    	
    	filePath = getServletContext().getInitParameter("file-upload");
	}
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(false);
		if (session != null) {
			request.setAttribute("page", properties.getPropertyForValue("goBack"));
		}
		
		logger.info(properties.getPropertyForValue("servletExit") + BookBulkUploadServlet.class);
		request.getRequestDispatcher(properties.getPropertyForValue("handelRequest")).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info(properties.getPropertyForValue("servletEntry") + BookBulkUploadServlet.class);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		List<FileItem> fileItems = null;
		Iterator<FileItem> iterator =null;
		FileInputStream inputStream = null;
		ServletFileUpload upload = null;

		List<BookBean> bookBeans = null;
		bookService = new BookServiceImpl();
		BookBean bookBean = null;
		Scanner scanner = null;
		String[] fileValues = null; 

		session = request.getSession(false);
		try {
			if (session != null) {
				isMultipart = ServletFileUpload.isMultipartContent(request);
				response.setContentType("text/html");

				if(isMultipart) {
					// maximum size that will be stored in memory
					factory.setSizeThreshold(maxMemSize);
					// Location to save data that is larger than maxMemSize.
					factory.setRepository(new File("c:\\temp"));
					upload = new ServletFileUpload(factory);
					// maximum file size to be uploaded.
					upload.setSizeMax( maxFileSize );

					// Parse the request to get file items.
					fileItems = upload.parseRequest(new ServletRequestContext(request));
					iterator = fileItems.iterator();

					while (iterator.hasNext()) {
						DiskFileItem diskFileItem = (DiskFileItem)iterator.next();
						if ( !diskFileItem.isFormField () ) {
							String fileName = diskFileItem.getName();

							logger.info(properties.getPropertyForValue("logFileDetails")
									+ " File Name: " + fileName
									+ " Field Name: " + diskFileItem.getFieldName()
									+ " Content Type:" + diskFileItem.getContentType()
									+ " Is The File In Memory: " + diskFileItem.isInMemory()
									+ " File Siza In Bytes: " + diskFileItem.getSize());
							// Write the file
							if( fileName.lastIndexOf("\\") >= 0 ) {
								file = new File(filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
							} else {
								file = new File(filePath + "\\" + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
							}
							diskFileItem.write(file) ;
						}
					}

					bookBeans = new ArrayList<BookBean>();
					inputStream = new FileInputStream(file);
					scanner = new Scanner(inputStream, ApplicationConstants.CHARSET_NAME);
					while (scanner.hasNextLine()) {
						bookBean = new BookBean();
						String line = scanner.nextLine();
						fileValues = line.split(ApplicationConstants.SEMICOLON);

						bookBean.setBookName(fileValues[ApplicationConstants.VALUE_ZERO]);
						bookBean.setAuthorName(fileValues[ApplicationConstants.VALUE_ONE]);
						bookBean.setAvailabilityDate(new SimpleDateFormat(ApplicationConstants.DATE_FORMAT)
								.parse(fileValues[ApplicationConstants.VALUE_TWO]));
						bookBean.setGenreId(Integer.parseInt(fileValues[ApplicationConstants.VALUE_THREE]));

						bookBeans.add(bookBean);
					}

					session.setAttribute("NoOfBookUploaded", bookService.saveBookInfo(bookBeans).size());

					request.getServletContext().setAttribute("bookBulkUploaded", new Boolean(true));
					request.setAttribute("page", BookBulkUploadServlet.class);
				}
				
				logger.info(properties.getPropertyForValue("servletExit") + BookBulkUploadServlet.class);
				request.getRequestDispatcher(properties.getPropertyForValue("handelRequest")).forward(request, response);
			}
		} catch (ServiceException serviceException) {
			logger.error((serviceException.toString() + "\n" + serviceException.getMessage()));
			throw new ServletException(serviceException);
		} catch (Exception exception) {
			logger.error((exception.toString() + "\n" + exception.getMessage()));
			throw new ServletException(properties.getPropertyForValue("bookBulkUploadFileException"));
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (scanner != null) {
				scanner.close();
			}
		}
	}
}
