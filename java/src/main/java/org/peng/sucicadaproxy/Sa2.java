package org.peng.sucicadaproxy;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Sa2", value = "/Sa2/*")
public class Sa2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
StringBuffer sb = request.getRequestURL();
        System.out.println(sb);
        String contextPath = request.getContextPath();
        System.out.println("contextPath" + contextPath);
        String uri = request.getRequestURI();
        System.out.println("uri : " + uri);
        String newuri = StringUtils.remove(uri,contextPath);
        System.out.println("new uri : " + newuri);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
