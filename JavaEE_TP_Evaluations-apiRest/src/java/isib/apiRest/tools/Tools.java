/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.apiRest.tools;

import java.text.*;
import java.util.*;

/**
 *
 * @author LOIC
 */
public final class Tools {
    
    public static Date convertStringToDate(String value) throws ParseException{
        return new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH).parse(value);
    }
}
