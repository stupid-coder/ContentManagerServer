package org.buaa.cms.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by stupid-coder on 6/28/16.
 */
public class TimeUtils {

    private static final SimpleDateFormat simpledf = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss");

    public static String GetTimeStamps()
    {
        return simpledf.format(new Date());
    }

}
