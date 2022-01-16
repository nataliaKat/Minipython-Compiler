/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class AFCallValue extends PValue
{
    private TId _id_;
    private PFuncCall _funcCall_;

    public AFCallValue()
    {
    }

    public AFCallValue(
        TId _id_,
        PFuncCall _funcCall_)
    {
        setId(_id_);

        setFuncCall(_funcCall_);

    }
    public Object clone()
    {
        return new AFCallValue(
            (TId) cloneNode(_id_),
            (PFuncCall) cloneNode(_funcCall_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAFCallValue(this);
    }

    public TId getId()
    {
        return _id_;
    }

    public void setId(TId node)
    {
        if(_id_ != null)
        {
            _id_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _id_ = node;
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
            + toString(_id_)
            + toString(_funcCall_);
    }

    void removeChild(Node child)
    {
        if(_id_ == child)
        {
            _id_ = null;
            return;
        }

        if(_funcCall_ == child)
        {
            _funcCall_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_id_ == oldChild)
        {
            setId((TId) newChild);
            return;
        }

        if(_funcCall_ == oldChild)
        {
            setFuncCall((PFuncCall) newChild);
            return;
        }

    }
}
