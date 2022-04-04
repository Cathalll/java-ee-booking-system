/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Collection;

/**
 *
 * @author Cathal
 */
public class Util {
    
    
  public static boolean contains(Collection<?> coll, Object o) {
    if (coll == null) return false;
    return coll.contains(o);
  }

    
}
