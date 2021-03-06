/**
 * Open Settlers - an open implementation of the game Settlers of Catan
 * Copyright (C) 2003  Robert S. Thomas
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>. **/
package soc.util;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.5 $
 */
public class Pair
{
    private Object a;
    private Object b;

    /**
     * Creates a new Pair object initialized with null values.
     */
    public Pair() {
        this(null, null);
    }

    /**
     * Creates a new Pair object.
     *
     * @param i DOCUMENT ME!
     * @param j DOCUMENT ME!
     */
    public Pair(Object i, Object j)
    {
        a = i;
        b = j;
    }

    /**
     * returns a hash code for the object
     *
     * @return the hash code
     */
    public int hashCode()
    {
        return ((a == null) ? 0 : a.hashCode()) ^ ((b == null) ? 0 : b.hashCode());
    }
    
    /**
     * DOCUMENT ME!
     *
     * @param o DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public boolean equals(Object o)
    {
        if (o instanceof Pair)
        {
            Pair ip = (Pair)o;
            if (((ip.a == a) && (ip.b == b)) || ((ip.a == b) && (ip.b == a)))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Object getA()
    {
        return a;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Object getB()
    {
        return b;
    }

    /**
     * DOCUMENT ME!
     *
     * @param val DOCUMENT ME!
     */
    public void setA(Object val)
    {
        a = val;
    }

    /**
     * DOCUMENT ME!
     *
     * @param val DOCUMENT ME!
     */
    public void setB(Object val)
    {
        b = val;
    }
}
