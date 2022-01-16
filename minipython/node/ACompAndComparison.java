/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class ACompAndComparison extends PComparison
{
    private PComparison _comparison_;

    public ACompAndComparison()
    {
    }

    public ACompAndComparison(
        PComparison _comparison_)
    {
        setComparison(_comparison_);

    }
    public Object clone()
    {
        return new ACompAndComparison(
            (PComparison) cloneNode(_comparison_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACompAndComparison(this);
    }

    public PComparison getComparison()
    {
        return _comparison_;
    }

    public void setComparison(PComparison node)
    {
        if(_comparison_ != null)
        {
            _comparison_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _comparison_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_comparison_);
    }

    void removeChild(Node child)
    {
        if(_comparison_ == child)
        {
            _comparison_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_comparison_ == oldChild)
        {
            setComparison((PComparison) newChild);
            return;
        }

    }
}
