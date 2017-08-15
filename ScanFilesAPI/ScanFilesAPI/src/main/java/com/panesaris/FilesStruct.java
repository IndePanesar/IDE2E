/**
 * Author : Inderpal Panesar
 * Date   : 08/2017
 * Description :
 *  This class just sets up file data. The class is used to serialize the data to a JSON object
 *  Files are classified as knownContent or unknown content based on whether the file hasan extension
 *  which would allow us to determine the type of content it has. (Clearly not the ideal way).
 */

package com.panesaris;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;

public class FilesStruct
{
    // File properties
    private String _fileName;
    private String _mimeType;
    private String _size;
    private String _extension;
    private boolean _knowContent;

    // Getter methods returning the file data
    public String GetFileName()
    {
        return _fileName;
    }

    public String GetFileMimeType()
    {
        return _mimeType;
    }

    public String GetFileSize()
    {
        return _size;
    }

    public String GetFileExtension()
    {
        return _extension;
    }

    public boolean KnowFileContent()
    {
        return _knowContent;
    }

    // Constructor
    public FilesStruct(File p_File)
    {
        if (p_File != null)
            SetUpFileData(p_File);
    }

    // Setup file data
    private void SetUpFileData(File p_File)
    {
        _fileName = p_File.getName();
        _mimeType = new MimetypesFileTypeMap().getContentType(p_File);
        _size = String.format("%dKB", p_File.length()/1024 + 1);
        _extension = "";
        if ( _fileName.contains("."))
        {
            String[] exts = _fileName.split("\\.");
            _extension = exts[exts.length - 1];
        }

        _knowContent = _extension.length() > 0;
    }
}
