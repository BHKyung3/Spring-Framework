/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.102
 * Generated at: 2025-05-27 08:40:09 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class uploadAjax_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(4);
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js\"></script>\r\n");
      out.write("<style>\r\n");
      out.write("	.uploadResult {\r\n");
      out.write("	    width: 100%;\r\n");
      out.write("	    background-color: gray;\r\n");
      out.write("	}\r\n");
      out.write("\r\n");
      out.write("	.uploadResult ul {\r\n");
      out.write("	    display: flex;\r\n");
      out.write("	    flex-flow: row;\r\n");
      out.write("	    justify-content: center;\r\n");
      out.write("	    align-items: center;\r\n");
      out.write("	}\r\n");
      out.write("	\r\n");
      out.write("	.uploadResult ul li {\r\n");
      out.write("	    list-style: none;\r\n");
      out.write("	    padding: 10px;\r\n");
      out.write("	    align-content: center;\r\n");
      out.write("	    text-align: center;\r\n");
      out.write("	}\r\n");
      out.write("	\r\n");
      out.write("	.uploadResult ul li img {\r\n");
      out.write("	    width: 100px;\r\n");
      out.write("	}\r\n");
      out.write("	\r\n");
      out.write("	.uploadResult ul li img span{\r\n");
      out.write("	    color: white;\r\n");
      out.write("	}\r\n");
      out.write("	\r\n");
      out.write("	.bigPictureWrapper {\r\n");
      out.write("		position: absolute;\r\n");
      out.write("		display: none;\r\n");
      out.write("		justify-content: center;\r\n");
      out.write("		align-items: center;\r\n");
      out.write("		top: 0%;\r\n");
      out.write("		width: 100%;\r\n");
      out.write("		height: 100%;\r\n");
      out.write("		background-color: gray;\r\n");
      out.write("		z-index: 100;\r\n");
      out.write("		background: rgba(255, 255, 255, 0.5);\r\n");
      out.write("	}\r\n");
      out.write("	\r\n");
      out.write("	.bigPicture {\r\n");
      out.write("		position: relative;\r\n");
      out.write("		display: flex;\r\n");
      out.write("		justify-content: center;\r\n");
      out.write("		align-items: center;\r\n");
      out.write("	}\r\n");
      out.write("	\r\n");
      out.write("	.bigPicture img {\r\n");
      out.write("		width: 600px;\r\n");
      out.write("	}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	<h1>Upload with Ajax</h1>\r\n");
      out.write("	\r\n");
      out.write("	<div class=\"uploadDiv\">\r\n");
      out.write("		<input type=\"file\" name=\"uploadFile\" multiple=\"multiple\">\r\n");
      out.write("	</div>\r\n");
      out.write("	\r\n");
      out.write("	<div class=\"uploadResult\">\r\n");
      out.write("		<ul>\r\n");
      out.write("		</ul>\r\n");
      out.write("	</div>\r\n");
      out.write("	\r\n");
      out.write("	<button id=\"uploadBtn\">Upload</button>\r\n");
      out.write("	\r\n");
      out.write("	<div class='bigPictureWrapper'>\r\n");
      out.write("	  <div class='bigPicture'>\r\n");
      out.write("	  </div>\r\n");
      out.write("	</div>\r\n");
      out.write("	\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("	function showImage(fileCallPath) {\r\n");
      out.write("		$(\".bigPictureWrapper\").css(\"display\", \"flex\").show();\r\n");
      out.write("		$(\".bigPicture\")\r\n");
      out.write("		  .html(\"<img src='/display?fileName=\" + encodeURI(fileCallPath) + \"'>\")\r\n");
      out.write("		  .animate({width: '100%', height: '100%'}, 1000);\r\n");
      out.write("	}; // end showImage\r\n");
      out.write("\r\n");
      out.write("	$(document).ready(function() {\r\n");
      out.write("		\r\n");
      out.write("		let regex = new RegExp(\"(.*?)\\.(exe|sh|zip|alz)$\")\r\n");
      out.write("		let maxSize = 5242880; // 5MB\r\n");
      out.write("		\r\n");
      out.write("		$(\".uploadResult\").on(\"click\", \"span\", function(e) {\r\n");
      out.write("			let targetFile = $(this).data(\"file\");\r\n");
      out.write("			let type = $(this).data(\"type\");\r\n");
      out.write("			\r\n");
      out.write("			$.ajax({\r\n");
      out.write("				url: '/deleteFile',\r\n");
      out.write("				data: {fileName: targetFile, type: type},\r\n");
      out.write("				dataType: 'text',\r\n");
      out.write("				type: 'post',\r\n");
      out.write("				\r\n");
      out.write("				success: function(result) {\r\n");
      out.write("					alert(result);\r\n");
      out.write("				}\r\n");
      out.write("			});\r\n");
      out.write("		});\r\n");
      out.write("		\r\n");
      out.write("		// 원본 이미지가 전체 화면을 차지하기 때문에 이미지 클릭시 사라지도록 하는 이벤트\r\n");
      out.write("		$(\".bigPictureWrapper\").on(\"click\", function(e) {\r\n");
      out.write("			$(\".bigPicture\").animate({width: '0%', height: '0%'}, 1000);\r\n");
      out.write("			setTimeout(()=>{\r\n");
      out.write("				$(this).hide();\r\n");
      out.write("			}, 1000);\r\n");
      out.write("		}); // end 원본 이미지가 전체 화면을 차지하기 때문에 이미지 클릭시 사라지도록 하는 이벤트\r\n");
      out.write("		\r\n");
      out.write("		function checkExtension(fileName, fileSize) {\r\n");
      out.write("			if(fileSize >= maxSize){\r\n");
      out.write("				alert(\"파일 사이즈 초과\");\r\n");
      out.write("				return false;\r\n");
      out.write("			}\r\n");
      out.write("			\r\n");
      out.write("			if(regex.test(fileName)){\r\n");
      out.write("				alert(\"해당 종류의 파일은 업로드 할 수 없습니다.\");\r\n");
      out.write("				return false;\r\n");
      out.write("			}\r\n");
      out.write("			\r\n");
      out.write("			return true;\r\n");
      out.write("		}\r\n");
      out.write("		\r\n");
      out.write("		// 함수 만들기\r\n");
      out.write("		let uploadResult = $(\".uploadResult ul\");\r\n");
      out.write("		\r\n");
      out.write("		function showUploadFile(uploadResultArr) {\r\n");
      out.write("			let str = \"\";\r\n");
      out.write("			\r\n");
      out.write("			$(uploadResultArr).each(function(i, obj) {\r\n");
      out.write("				\r\n");
      out.write("				if(!obj.image){\r\n");
      out.write("					\r\n");
      out.write("					let fileCallPath = encodeURIComponent(obj.uploadPath + \"/\" + obj.uuid + \"_\" + obj.fileName); \r\n");
      out.write("					// 이미지 삭제\r\n");
      out.write("					let fileLink = fileCallPath.replace(new RegExp(/\\\\/g), \"/\");\r\n");
      out.write("		               \r\n");
      out.write("					str += \"<li><div><a href='/download?fileName=\" + fileCallPath + \"'>\"\r\n");
      out.write("					     + \"<img src='/resources/img/attach.png'>\" + obj.fileName + \"</a>\"\r\n");
      out.write("					     + \"<span data-file=\\'\" + fileCallPath + \"\\' data-type='file'> x </span>\" + \"</div></li>\";\r\n");
      out.write("\r\n");
      out.write("				}else{\r\n");
      out.write("					let fileCallPath = encodeURIComponent(obj.uploadPath + \"/s_\" + obj.uuid + \"_\" + obj.fileName);\r\n");
      out.write("		               \r\n");
      out.write("		               let orginPath = obj.uploadPath + \"\\\\\" + obj.uuid + \"_\" + obj.fileName;\r\n");
      out.write("		               \r\n");
      out.write("		               orginPath = orginPath.replace(new RegExp(/\\\\/g), \"/\");\r\n");
      out.write("		               \r\n");
      out.write("		               str += \"<li><a href=\\\"javascript:showImage(\\'\" + orginPath + \"\\')\\\">\" + \r\n");
      out.write("			               \"<img src='display?fileName=\" + fileCallPath + \"'></a>\" +\r\n");
      out.write("			               \"<span data-file=\\'\" + fileCallPath + \"\\' data-type='image'> x </span>\"\r\n");
      out.write("			               + \"</li>\";\r\n");
      out.write("//					str += \"<li>\" + obj.fileName + \"</li>\"	\r\n");
      out.write("				}\r\n");
      out.write("			});\r\n");
      out.write("			\r\n");
      out.write("			uploadResult.append(str);\r\n");
      out.write("		} // end showUploadFile\r\n");
      out.write("		\r\n");
      out.write("		let cloneObj = $(\".uploadDiv\").clone(); // 파일 업로드 영역을 동적으로 추가\r\n");
      out.write("		\r\n");
      out.write("		$(\"#uploadBtn\").on(\"click\", function(e) { // 버튼을 클릭하면 동작\r\n");
      out.write("			let formData = new FormData();\r\n");
      out.write("			let inputFile = $(\"input[name='uploadFile']\"); // inputFile : 업로드한 파일 정보를 가지고 있음\r\n");
      out.write("			\r\n");
      out.write("			let files = inputFile[0].files;\r\n");
      out.write("//			console.log(inputFile)\r\n");
      out.write("//			console.log(\"----------\")\r\n");
      out.write("//			console.log(files)\r\n");
      out.write("\r\n");
      out.write("			// 사진 저장 경로\r\n");
      out.write("			for(let i=0; i<files.length; i++){\r\n");
      out.write("				\r\n");
      out.write("				if(!checkExtension(files[i].name, files[i].size)){\r\n");
      out.write("					return false;\r\n");
      out.write("				}\r\n");
      out.write("				\r\n");
      out.write("				formData.append(\"uploadFile\", files[i]);\r\n");
      out.write("			}\r\n");
      out.write("			console.log(\"formData\");\r\n");
      out.write("			console.log(formData);\r\n");
      out.write("			\r\n");
      out.write("			$.ajax({\r\n");
      out.write("				url : \"/uploadAjaxAction\",\r\n");
      out.write("				type : \"post\",\r\n");
      out.write("				processData : false, // 필수 -> 데이터를 문자열로 변환하지 말라\r\n");
      out.write("				contentType : false, // contentType 설정하지 않음(자동으로 enctype=\"multipart/form-data\" 변경해줌)\r\n");
      out.write("				data : formData,\r\n");
      out.write("				dataType : 'json', // 전달 받은 데이터 형식이 json\r\n");
      out.write("				success : function(result) {\r\n");
      out.write("					console.log(result);\r\n");
      out.write("					\r\n");
      out.write("					showUploadFile(result); // 호출\r\n");
      out.write("					$(\".uploadDiv\").html(cloneObj.html());\r\n");
      out.write("				}\r\n");
      out.write("			});\r\n");
      out.write("		});\r\n");
      out.write("	});\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
