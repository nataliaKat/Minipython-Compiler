/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import minipython.analysis.*;

public final class TLen extends Token
{
    public TLen()
    {
        super.setText("len");
    }

    public TLen(int line, int pos)
    {
        super.setText("len");
        setLine(line);
        setPos(pos);
    }

    public Object clone()
    {
      return new TLen(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTLen(this);
    }

    public void setText(String text)
    {
        throw new RuntimeException("Cannot change TLen text.");
    }
}
