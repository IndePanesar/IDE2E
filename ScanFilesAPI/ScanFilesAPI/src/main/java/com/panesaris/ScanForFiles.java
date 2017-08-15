/**
* Author : Inderpal Panesar
* Date   : 08/2017
* Description :
*  This is the main class that uses the HttpServletRequest and HttpServletResponse objects to listen for the
*  request and send back a response either as HTML or JSON.
*/

package com.panesaris;

import java.io.*;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.*;

// Inherit from the HttpServlet class
public class ScanForFiles extends HttpServlet
{
    // Function return some simple HTML for the error response
    private String ErrorHTML(String p_ErrorMsg, String p_Directory, boolean p_Error)
    {
        return "\n" +
                "<html>\n" +
                "<body>\n" +
                "<h1 id=\"error\"><font color=" + (p_Error ? "red" : "green") + ">" + p_ErrorMsg + "</font></h1>\n" +
                "<h2><p id=\"directory\"><font color=\"blue\">" + p_Directory + "</font></p></h2>\n" +
                "</body>\n" +
                "</html>\n";
    }

    // The HTTP Get method
    public void doGet(HttpServletRequest p_Req, HttpServletResponse p_Resp) throws ServletException, IOException
    {
        // Get the request parameters for drive and directory path
        String reqDrive = p_Req.getParameter("drive");
        String reqPath = p_Req.getParameter("path");
        String reqMime = p_Req.getParameter("mime");
        String mime = "";

        // If drive or path not given then assume C:/"
        // Build up the path based on the drive and relative path from root
        if (reqDrive == null || reqDrive.isEmpty())
            reqDrive = "C:/";
        else if (!reqDrive.contains(":"))
            reqDrive = reqDrive + ":/";
        else if (!reqDrive.contains("/"))
            reqDrive = reqDrive + "/";

        if (reqPath == null) reqPath = "";
        if (!reqPath.isEmpty() && !reqPath.startsWith("/"))
            reqPath = "/" + reqPath;

        // The directory to scan for files
        final String directory = reqDrive + reqPath;
        directory.replace("//","/");
        // A mime type is specified
        if (reqMime != null && !reqMime.isEmpty())
            mime = reqMime;

        // Set responseobject
        p_Resp.setContentType("text/html");
        p_Resp.setCharacterEncoding("utf-8");

        // Using Google Json Gson create Json Object
        Gson json = new Gson();
        try
        {
            boolean filesExist = false;

            // put some value pairs into the JSON object .
            Map<String, ArrayList<FilesStruct>> mapfs = new DirectoryFiles()
                    .GetDirectoryFiles(directory, mime);

            // Check we have something to convertto JSON
            for (Map.Entry<String, ArrayList<FilesStruct>> entry : mapfs.entrySet())
            {
                if (entry.getValue().size() > 0)
                    filesExist = true;
            }

            if (!filesExist)
                // There are no files found in this directory
                p_Resp.getWriter().print(ErrorHTML("No files found whilst parsing ", directory, false));
            else
            {
                // There are files in this directory so create the JSON to send back
                p_Resp.setContentType("application/json");
                p_Resp.getWriter().print(json.toJson(mapfs));
            }
        }
        catch (Exception exp)
        {
            // Exception thrown whilst parsing
            p_Resp.getWriter().print(ErrorHTML("Error whilst parsing\n", exp.getMessage(), true));
        }
    }
}
