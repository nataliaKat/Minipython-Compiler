/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class AGreatEqComparison extends PComparison
{
    private PExpression _exp1_;
    private PExpression _exp2_;

    public AGreatEqComparison()
    {
    }

    public AGreatEqComparison(
        PExpression _exp1_,
        PExpression _exp2_)
    {
        setExp1(_exp1_);

        setExp2(_exp2_);

    }
    public Object clone()
    {
        return new AGreatEqComparison(
            (PExpression) cloneNode(_exp1_),
            (PExpression) cloneNode(_exp2_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAGreatEqComparison(this);
    }

    public PExpression getExp1()
    {
        return _exp1_;
    }

    public void setExp1(PExpression node)
    {
        if(_exp1_ != null)
        {
            _exp1_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _exp1_ = node;
    }

    public PExpression getExp2()
    {
        return _exp2_;
    }

    public void setExp2(PExpression node)
    {
        if(_exp2_ != null)
        {
            _exp2_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _exp2_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_exp1_)
            + toString(_exp2_);
    }

    void removeChild(Node child)
    {
        if(_exp1_ == child)
        {
            _exp1_ = null;
            return;
        }

        if(_exp2_ == child)
        {
            _exp2_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_exp1_ == oldChild)
        {
            setExp1((PExpression) newChild);
            return;
        }

        if(_exp2_ == oldChild)
        {
            setExp2((PExpression) newChild);
            return;
        }

    }
}
