/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class AArgument extends PArgument
{
    private TId _id_;
    private final LinkedList _value_ = new TypedLinkedList(new Value_Cast());
    private final LinkedList _notFirstArgument_ = new TypedLinkedList(new NotFirstArgument_Cast());

    public AArgument()
    {
    }

    public AArgument(
        TId _id_,
        List _value_,
        List _notFirstArgument_)
    {
        setId(_id_);

        {
            this._value_.clear();
            this._value_.addAll(_value_);
        }

        {
            this._notFirstArgument_.clear();
            this._notFirstArgument_.addAll(_notFirstArgument_);
        }

    }
    public Object clone()
    {
        return new AArgument(
            (TId) cloneNode(_id_),
            cloneList(_value_),
            cloneList(_notFirstArgument_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAArgument(this);
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

    public LinkedList getValue()
    {
        return _value_;
    }

    public void setValue(List list)
    {
        _value_.clear();
        _value_.addAll(list);
    }

    public LinkedList getNotFirstArgument()
    {
        return _notFirstArgument_;
    }

    public void setNotFirstArgument(List list)
    {
        _notFirstArgument_.clear();
        _notFirstArgument_.addAll(list);
    }

    public String toString()
    {
        return ""
            + toString(_id_)
            + toString(_value_)
            + toString(_notFirstArgument_);
    }

    void removeChild(Node child)
    {
        if(_id_ == child)
        {
            _id_ = null;
            return;
        }

        if(_value_.remove(child))
        {
            return;
        }

        if(_notFirstArgument_.remove(child))
        {
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

        for(ListIterator i = _value_.listIterator(); i.hasNext();)
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

        for(ListIterator i = _notFirstArgument_.listIterator(); i.hasNext();)
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

    private class Value_Cast implements Cast
    {
        public Object cast(Object o)
        {
            PValue node = (PValue) o;

            if((node.parent() != null) &&
                (node.parent() != AArgument.this))
            {
                node.parent().removeChild(node);
            }

            if((node.parent() == null) ||
                (node.parent() != AArgument.this))
            {
                node.parent(AArgument.this);
            }

            return node;
        }
    }

    private class NotFirstArgument_Cast implements Cast
    {
        public Object cast(Object o)
        {
            PNotFirstArgument node = (PNotFirstArgument) o;

            if((node.parent() != null) &&
                (node.parent() != AArgument.this))
            {
                node.parent().removeChild(node);
            }

            if((node.parent() == null) ||
                (node.parent() != AArgument.this))
            {
                node.parent(AArgument.this);
            }

            return node;
        }
    }
}