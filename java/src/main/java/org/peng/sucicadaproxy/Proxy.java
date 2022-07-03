package org.peng.sucicadaproxy;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 访问   /Proxy,
 * :/Proxy/port/url?perament
 */
@WebServlet(name = "Proxy", value = "/Proxy/*")
public class Proxy extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contextPath = request.getContextPath();
        /* 得到 host
         * 从当前的类加载器的getResourcesAsStrm来获取
         * InputStream inputStream = this.getClass().getResourceAsStream(name)
         */
        java.util.Properties pro = new java.util.Properties();
        //InputStream inputStream = this.getClass().getResourceAsStream("/resources/host.properties");
        //pro.load(inputStream);
//        pro.load(new FileInputStream(this.getClass().getResource("/host.properties").getFile()));
        pro.put("host", "http://43.142.108.162");
        String host = pro.getProperty("host");

        String uri = request.getRequestURI();

        uri = StringUtils.remove(uri, contextPath);
        String parturl[] = StringUtils.split(uri, "/");
        String port = parturl[1];
        String url = parturl[2];

        // 构建新的url
        if (StringUtils.endsWith(host,"/"))        {
            host = StringUtils.removeEnd(host,"/") + ":";
        }else{
            host = host + ":";
        }
        port = port + "/";

        url = host + port + url;
logger.debug(url);
        System.out.println(url);
        //request.getRequestDispatcher(url).forward(request, response);
        //response.sendRedirect(url)
        String parament = null;
        Map<String,String[]> paramentMap = request.getParameterMap();
        Set keysets = paramentMap.keySet();
        Iterator<String> it = keysets.iterator();
        while (it.hasNext()) {
            String key = it.next();
            String value[] = paramentMap.get(key);
            for (int i = 0 ; i < value.length ; i ++){
                parament = key + "=" + value[i] + "&";
            }

        }
        parament = StringUtils.removeEnd(parament,"&");
        url = url + "?" + parament;
        System.out.println("url : " + url);
        response.sendRedirect(url);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
