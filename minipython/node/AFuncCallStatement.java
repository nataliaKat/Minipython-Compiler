/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class AFuncCallStatement extends PStatement
{
    private PFuncCall _funcCall_;

    public AFuncCallStatement()
    {
    }

    public AFuncCallStatement(
        PFuncCall _funcCall_)
    {
        setFuncCall(_funcCall_);

    }
    public Object clone()
    {
        return new AFuncCallStatement(
            (PFuncCall) cloneNode(_funcCall_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAFuncCallStatement(this);
    }

    public PFuncCall getFuncCall()
    {
        return _funcCall_;
    }

    public void setFuncCall(PFuncCall node)
    {
        if(_funcCall_ != null)
        {
            _funcCall_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _funcCall_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_funcCall_);
    }

    void removeChild(Node child)
    {
        if(_funcCall_ == child)
        {
            _funcCall_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_funcCall_ == oldChild)
        {
            setFuncCall((PFuncCall) newChild);
            return;
        }

    }
}
