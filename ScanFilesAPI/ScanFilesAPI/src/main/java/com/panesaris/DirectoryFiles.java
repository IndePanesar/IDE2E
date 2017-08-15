/**
 * Author : Inderpal Panesar
 * Date   : 08/2017
 * Description :
 *  Populate two lists based on known and unknown content types
 */

package com.panesaris;

import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DirectoryFiles
{
    /**
     * List all the files in the directory provided
     *
     * @param p_DirectoryName to be listed
     */
    public Map<String, ArrayList<FilesStruct>> GetDirectoryFiles(String p_DirectoryName, String p_Type) throws NotDirectoryException
    {
        ArrayList<FilesStruct> knownTypes = new ArrayList<FilesStruct>();    // Files we can say the content is known
        ArrayList<FilesStruct> unknownTypes = new ArrayList<FilesStruct>();  // Files we can say the content is unknown
        File directory = new File(p_DirectoryName);

        // Check the item given is a directory
        if (!directory.isDirectory())
        {
            throw new NotDirectoryException(p_DirectoryName + " - Not a valid directory.");
        }

        // Iterate through the items located in the directory and if its a file get it
        //and populaate the appropriate arraylist - knowncontent and unknowncontent
        for (File file : directory.listFiles())
        {
            if (file.isFile())
            {
                FilesStruct fs = new FilesStruct(file);
                if (fs.KnowFileContent() && p_Type != "" && fs.GetFileExtension().toLowerCase().equals(p_Type.toLowerCase()))
                {
                    knownTypes.add(fs);
                }
                else if (p_Type == "")
                {
                    if (fs.KnowFileContent())
                        knownTypes.add(fs);
                    else
                        unknownTypes.add(fs);
                }
            }
        }
        // Populate the Map with known and unknown content lists. Note these could just be empty
        Map<String, ArrayList<FilesStruct>> filesMap = new HashMap<String, ArrayList<FilesStruct>>();
        filesMap.put("KnownTypes - (" + knownTypes.size() + ")", knownTypes);
        filesMap.put("UnKnownTypes - (" + unknownTypes.size() + ")", unknownTypes);

        return filesMap;
    }
}