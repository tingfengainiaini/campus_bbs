<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<html:html>
  <head>
  <title>
  用户详细信息
  </title>
  </head>
  <body>
 	<%@ include file="/common/header.jsp"%>

	<html:errors />
	
	<%@ page import="bbsDAO.*"%>
	<%@ page import="java.util.*"%>
	<% 
	Vector uservector = (Vector)session
			.getAttribute(Constants.USERINFORMATION_KEY);
	User user = null;
	%>
	
    <table>
       <tr>
         <table width="90%" border="0">
            <h3> 
                              用户详细信息： 
            </h3>
         </table>
        </tr>
     <% 
    if(uservector!=null)
    {
    for(int i=0;i<uservector.size();i++)
    {
    user=(User) uservector.get(i);
    String username=user.getUsername();
    String usersex;
    String usergrade;
    String userqq;
    String usersignature;
    String useremail;
    String registertime;
    String lastlogtime;
    int submit=user.getSubmit();
    int qq;
    if(user.getSex()!=null)
    {
     usersex=user.getSex();
     if(usersex.equals("0"))
     {
      usersex="男";
     }else
     {
     
     usersex="女";}
    }
    else
    {
       usersex=" ";
    }
  
    if(user.getGrade()!=null)
    {
     usergrade=user.getGrade();
    }
    else
    {
    usergrade=" ";
    }
   
    
    if(user.getQq()!=null)
    {
      userqq=user.getQq();
    /*
      if(userqq.equals("null"))
      {
       userqq=" ";
      
      }*/
    //    qq=Integer.valueOf(userqq).intValue();
    }
    else
    {
       userqq=" ";
      
    }
    
    
    if(user.getSignature()!=null)
    {
    usersignature=user.getSignature();
    }
    else
    {
    usersignature=" ";
    }
   
    if(user.getEmail()!=null)
    {
    useremail=user.getEmail();
    }
    else
    {
      useremail=" ";
    }
    if(user.getRegistertime()!=null)
    {
       registertime=user.getRegistertime();
    }
    else
    {
      registertime=" ";
    }
    if(user.getLastlogtime()!=null)
    {
    lastlogtime=user.getLastlogtime();
    }
    else
    {
     lastlogtime=" ";
    }
    %>
     
      <tr>  
         <table width="90%" border="0" bgcolor="87ceff">
    
     
           <tr> 
             <td> 
                                      用户名： 
              </td> 
              <td>  
                      <%=username %> 
              </td> 
           </tr>
    
    
           <tr> 
              <td> 
                                           用户性别： 
              </td> 
              <td>  
             
               <%=usersex %> 
              </td> 
             </tr>
    
    
            <tr> 
              <td> 
                                         用户权限： 
             </td> 
             <td>  
                <%=usergrade %> 
             </td> 
            </tr>
    
    
           <tr> 
             <td> 
                                       用户qq： 
             </td> 
             <td>  
              <%=userqq %> 
             </td> 
           <td>     <a target="blank" href="tencent://message/?uin=qq&Site=百特网&Menu=yes"><img border="0" SRC="http://wpa.qq.com/pa?p=1:200864103:7" alt="点击这里给我发消息"></a>
</td></tr>
     
     
          <tr> 
            <td>
                                    用户邮箱：
            </td> 
            <td>  
              <%=useremail %> 
            </td> 
          </tr>
     
      
          <tr> 
             <td>
                                     用户签名：
            </td> 
            <td>  
                    <%=usersignature %> 
            </td> 
         </tr> 
      
         <tr> 
             <td>
                                     用户发帖数量：
            </td> 
            <td>  
                    <%=submit %> 
            </td> 
         </tr> 
         
          <tr> 
             <td>
                                     用户注册时间：
            </td> 
            <td>  
                    <%=registertime %> 
            </td> 
         </tr> 
         
         <tr> 
             <td>
                                     用户上次登录时间：
            </td> 
            <td>  
                    <%=lastlogtime %> 
            </td> 
         </tr> 
       
       </table>
      </tr>
      <% 
        }
      }
      %>
      <tr>
      <td>
      <A href="javascript:history.go(-1);">
                   返回列表
      </A>
      </td>
      </tr>
    </table>
    <%@ include file="/common/footer.jsp"%>
</body>
</html:html>