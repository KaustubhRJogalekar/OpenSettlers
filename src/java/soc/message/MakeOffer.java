/**
 * Open Settlers - an open implementation of the game Settlers of Catan
 * Copyright (C) 2003  Robert S. Thomas
 * Portions of this file Copyright (C) 2009 Jeremy D Monin <jeremy@nand.net>
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
package soc.message;

import soc.game.ResourceConstants;
import soc.game.ResourceSet;
import soc.game.TradeOffer;

import java.util.StringTokenizer;


/**
 * This message means that a player wants to trade with other players
 *
 * @author Robert S. Thomas
 */
public class MakeOffer extends Message
{
    private static final long serialVersionUID = -7977524162698583765L;

    /**
     * Name of game
     */
    private String game;

    /**
     * The offer being made
     */
    private TradeOffer offer;

    /**
     * Create a MakeOffer message.
     *
     * @param ga   the name of the game
     * @param of   the offer being made
     */
    public MakeOffer(String ga, TradeOffer of)
    {
        messageType = MAKEOFFER;
        game = ga;
        offer = of;
    }

    /**
     * @return the name of the game
     */
    public String getGame()
    {
        return game;
    }

    /**
     * @return the offer being made
     */
    public TradeOffer getOffer()
    {
        return offer;
    }

    /**
     * @return the command string
     */
    public String toCmd()
    {
        return toCmd(game, offer);
    }

    /**
     * @return the command string
     *
     * @param ga  the name of the game
     * @param of   the offer being made
     */
    public static String toCmd(String ga, TradeOffer of)
    {
        String cmd = MAKEOFFER + sep + ga;
        cmd += (sep2 + of.getFrom());

        boolean[] to = of.getTo();

        for (int i = 0; i < to.length; i++)  // length should be == game.maxPlayers
        {
            cmd += (sep2 + to[i]);
        }

        ResourceSet give = of.getGiveSet();

        for (int i = ResourceConstants.CLAY; i <= ResourceConstants.WOOD;
                i++)
        {
            cmd += (sep2 + give.getAmount(i));
        }

        ResourceSet get = of.getGetSet();

        for (int i = ResourceConstants.CLAY; i <= ResourceConstants.WOOD;
                i++)
        {
            cmd += (sep2 + get.getAmount(i));
        }

        return cmd;
    }

    /**
     * Parse the command String into a MakeOffer message
     *
     * @param s   the String to parse
     * @return    a MakeOffer message, or null of the data is garbled
     */
    public static MakeOffer parseDataStr(String s)
    {
        String ga; // the game name
        int from; // the number of the offering player
        boolean[] to; // the players to which this trade is offered
        ResourceSet give; // the set of resources being asked for 
        ResourceSet get; // the set of resources that the offerer wants in exchange

        give = new ResourceSet();
        get = new ResourceSet();

        StringTokenizer st = new StringTokenizer(s, sep2);

        try
        {
            ga = st.nextToken();
            from = Integer.parseInt(st.nextToken());
            final int numPlayerTokens = st.countTokens() - (2 * 5);  // Should be == game.maxPlayers
            to = new boolean[numPlayerTokens];

            for (int i = 0; i < numPlayerTokens; i++)
            {
                to[i] = (Boolean.valueOf(st.nextToken())).booleanValue();
            }

            /**
             * Note: this only works if ResourceConstants.CLAY == 1
             */
            for (int i = 1; i <= ResourceConstants.WOOD; i++)
            {
                give.setAmount(Integer.parseInt(st.nextToken()), i);
            }

            for (int i = 1; i <= ResourceConstants.WOOD; i++)
            {
                get.setAmount(Integer.parseInt(st.nextToken()), i);
            }
        }
        catch (Exception e)
        {
            return null;
        }

        return new MakeOffer(ga, new TradeOffer(ga, from, to, give, get));
    }

    /**
     * @return a human readable form of the message
     */
    public String toString()
    {
        return "MakeOffer:game=" + game + "|offer=" + offer;
    }
}
