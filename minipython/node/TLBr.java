/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import minipython.analysis.*;

public final class TLBr extends Token
{
    public TLBr()
    {
        super.setText("[");
    }

    public TLBr(int line, int pos)
    {
        super.setText("[");
        setLine(line);
        setPos(pos);
    }

    public Object clone()
    {
      return new TLBr(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTLBr(this);
    }

    public void setText(String text)
    {
        throw new RuntimeException("Cannot change TLBr text.");
    }
}
