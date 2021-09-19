package Common;

import java.io.File;

public class AbsolutePath {

    public static String absPath = null;


    ///This method returns the absolute path of the project

    public AbsolutePath(String ProjectName) {
        absPath = new File(ProjectName).getAbsolutePath();
        absPath = absPath.substring(0, absPath.indexOf(ProjectName));
        absPath = absPath.replace("/", "//");
    }

    public static String Path() {
        return absPath;
    }
}
