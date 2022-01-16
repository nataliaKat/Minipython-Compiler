/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class AGoal extends PGoal
{
    private final LinkedList _goalHelper_ = new TypedLinkedList(new GoalHelper_Cast());

    public AGoal()
    {
    }

    public AGoal(
        List _goalHelper_)
    {
        {
            this._goalHelper_.clear();
            this._goalHelper_.addAll(_goalHelper_);
        }

    }
    public Object clone()
    {
        return new AGoal(
            cloneList(_goalHelper_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAGoal(this);
    }

    public LinkedList getGoalHelper()
    {
        return _goalHelper_;
    }

    public void setGoalHelper(List list)
    {
        _goalHelper_.clear();
        _goalHelper_.addAll(list);
    }

    public String toString()
    {
        return ""
            + toString(_goalHelper_);
    }

    void removeChild(Node child)
    {
        if(_goalHelper_.remove(child))
        {
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        for(ListIterator i = _goalHelper_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set(newChild);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

    }

    private class GoalHelper_Cast implements Cast
    {
        public Object cast(Object o)
        {
            PGoalHelper node = (PGoalHelper) o;

            if((node.parent() != null) &&
                (node.parent() != AGoal.this))
            {
                node.parent().removeChild(node);
            }

            if((node.parent() == null) ||
                (node.parent() != AGoal.this))
            {
                node.parent(AGoal.this);
            }

            return node;
        }
    }
}
