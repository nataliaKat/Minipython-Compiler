/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import minipython.analysis.*;

public final class TCompEq extends Token
{
    public TCompEq()
    {
        super.setText("==");
    }

    public TCompEq(int line, int pos)
    {
        super.setText("==");
        setLine(line);
        setPos(pos);
    }

    public Object clone()
    {
      return new TCompEq(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTCompEq(this);
    }

    public void setText(String text)
    {
        throw new RuntimeException("Cannot change TCompEq text.");
    }
}
