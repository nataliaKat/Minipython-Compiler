/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import minipython.analysis.*;

public final class TMinusEq extends Token
{
    public TMinusEq()
    {
        super.setText("-=");
    }

    public TMinusEq(int line, int pos)
    {
        super.setText("-=");
        setLine(line);
        setPos(pos);
    }

    public Object clone()
    {
      return new TMinusEq(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTMinusEq(this);
    }

    public void setText(String text)
    {
        throw new RuntimeException("Cannot change TMinusEq text.");
    }
}
