<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ page import="java.util.*" %>
<%@ page import="zyf.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
</head>
<body>
    <table align="center" width="700" border="1">
        <tr>
            <td align="center" colspan="5">
                <h2>所有用户信息</h2>
            </td>
        </tr>
        <tr align="center">
            <td><b>ID</b></td>
            <td><b>用户ID</b></td>
            <td><b>ACCOUNT</b></td>
        </tr>
        <%
            List<User> list=(List<User>)request.getAttribute("list");
            for(User p:list){
         %>
         <tr align="center">
             <td><%=p.getId() %></td>
             <td><%=p.getUser_id() %></td>
             <td><%=p.getAccount() %></td>
         </tr>
         <%
             }
          %>
          <tr>
              <td align="center" colspan="5">
                  <%=request.getAttribute("pageBar") %>
              </td>
          </tr>
    </table>
</body>
</html>