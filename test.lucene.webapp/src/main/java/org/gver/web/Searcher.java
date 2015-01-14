package org.gver.web;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.gver.lucene.NewsSearch;
import org.gver.search.BaiduZhidaoSearch;
import org.gver.web.handlebars.HandlebarsEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author wanggen on 14-8-11.
 */
@RequestMapping(value = {"/"})
@Controller
public class Searcher {

    @Autowired
    private NewsSearch newsSearch;

    @Autowired
    private BaiduZhidaoSearch baiduZhidaoSearch;

    @Autowired
    private HandlebarsEngine handlebarsEngine;

    @RequestMapping(value = {"/"}, method = {RequestMethod.GET})
    public ModelAndView search(@RequestBody String val, @RequestParam String q, HttpServletResponse response) throws Exception {

        List<String> pages = newsSearch.search(q);

        return new ModelAndView("/index.jsp", ImmutableMap.<String, Object>of("pages", pages));

    }

    @RequestMapping(value = {"/zhidao"}, method = {RequestMethod.GET})
    public void searchZhidao(HttpServletRequest request, HttpServletResponse response, @RequestBody String val, @RequestParam String q) throws Exception {

        System.out.println("URL: "+request.getRequestURL());
        System.out.println("URI: "+request.getRequestURI());
        System.out.println("QueryString: "+request.getQueryString());

        List<String> pages = baiduZhidaoSearch.search(q);

        Map context = Maps.newHashMap();
        context.put("_DATA_", pages);


        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(handlebarsEngine.apply(context, "index"));

//        return new ModelAndView("/index.jsp", ImmutableMap.<String, Object>of("pages", pages));
    }

}
