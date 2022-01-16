/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class AOrCompComparison extends PComparison
{
    private PComparison _l_;
    private PComparison _r_;

    public AOrCompComparison()
    {
    }

    public AOrCompComparison(
        PComparison _l_,
        PComparison _r_)
    {
        setL(_l_);

        setR(_r_);

    }
    public Object clone()
    {
        return new AOrCompComparison(
            (PComparison) cloneNode(_l_),
            (PComparison) cloneNode(_r_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAOrCompComparison(this);
    }

    public PComparison getL()
    {
        return _l_;
    }

    public void setL(PComparison node)
    {
        if(_l_ != null)
        {
            _l_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _l_ = node;
    }

    public PComparison getR()
    {
        return _r_;
    }

    public void setR(PComparison node)
    {
        if(_r_ != null)
        {
            _r_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _r_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_l_)
            + toString(_r_);
    }

    void removeChild(Node child)
    {
        if(_l_ == child)
        {
            _l_ = null;
            return;
        }

        if(_r_ == child)
        {
            _r_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_l_ == oldChild)
        {
            setL((PComparison) newChild);
            return;
        }

        if(_r_ == oldChild)
        {
            setR((PComparison) newChild);
            return;
        }

    }
}